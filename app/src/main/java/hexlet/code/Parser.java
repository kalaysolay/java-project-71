package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parse(String content, String format) throws IOException {
        ObjectMapper mapper = switch (format.toLowerCase()) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new YAMLMapper();
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
        return mapper.readValue(content, Map.class);
    }

    private static ObjectMapper getMapper(String format) {
        return switch (format.toLowerCase()) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new YAMLMapper();
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }


    public static Map<String, Object> getDataFromFile(String filePath) throws IOException {
        ObjectMapper mapper;
        if (filePath.endsWith(".json")) {
            mapper = new ObjectMapper();
        } else if (filePath.endsWith(".yml") || filePath.endsWith(".yaml")) {
            mapper = new YAMLMapper();
        } else {
            throw new IllegalArgumentException("Unsupported file format: " + filePath);
        }
        File file = Paths.get(filePath).toFile();
        return mapper.readValue(file, Map.class);
    }
}
