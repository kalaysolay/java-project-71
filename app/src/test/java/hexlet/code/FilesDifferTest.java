package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        String actual = FilesDiffer.diff(PATH_FIRST_JSON, PATH_SECOND_JSON, "stylish");
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
        String actual = FilesDiffer.diff(PATH_FIRST_JSON_HARD, PATH_SECOND_JSON_HARD, "stylish");
        System.out.println("Actual output:\n" + actual);

        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void testStylishFormat() throws Exception {
        String actual = FilesDiffer.diff(PATH_FIRST_JSON, PATH_SECOND_JSON, "stylish");
        assertThat(actual).contains("- follow").contains("+ verbose").contains("host: hexlet.io");
    }

    @Test
    public void testStylishHardJson() throws Exception {
        String result = FilesDiffer.diff(PATH_FIRST_JSON_HARD, PATH_SECOND_JSON_HARD, "stylish");
        assertThat(result).contains("chars2").contains("+ obj1").contains("- setting2");
    }

    @Test
    public void testPlainFormat() throws Exception {
        String actual = FilesDiffer.diff(PATH_FIRST_JSON_HARD, PATH_SECOND_JSON_HARD, "plain");
        assertThat(actual)
                .contains("Property 'chars2' was updated")
                .contains("Property 'key2' was added")
                .contains("Property 'numbers3' was removed");
    }
/*
    @Test
    public void testJsonFormat() throws Exception {
        String actual = FilesDiffer.diff(PATH_FIRST_JSON, PATH_SECOND_JSON, "json");
        assertThat(actual).startsWith("[{").contains("\"key\"").contains("\"oldValue\"");
    }
*/
    @Test
    public void testFileNotFound() {
        String badPath = "no/such/file.json";
        assertThatThrownBy(() -> FilesDiffer.diff(badPath, PATH_SECOND_JSON, "stylish"))
                .isInstanceOf(IOException.class);
    }

}
