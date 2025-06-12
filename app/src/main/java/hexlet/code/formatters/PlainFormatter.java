package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public final class PlainFormatter implements Formatter {

    @Override
    public String format(List<Map<String, Object>> differences) throws Exception {
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> diff : differences) {
            String key = (String) diff.get("key");
            String type = (String) diff.get("type");

            switch (type) {
                case "added" -> result.append(
                        String.format("Property '%s' was added with value: %s\n",
                                key, toPlainValue(diff.get("value"))));
                case "deleted" -> result.append(
                        String.format("Property '%s' was removed\n", key));
                case "unchanged" -> {}
                case "changed" -> result.append(
                        String.format("Property '%s' was updated. From %s to %s\n",
                                key,
                                toPlainValue(diff.get("value1")),
                                toPlainValue(diff.get("value2"))));
                default -> throw new Exception("Unknown type: " + type);

            }
        }
        return result.toString().trim();
    }

    private String toPlainValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof List || value instanceof Map) {
            return "[complex value]";
        }
        return value.toString();
    }
}
