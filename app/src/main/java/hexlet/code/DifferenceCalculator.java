package hexlet.code;

import java.util.Objects;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class DifferenceCalculator {
    static List<Map<String, Object>> calculateDifferences(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());
        List<Map<String, Object>> result = new ArrayList<>();

        for (String key : keys) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("key", key);
            if (!map1.containsKey(key)) {
                entry.put("type", "added");
                entry.put("value", map2.get(key));
            } else if (!map2.containsKey(key)) {
                entry.put("type", "deleted");
                entry.put("value", map1.get(key));
            } else if (Objects.equals(map1.get(key), map2.get(key))) {
                entry.put("type", "unchanged");
                entry.put("value", map1.get(key));
            } else {
                entry.put("type", "changed");
                entry.put("value1", map1.get(key));
                entry.put("value2", map2.get(key));
            }
            result.add(entry);
        }
        return result;
    }
}
