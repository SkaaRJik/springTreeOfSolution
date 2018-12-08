package ru.filippov.springTree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.filippov.springTree.treeOfSolution.TreeBuilder;
import ru.filippov.springTree.utils.DataFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/build")
public class BuildController {
    @Value("${upload.path}")
    String uploadPath;

    TreeBuilder treeBuilder;

    @GetMapping("")
    public String buildFromFileTree(@RequestParam String fileName) throws IOException {
        String[][] data = DataFactory.readUsingFileReader(new File(this.uploadPath+"/"+fileName), StandardCharsets.UTF_8);
        this.treeBuilder = new TreeBuilder(data);
        this.treeBuilder.start();
        return "build";
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    @ResponseBody
    public  String getStatus() throws IOException {
        return String.valueOf(this.treeBuilder.getStatus());
    }
}
