import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class ParseJson {
    HashMap<String, String> stationMap = new HashMap<>();
    List<File> filesJson = new ArrayList<>();
    List<File> filesCsv = new ArrayList<>();
    FileSearch fileSearch = new FileSearch();

    public HashMap<String, String> getStation() throws IOException, ParseException {
        fileSearch.searchFile(new File("FilesAndNetwork/DataCollector/data")
                , filesCsv, filesJson);
        for (File file : filesJson) {
            FileReader reader = new FileReader(file);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray lang = (JSONArray) jsonObject.get("station_name");
            for (Object o : lang) {
                JSONObject innerObj = (JSONObject) o;
                stationMap.put((String) innerObj.get("station_name"),
                        (String) innerObj.get("depth"));
            }
        }
        return stationMap;
    }
}
