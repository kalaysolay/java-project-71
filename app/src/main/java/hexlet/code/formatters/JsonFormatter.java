package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;
import java.util.Map;
/**
 * Formats the input data into JSON format.
 */
public  class JsonFormatter implements Formatter {

    @Override
    public String format(List<Map<String, Object>> differences) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(differences);
        } catch (Exception e) {
            throw new RuntimeException("Failed to format as JSON", e);
        }
    }
}
