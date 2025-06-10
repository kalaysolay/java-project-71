package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;
import java.util.Map;

public final class JsonFormatter implements Formatter {

    @Override
    public String format(List<Map<String, Object>> differences) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(differences);
    }
}
