package hexlet.code.formatters;

import hexlet.code.Difference;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.List;

public class StylishFormatter implements Formatter {
    @Override
    public String format(List<Difference> differences) {
        StringBuilder result = new StringBuilder("{\n");
        for (Difference diff : differences) {
            String key = diff.getKey();
            Object oldValue = diff.getOldValue();
            Object newValue = diff.getNewValue();

            if (oldValue != null && newValue == null) {
                result.append(String.format("  - %s: %s\n", key, oldValue));
            } else if (!Objects.equals(oldValue, newValue)) {
                result.append(String.format("  - %s: %s\n", key, toStringValue(oldValue)));
                result.append(String.format("  + %s: %s\n", key, toStringValue(newValue)));
            } else if (!oldValue.equals(newValue)) {
                result.append(String.format("  - %s: %s\n", key, oldValue));
                result.append(String.format("  + %s: %s\n", key, newValue));
            } else {
                result.append(String.format("    %s: %s\n", key, oldValue));
            }
        }
        result.append("}");
        return result.toString();
    }

    private String toStringValue(Object value) {
        return value == null ? "null" : value.toString();
    }
}
