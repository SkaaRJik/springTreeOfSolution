package ru.filippov.springTree.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.filippov.springTree.utils.DataFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Controller
public class AjaxController {
    @Value("${upload.path}")
    private String uploadPath;


    @RequestMapping(value = "/loadFile", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    @ResponseBody
    public  String readFile(@RequestParam String fileName) throws IOException {

        String[][] table = DataFactory.readUsingFileReader(new File(uploadPath+"/"+fileName), StandardCharsets.UTF_8);
        String result = "";
        for(String[] row : table){
            for(String column : row){
                result =  result + column + " ";
            }
            result+="<br>";
        }

        return result;

    }

    @RequestMapping(value = "/load", method = RequestMethod.GET, produces = {"text/html; charset=UTF-8"})
    public @ResponseBody String laod(){



        return "SUCCESS";

    }
}
