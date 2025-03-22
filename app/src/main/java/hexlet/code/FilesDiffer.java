package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FilesDiffer {

    public static String diff(String filePath1, String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode1 = objectMapper.readTree(new File(filePath1));
        JsonNode jsonNode2 = objectMapper.readTree(new File(filePath2));

        Map<String, Object> differences = new HashMap<>();
        compareJson("", jsonNode1, jsonNode2, differences);
        return differences.toString();
    }

    private static void compareJson(String path, JsonNode node1, JsonNode node2, Map<String, Object> differences) {
        if (!node1.equals(node2)) {
            if (node1.isObject() && node2.isObject()) {
                for (String fieldName : node1.fieldNames().toList()) {
                    //рекурсивно сравниваем каждый узел в json
                    compareJson(path + "." + fieldName, node1.get(fieldName), node2.get(fieldName), differences);
                }
            } else {
                differences.put(path.isEmpty() ? "root" : path, "Expected: " + node1 + ", Found: " + node2);
            }
        }
    }

    public static List<Map<String, Object>> getDifference(Map<String, Object> fileMap1, Map<String, Object> fileMap2) {
        List<Map<String, Object>> diffList = new ArrayList<>();
        Set<String> keys = new HashSet<>();
        keys.addAll(fileMap1.keySet());
        keys.addAll(fileMap2.keySet());
    }

    public static Map<String, Object> getContent(String fileContent) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.readValue(fileContent, new TypeReference<>() { });
        //return fileData;
    }
}
