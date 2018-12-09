package ru.filippov.springTree.repos;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FilesRepo {

    public List<String> scan(String uploadPath) {
        List<String> files = new ArrayList<>();
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();

        } else {
            files = Arrays.asList(uploadDir.list());
        }
        return files;
    }
}
