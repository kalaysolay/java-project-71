package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Difference;


import java.util.List;

public class JsonFormatter implements Formatter {
    @Override
    public String format(List<Difference> differences) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(differences);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert differences to JSON", e);
        }
    }
}