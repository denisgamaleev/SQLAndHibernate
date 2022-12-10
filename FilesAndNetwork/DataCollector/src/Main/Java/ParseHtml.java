import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParseHtml {
    List<StationHtml> stationHtml = new ArrayList<>();
    int j = 0;
    int k = 0;

    public List<StationHtml> getNameNumber() {
        int t = 0;
        int l = 0;
        ParseHtml parseHtml = new ParseHtml();
        List<String> listStations = parseHtml.getStations();
        List<String> listStationsCopy = new ArrayList<>();
        Collections.addAll(listStationsCopy, listStations.get(0).split("\\."));
        List<String> listNumberStation = parseHtml.getNumbers();
        List<String> listNameStation = parseHtml.getNameStations();
        List<StationHtml> listNumberName = new ArrayList<>();

        for (int i = 0; i < listNameStation.size(); i++) {
            if ((listStationsCopy.get(i).contains("Линия") ||
                    listStationsCopy.get(i).contains("линия") ||
                    listStationsCopy.get(i).contains("Центральное") ||
                    listStationsCopy.get(i).contains("МЦД")) && t >= 1) {
                listNumberName.add(new StationHtml(
                        listNumberStation.get(++l),
                        listNameStation.get(i)));
            } else {
                t++;
                listNumberName.add(new StationHtml(
                        listNumberStation.get(l),
                        listNameStation.get(i)));
            }
        }
        return listNumberName;
    }

    public List<StationHtml> getStation() {

        ParseHtml parseHtml = new ParseHtml();
        List<String> listStations = parseHtml.getStations();
        List<String> listStationsCopy = new ArrayList<>();
        Collections.addAll(listStationsCopy, listStations.get(0).split("\\."));

        List<String> listNumberStation = parseHtml.getNumbers();
        List<String> listLineStation = parseHtml.getLines();
        List<String> listNameStation = parseHtml.getNameStations();
        List<Boolean> listConnection = parseHtml.getConnection();

        for (int i = 0; i < listNameStation.size(); i++) {
            if ((listStationsCopy.get(i).contains("Линия") ||
                    listStationsCopy.get(i).contains("линия") ||
                    listStationsCopy.get(i).contains("Центральное") ||
                    listStationsCopy.get(i).contains("МЦД")) && k >= 1) {
                stationHtml.add(new StationHtml(
                        listNumberStation.get(j),
                        listLineStation.get(++j),
                        listNameStation.get(i),
                        listConnection.get(i)));
            } else {
                k++;
                stationHtml.add(new StationHtml(
                        listNumberStation.get(j),
                        listLineStation.get(j),
                        listNameStation.get(i),
                        listConnection.get(i)));

            }
        }
        return stationHtml;

    }

    Document doc;

    {
        try {
            doc = Jsoup.connect("https://skillbox-java.github.io/").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Elements lines = doc.select(".s-depend-control-single");
    Elements stationsName = doc.select(".single-station");
    Elements stations = doc.select(".t-text-simple");
    Elements numbers = doc.select(".js-metro-line");


    public List<String> getLines() {
        return lines.stream()
                .map(anchor ->
                        anchor.select("span").text()
                ).toList();
    }

    public List<String> getNameStations() {
        return stationsName.stream()
                .map(anchor ->
                        anchor.select("span.name").text()
                ).toList();
    }

    public List<Boolean> getConnection() {
        return stationsName.stream()
                .map(anchor ->
                        !anchor.select("span.t-icon-metroln").attr("title").equals("")).toList();
    }

    public List<String> getStations() {
        return stations.stream().map(anchor ->
                anchor.select("span").text()
        ).toList();
    }

    public List<String> getNumbers() {
        return numbers.stream().map(anchor ->
                anchor.select("span").attr("data-line")
        ).toList();
    }
}


