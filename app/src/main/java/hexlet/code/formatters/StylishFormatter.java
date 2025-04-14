package hexlet.code.formatters;

import java.util.Map;
import java.util.List;
/**
 * Formats the input data into Stylish format.
 */
public class StylishFormatter implements Formatter {
    @Override
    public String format(List<Map<String, Object>> differences) {
        StringBuilder result = new StringBuilder("{\n");
        for (Map<String, Object> diff : differences) {
            String key = (String) diff.get("key");
            String type = (String) diff.get("type");

            switch (type) {
                case "added" -> result.append(String.format("  + %s: %s\n", key, toStringValue(diff.get("value"))));
                case "deleted" -> result.append(String.format("  - %s: %s\n", key, toStringValue(diff.get("value"))));
                case "unchanged" -> result.append(String.format("    %s: %s\n", key, toStringValue(diff.get("value"))));
                case "changed" -> {
                    result.append(String.format("  - %s: %s\n", key, toStringValue(diff.get("value1"))));
                    result.append(String.format("  + %s: %s\n", key, toStringValue(diff.get("value2"))));
                }
                default -> {
                    return  "Nothing";
                }
            }
        }
        result.append("}");
        return result.toString();
    }

    private String toStringValue(Object value) {
        return value == null ? "null" : value.toString();
    }
}
