package ru.filippov.springTree.treeOfSolution;

import com.google.gson.*;

import java.lang.reflect.Type;

public class NodeSerializer implements JsonSerializer<Node> {

    public NodeSerializer() {

    }

    @Override
    public JsonElement serialize(Node node, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();


        result.addProperty("label",  node.getLabel() );
        /*result.addProperty("x", node.getLevel());
        result.addProperty("y", node.getID()+padding);*/
        result.addProperty("id", node.getID());
        /*result.addProperty("color", "rgb(153,255,0)");
        result.addProperty("size", 4);*/
        result.addProperty("attribute", node.getAttributeName());
        result.addProperty("level", node.getLevel());
        JsonArray examples = new JsonArray(node.getExamples().size());
        for(int i : node.getExamples()){
            examples.add(i);
        }
        result.add("examples", examples);
        return result;
    }
}
