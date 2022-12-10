import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class ParseCsv {

    List<File> filesCsv = new ArrayList<>();
    List<File> filesJson = new ArrayList<>();

    FileSearch fileSearch = new FileSearch();
    HashMap<String, String> stationMap = new HashMap<>();
    String line = "";
    String cvsSplitBy = ",";

    public HashMap<String, String> getStation() {
        fileSearch.searchFile(new File("FilesAndNetwork/DataCollector/data")
                , filesCsv, filesJson);

        for (File file : filesCsv) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                while ((line = br.readLine()) != null) {
                    String[] country = line.split(cvsSplitBy);
                    stationMap.put(country[0], country[1]);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stationMap;
    }
}
