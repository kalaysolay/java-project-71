package hexlet.code;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilesDifferTest {

    private static final String PATH_FIRST_JSON = "./src/test/resources/file1_hard.json";
    private static final String PATH_SECOND_JSON = "./src/test/resources/file2_hard.json";

    private static final String PATH_FIRST_YML = "./src/test/resources/file1.yml";
    private static final String PATH_SECOND_YML = "./src/test/resources/file2.yml";

    private static String expectedStylish;
    private static String expectedPlain;
    private static JsonNode expectedJson;

    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedStylish = readFile(getPath("/fixtures/expectedStylishResult.txt"));
        expectedPlain = readFile(getPath("/fixtures/expectedPlainResult.txt"));

        ObjectMapper objectMapper = new ObjectMapper();
        expectedJson = objectMapper.readTree(readFile(getPath("/fixtures/expectedJsonResult.txt")));
    }

    private static String getPath(String file) {
        return "src/test/resources/" + file;
    }

    private static String readFile(String filepath) throws Exception {
        Path path = Path.of(filepath);
        return Files.readString(path)
                .replace("\r\n", "\n")
                .replace("\r", "\n")
                .trim();
    }

    // JSON input tests
    @Test
    public void testJsonInputStylishFormat() throws Exception {
        String actual = Differ.generate(PATH_FIRST_JSON, PATH_SECOND_JSON, "stylish");
        assertThat(actual).isEqualTo(expectedStylish);
    }

    @Test
    public void testJsonInputPlainFormat() throws Exception {
        String actual = Differ.generate(PATH_FIRST_JSON, PATH_SECOND_JSON, "plain");
        assertThat(actual).isEqualTo(expectedPlain);
    }

    @Test
    public void testJsonInputJsonFormat() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode actual = objectMapper.readTree(Differ.generate(PATH_FIRST_JSON, PATH_SECOND_JSON, "json"));
        assertEquals(expectedJson, actual);
    }

    @Test
    public void testJsonInputDefaultFormat() throws Exception {
        String actual = Differ.generate(PATH_FIRST_JSON, PATH_SECOND_JSON);
        assertThat(actual).isEqualTo(expectedStylish);
    }

    // YAML input tests
    @Test
    public void testYamlInputStylishFormat() throws Exception {
        String actual = Differ.generate(PATH_FIRST_YML, PATH_SECOND_YML, "stylish");
        assertThat(actual).isEqualTo(expectedStylish);
    }

    @Test
    public void testYamlInputPlainFormat() throws Exception {
        String actual = Differ.generate(PATH_FIRST_YML, PATH_SECOND_YML, "plain");
        assertThat(actual).isEqualTo(expectedPlain);
    }

    @Test
    public void testYamlInputJsonFormat() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode actual = objectMapper.readTree(Differ.generate(PATH_FIRST_YML, PATH_SECOND_YML, "json"));
        assertEquals(expectedJson, actual);
    }

    @Test
    public void testYamlInputDefaultFormat() throws Exception {
        String actual = Differ.generate(PATH_FIRST_YML, PATH_SECOND_YML);
        assertThat(actual).isEqualTo(expectedStylish);
    }

    @Test
    public void testFileNotFound() {
        String badPath = "src/test/resources/missing_file.json";

        assertThatThrownBy(() -> Differ.generate(badPath, PATH_SECOND_JSON, "stylish"))
                .isInstanceOf(NoSuchFileException.class);


    }
}
