package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.util.Map;

public class Parser {

    public static Map parse(String content, String format) throws Exception {
        ObjectMapper mapper = switch (format.toLowerCase()) {
            case "json" -> new ObjectMapper();
            case "yaml", "yml" -> new YAMLMapper();
            default -> throw new Exception("Unknown type: " + format);
        };
        return mapper.readValue(content, Map.class);
    }
}
