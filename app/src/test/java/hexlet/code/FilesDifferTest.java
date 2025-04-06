package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FilesDifferTest {

    private static final String PATH_FIRST_JSON = "./src/test/resources/file1.json";
    private static final String PATH_SECOND_JSON = "./src/test/resources/file2.json";

    @Test
    public void testDifferGenerateJSON() throws Exception {
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                + "}";
        String actual = FilesDiffer.diff(PATH_FIRST_JSON, PATH_SECOND_JSON);
        System.out.println("Actual output:\n" + actual);

        assertThat(actual).isEqualTo(expected);
    }
}
