package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map parse(String content, String format) throws IOException {
        ObjectMapper mapper = switch (format.toLowerCase()) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new YAMLMapper();
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
        return mapper.readValue(content, Map.class);
    }
}
