package hexlet.code.formatters;
import hexlet.code.Difference;

import java.util.List;

public interface Formatter {
    String format(List<Difference> differences);
}