package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FilesDifferTest {

    private static final String PATH_FIRST_JSON = "./src/test/resources/file1.json";
    private static final String PATH_SECOND_JSON = "./src/test/resources/file2.json";

    private static final String PATH_FIRST_JSON_HARD = "./src/test/resources/file1_hard.json";
    private static final String PATH_SECOND_JSON_HARD = "./src/test/resources/file2_hard.json";

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

    @Test
    public void testDifferGenerateDifficultJSON() throws Exception {
        String expected = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";
        String actual = FilesDiffer.diff(PATH_FIRST_JSON_HARD, PATH_SECOND_JSON_HARD);
        System.out.println("Actual output:\n" + actual);

        assertThat(actual).isEqualTo(expected);
    }
}
