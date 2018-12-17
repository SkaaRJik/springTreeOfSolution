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

        //String result = "<button id='build'>Построить</button>";
        String result = "";
        result += "<table class=\"container\">\n";

        result+="\t<tr>\n";
        for(int j = 0 ; j < table[0].length; j++){
            result =  result + "<th class = \""+ table[0][j] +"\" onClick=\"goToBuild(this)\">" + table[0][j] + "</th>";
        }
        result+="\t</tr>\n";


        for(int i = 1 ; i < table.length; i++){
            result+="\t<tr>\n";
            for(int j = 0 ; j < table[i].length; j++){
                result =  result + "<td class = \""+ table[0][j] +"\" onClick=\"goToBuild(this)\">" + table[i][j] + "</td>";
            }
            result+="\t</tr>\n";

        }
        result+="</table>";
        return result;

    }

}
