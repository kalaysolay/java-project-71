package hexlet.code.formatters;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class StylishFormatter {
    public static String makeStylishFormat (Map<String, Object> firstFileData, Map<String, Object> secondFileData, Set<String> allKeys ) {
        StringBuilder result = new StringBuilder("{\n");

        for (String key : allKeys) {
            boolean inFirst = firstFileData.containsKey(key);
            boolean inSecond = secondFileData.containsKey(key);

            if (inFirst && inSecond) {
                Object firstValue = firstFileData.get(key);
                Object secondValue = secondFileData.get(key);

                // узел есть обоиз файлах. Выводим просто пробел
                if (Objects.equals(firstValue, secondValue)) {
                    result.append("    ").append(key).append(": ").append(firstValue).append("\n");
                } else {
                    // узла нет в первом файле,но узел есть во втором файле.
                    // Поэтому первой строкой первый файл, ниже - второй файл
                    result.append("  - ").append(key).append(": ").append(firstValue).append("\n");
                    result.append("  + ").append(key).append(": ").append(secondValue).append("\n");
                }
            } else if (inFirst) {
                result.append("  - ").append(key).append(": ").append(firstFileData.get(key)).append("\n");
            } else {
                result.append("  + ").append(key).append(": ").append(secondFileData.get(key)).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }
}
