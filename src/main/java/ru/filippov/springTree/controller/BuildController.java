package ru.filippov.springTree.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.filippov.springTree.treeOfSolution.Node;
import ru.filippov.springTree.treeOfSolution.NodeSerializer;
import ru.filippov.springTree.treeOfSolution.TreeBuilder;
import ru.filippov.springTree.utils.DataFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
    public  String getStatus() {
        return String.valueOf(this.treeBuilder.getStatus());
    }

    @RequestMapping(value = "/tree.json", method = RequestMethod.GET, produces = {"text/json; charset=UTF-8"})
    @ResponseBody
    public  String getTree() throws IOException {
        Node[][] adjacencyMatrix = this.treeBuilder.getAdjacencyMatrixOfTree();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Node.class, new NodeSerializer())
                .create();
        JsonObject treeInfo = new JsonObject();
        JsonArray nodes = new JsonArray();
        JsonArray edges = new JsonArray();
        treeInfo.add("edges", edges);
        treeInfo.add("nodes", nodes);

        List<Node> listOfNodes = new ArrayList<>();
        boolean[] visited = new boolean[adjacencyMatrix.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int[] parents = new int[adjacencyMatrix.length];
        parents[0] = -1;
        int edgeID = 0;
        while (!queue.isEmpty()){
            int v = queue.remove();
            for (int i = 0; i < adjacencyMatrix[v].length; i++) {
                if (adjacencyMatrix[v][i] != null) {
                    int to = adjacencyMatrix[v][i].getID();
                    if (!visited[to]) {
                        visited[to] = true;
                        listOfNodes.add(adjacencyMatrix[v][i]);
                        //nodes.add(gson.toJsonTree(adjacencyMatrix[v][i], Node.class));
                        queue.offer(to);
                        JsonObject edgeInfo = new JsonObject();

                        edgeInfo.addProperty("source", String.valueOf(adjacencyMatrix[i][i].getID()));
                        edgeInfo.addProperty("target", String.valueOf(adjacencyMatrix[v][i].getID()));
                        edgeInfo.addProperty("id", "E-"+String.valueOf(edgeID++));
                        edges.add(edgeInfo);
                        //d[to] = d[v] + 1;
                        parents[to] = v;
                    }
                }
            }
        }
        for(Node node : listOfNodes){
            nodes.add(gson.toJsonTree(node, Node.class));
        }

        return gson.toJson(treeInfo);
    }


    @RequestMapping(value = "/arctic.json", method = RequestMethod.GET, produces = {"text/json; charset=UTF-8"})
    @ResponseBody
    public  String getArctic() throws IOException {
        return DataFactory.readJson(new File(uploadPath+"/arctic.json"), StandardCharsets.UTF_8);
    }
}
