package hexlet.code;
import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static hexlet.code.Parser.getDataFromFile;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws IOException {
        Map<String, Object> firstFileData = getDataFromFile(filePath1);
        Map<String, Object> secondFileData = getDataFromFile(filePath2);

        List<Map<String, Object>> differences = calculateDifferences(firstFileData, secondFileData);

        Formatter formatter;
        switch (format.toLowerCase()) {
            case "plain":
                formatter = new PlainFormatter();
                break;
            case "json":
                formatter = new JsonFormatter();
                break;
            case "stylish":
            default:
                formatter = new StylishFormatter();
        }

        return formatter.format(differences);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        Map<String, Object> firstFileData = getDataFromFile(filePath1);
        Map<String, Object> secondFileData = getDataFromFile(filePath2);

        List<Map<String, Object>> differences = calculateDifferences(firstFileData, secondFileData);

        Formatter formatter = new StylishFormatter();
        return formatter.format(differences);
    }

    private static List<Map<String, Object>> calculateDifferences(Map<String, Object> map1, Map<String, Object> map2) {
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
