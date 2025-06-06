package hexlet.code;
import hexlet.code.formatters.Formatter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.Map;

import static hexlet.code.DifferenceCalculator.calculateDifferences;
import static hexlet.code.Parser.parse;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
// Абсолютные пути
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

        // Считываем содержимое
        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);

        // Получаем формат по расширению
        String format1 = getFileType(filePath1);
        String format2 = getFileType(filePath2);

        if (!format1.equalsIgnoreCase(format2)) {
            throw new IllegalArgumentException("Files must have the same format");
        }

        // Парсим по содержимому и формату
        Map<String, Object> firstFileData = parse(content1, format1);
        Map<String, Object> secondFileData = parse(content2, format2);

        // Разница и форматирование
        List<Map<String, Object>> differences = calculateDifferences(firstFileData, secondFileData);
        Formatter formatter = FormatterFactory.getFormatter(format);
        return formatter.format(differences);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String getFileType(String file) throws Exception {
      /*  String[] fileParts;
        String delimeter = "\\.";
        fileParts = file.split(delimeter);
        String extension = fileParts[fileParts.length - 1];
        return extension;*/

        String[] fileParts = file.split("\\.");
        return fileParts[fileParts.length - 1];
    }
}
