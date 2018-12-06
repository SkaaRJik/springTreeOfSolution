package ru.filippov.springTree.repos;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class FilesRepo {

    public Iterable<String> scan(String uploadPath) {
        Iterable<String> files = new ArrayList<>();
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();

        } else {
            files = Arrays.asList(uploadDir.list());
        }
        return files;
    }
}
