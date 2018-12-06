package ru.filippov.springTree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.filippov.springTree.repos.FilesRepo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private FilesRepo filesRepo;


    @GetMapping("/")
    public String index(Map<String, Object> model) throws IOException {
        Iterable<String> files = this.filesRepo.scan(this.uploadPath);
        model.put("files", files);
        return "index";
    }

    @PostMapping("/")
    public String upload(@RequestParam("file")MultipartFile file, Map<String, Object> model) throws IOException {
        
        if(file != null){
            File uploadDir = new File(this.uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            Iterable<String> files = this.filesRepo.scan(this.uploadPath);
            if(((List<String>) files).contains(file.getOriginalFilename())){
                model.put("message", "Файл с таким именем уже существует!");
            } else {
                model.put("message", "Файл успешно загружен");
                file.transferTo(new File(this.uploadPath + "/" + file.getOriginalFilename()));
            }
        }
        Iterable<String> files = this.filesRepo.scan(this.uploadPath);
        model.put("files", files);
        return "index";
    }



}
