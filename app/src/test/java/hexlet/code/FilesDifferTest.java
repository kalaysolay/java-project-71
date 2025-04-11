package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilesDifferTest {


    private static final String PATH_FIRST_JSON_HARD = "./src/test/resources/file1_hard.json";
    private static final String PATH_SECOND_JSON_HARD = "./src/test/resources/file2_hard.json";

    private static final String PATH_FIRST_YML = "./src/test/resources/file1.yml";
    private static final String PATH_SECOND_YML = "./src/test/resources/file2.yml";

    private static String expectedStylish;
    private static String expectedPlain;
    private static JsonNode expectedJson;

    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedStylish = readFile(getPath("/fixtures/expectedStylishResult.txt"));
        expectedPlain = readFile(getPath("/fixtures/expectedPlainResult.txt"));

        // Это чтобы JSON была не как строка, а как структура древовидная
        ObjectMapper objectMapper = new ObjectMapper();
        expectedJson = objectMapper.readTree(readFile(getPath("/fixtures/expectedJsonResult.txt")));
    }


    public static String getPath(String file) {
        return "src/test/resources/" + file;
    }

    public static String readFile(String filepath) throws Exception {
        Path path = Path.of(filepath);
        return Files.readString(path)
                .replace("\r\n", "\n")
                .replace("\r", "\n")
                .trim();
    }

    @Test
    public void testStylishJson() throws Exception {
        String actual = Differ.generate(PATH_FIRST_JSON_HARD, PATH_SECOND_JSON_HARD, "stylish");
        assertThat(actual).isEqualTo(expectedStylish);
    }

    @Test
    public void testStylishYaml() throws Exception {
        String actual = Differ.generate(PATH_FIRST_YML, PATH_SECOND_YML, "stylish");
        assertThat(actual).isEqualTo(expectedStylish);
    }

    @Test
    public void testPlainJson() throws Exception {
        String actual = Differ.generate(PATH_FIRST_JSON_HARD, PATH_SECOND_JSON_HARD, "plain");
        assertThat(actual).isEqualTo(expectedPlain);
    }

    @Test
    public void testPlainYaml() throws Exception {
        String actual = Differ.generate(PATH_FIRST_YML, PATH_SECOND_YML, "plain");
        assertThat(actual).isEqualTo(expectedPlain);
        //assertEquals(actual,expectedPlain);
    }
    @Test
    public void testJsonFormatJsonFiles() throws Exception {
        //String actual = Differ.generate(PATH_FIRST_JSON_HARD, PATH_SECOND_JSON_HARD, "json");
        //assertThat(actual).isEqualTo(expectedJson);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode actualJson = objectMapper.readTree(Differ.generate(
                PATH_FIRST_JSON_HARD, PATH_SECOND_JSON_HARD, "json"));
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testJsonFormatYamlFiles() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode actualFromYaml = objectMapper.readTree(Differ.generate(PATH_FIRST_YML, PATH_SECOND_YML, "json"));
        assertEquals(expectedJson, actualFromYaml);

    }
    @Test
    public void testFileNotFound() {
        String badPath = "unknown/directory/file.json";
        assertThatThrownBy(() -> Differ.generate(badPath, PATH_FIRST_JSON_HARD, "stylish"))
                .isInstanceOf(IOException.class);
    }

}
