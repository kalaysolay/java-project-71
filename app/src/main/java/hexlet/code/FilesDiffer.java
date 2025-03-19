package hexlet.code;

import java.io.IOException;
import java.util.Map;

public class FilesDiffer {

    public static String diff(String filePath1, String filePath2) throws IOException {
        String fileContent1 = getContent(filePath1);
        String fileContent2 = getContent(filePath2);

        String result = Parcer.parce(fileContent1, fileContent2);

        return result;
    }
    public static String parce(String fileContent1, String fileContent2) throws JsonProcessingException {
        Map<String, Object> map1 = getData(fileContent1);
        Map<String, Object> map2 = getData(fileContent2);

        Map<String, Object> resultMap = getDifference(map1, map2);
        String result = getDifferenceString(resultMap);

        return result;
    }
    public static Map<String, Object> getContent(String fileContent) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> fileContentMap = om.readValue(fileContent, new TypeReference<>() { });

        return fileContentMap;
    }

}
