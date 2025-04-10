package hexlet.code.formatters;
import hexlet.code.Difference;

import java.util.List;

public class PlainFormatter implements Formatter {
    @Override
    public String format(List<Difference> differences) {
        StringBuilder result = new StringBuilder();

        for (Difference diff : differences) {
            String key = diff.getKey();
            Object oldValue = diff.getOldValue();
            Object newValue = diff.getNewValue();

            if (oldValue == null) {
                result.append(String.format("Property '%s' was added with value: %s\n", key, formatValue(newValue)));
            } else if (newValue == null) {
                result.append(String.format("Property '%s' was removed\n", key));
            } else if (!oldValue.equals(newValue)) {
                result.append(String.format("Property '%s' was updated. From %s to %s\n", key, formatValue(oldValue), formatValue(newValue)));
            }
        }

        return result.toString().trim();
    }

    private String formatValue(Object value) {
        if (value instanceof String) {
            return "'" + value + "'";
        } else if (value instanceof List) {
            return "[complex value]";
        } else {
            return String.valueOf(value);
        }
    }
}