package hexlet.code;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;
import java.nio.file.Paths;
import java.io.File;
import java.util.Set;
import java.util.TreeSet;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FilesDiffer {

    public static String diff(String filePath1, String filePath2) throws Exception {
        Map<String, Object> firstFileData = getDataFromFile(filePath1);
        Map<String, Object> secondFileData = getDataFromFile(filePath2);

        // Объединяем ключи из обоих файлов
        Set<String> allKeys = new TreeSet<>(firstFileData.keySet()); // TreeSet сразу сортирует
        allKeys.addAll(secondFileData.keySet());

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

    public static Map<String, Object> getDataFromFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = Paths.get(filePath).toFile(); // Преобразуем путь в объект File
        //System.out.println("PATH: " + Paths.get(filePath).toAbsolutePath());

        // читаем файл JSON и заполняем мапу
        Map<String, Object> resultMap = new HashMap<>(objectMapper.readValue(file, Map.class));

        return resultMap;
    }
}
