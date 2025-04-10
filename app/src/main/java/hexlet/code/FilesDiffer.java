package hexlet.code;
import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.io.IOException;
import java.util.*;

import static hexlet.code.Parser.getDataFromFile;

public class FilesDiffer {
    public static String diff(String filePath1, String filePath2, String format) throws IOException {
        Map<String, Object> firstFileData = getDataFromFile(filePath1);
        Map<String, Object> secondFileData = getDataFromFile(filePath2);

        List<Difference> differences = calculateDifferences(firstFileData, secondFileData);

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

    private static List<Difference> calculateDifferences(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(firstMap.keySet());
        allKeys.addAll(secondMap.keySet());

        List<Difference> differences = new ArrayList<>();

        for (String key : allKeys) {
            Object firstValue = firstMap.get(key);
            Object secondValue = secondMap.get(key);

            if (!firstMap.containsKey(key)) {
                differences.add(new Difference(key, null, secondValue));
            } else if (!secondMap.containsKey(key)) {
                differences.add(new Difference(key, firstValue, null));
            } else if (!Objects.equals(firstValue, secondValue)) {
                differences.add(new Difference(key, firstValue, secondValue));
            } else {
                differences.add(new Difference(key, firstValue, firstValue));
            }
        }

        return differences;
    }
}
