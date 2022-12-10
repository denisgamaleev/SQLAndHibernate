import java.io.*;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class AddJson {
    JSONObject obj = new JSONObject();
    ParseJson parseJson = new ParseJson();
    ParseCsv parseCsv = new ParseCsv();
    ParseHtml parseHtml = new ParseHtml();

    List<StationHtml> listHtml = parseHtml.getStation();
    List<StationHtml> listNumbersName = parseHtml.getNameNumber();
    List<String> listNumbers = parseHtml.getNumbers();
    List<String> listLine = parseHtml.getLines();
    List<String> listNameStations = parseHtml.getNameStations();
    List<String> htmlNames = listHtml.stream().map(StationHtml::getName).toList();
    List<String> htmlLines = listHtml.stream().map(StationHtml::getLine).toList();
    List<String> htmlNumbers = listNumbersName.stream().map(StationHtml::getNumber).toList();
    List<Boolean> htmlConnections = listHtml.stream().map(StationHtml::getConnection).toList();
    HashMap<String, String> mapJson = parseJson.getStation();
    HashMap<String, String> mapCsv = parseCsv.getStation();

    Gson gson;
    String filePathStations;
    String filePathLines;


    public AddJson() throws IOException, ParseException {
        this.filePathStations =
                "C:/Users/gamal/dpo_java_basics/FilesAndNetwork/DataCollector/Json/stations.json";
        this.filePathLines =
                "C:/Users/gamal/dpo_java_basics/FilesAndNetwork/DataCollector/Json/map.json";
    }


    public void addStations() throws IOException, ParseException {
        AddJson addJSON = new AddJson();
        ArrayList<Stations> stations = new ArrayList<>();

        for (int i = 0; i < htmlNames.size(); i++) {
            stations.add(new Stations(
                    htmlNames.get(i),
                    htmlLines.get(i),
                    mapCsv.get(htmlNames.get(i)),
                    mapJson.get(htmlNames.get(i)),
                    htmlConnections.get(i)));
        }
        obj.put("stations", stations);
        addJSON.jsonWriter(obj, addJSON.filePathStations);
    }
    public void addMap() throws IOException, ParseException {
        List<Lines> listLines = new ArrayList<>();
        AddJson addJSON = new AddJson();
        JSONObject obj = new JSONObject();
        JSONObject stationsLines = new JSONObject();
        TreeMap<String, JSONArray> map = new TreeMap<>(stationsLines);

        for (int i = 0; i < listLine.size(); i++) {
            JSONArray jsonArray = new JSONArray();
            for (int j = 0; j < listNameStations.size(); j++) {
                if (listNumbers.get(i).equalsIgnoreCase(htmlNumbers.get(j))) {
                    jsonArray.add(listNameStations.get(j));
                }
            }
            map.put(listNumbers.get(i), jsonArray);
        }

        for (int i = 0; i < listNumbers.size(); i++) {
            listLines.add(new Lines(listNumbers.get(i), listLine.get(i)));
        }

        obj.put("stations", map);
        obj.put("lines", listLines);
        addJSON.jsonWriter(obj, addJSON.filePathLines);
    }


    public void jsonWriter(JSONObject stations, String filePath) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(stations, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


