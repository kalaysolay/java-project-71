package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> getDataFromFile(String filePath) throws IOException {
        ObjectMapper mapper = null;
        File file = Paths.get(filePath).toFile(); // Преобразуем путь в объект File
        //System.out.println("PATH: " + Paths.get(filePath).toAbsolutePath());
        if (filePath.endsWith("json")) {
            mapper = new ObjectMapper();
        } else if (filePath.endsWith("yml") || filePath.endsWith("yaml")) {
            mapper = new YAMLMapper();
        } else {
            throw new IllegalArgumentException("Illegal format: " + filePath);
        }
        // читаем файл JSON и заполняем мапу
        Map<String, Object> resultMap = new HashMap<>(mapper.readValue(file, Map.class));

        return resultMap;
    }
}
