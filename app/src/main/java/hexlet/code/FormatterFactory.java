package hexlet.code;

import hexlet.code.formatters.Formatter;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

public class FormatterFactory {
    public static Formatter getFormatter(String format) {
        return switch (format.toLowerCase()) {
            case "plain" -> new PlainFormatter();
            case "json" -> new JsonFormatter();
            case "stylish" -> new StylishFormatter();
            default -> new StylishFormatter(); // fallback на stylish
        };
    }
}
