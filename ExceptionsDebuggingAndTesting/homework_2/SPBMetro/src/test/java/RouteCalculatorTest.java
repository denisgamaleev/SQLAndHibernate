import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.*;

public class RouteCalculatorTest extends TestCase {
    List<Station> route, route1, route2;
    RouteCalculator calculator;
    StationIndex stationIndex;
    List<Station> connections1;
    List<Station> connections2;
    Station station, station1, station2;
    Station station3, station4, station5;

    @Override
    protected void setUp() throws Exception {

        Line redLine = new Line(1, "Красная");
        Line blueLine = new Line(2, "Синяя");
        Line greenLine = new Line(3, "Зеленая");
        Station devaytkino = new Station("Девяткино",redLine);
        Station parnas = new Station("Парнас",blueLine);
        Station begovaya = new Station("Беговая",greenLine);
        Station grazhProspect = new Station("Гражданский проспект",redLine);
        Station akademicheskaya = new Station("Академическая",redLine);
        Station prospectPros = new Station("Проспект просвещения",blueLine);
        Station novokrestovskaya = new Station("Новокрестовская",greenLine);
        route = new ArrayList<>();
        route.add(devaytkino);
        route.add(grazhProspect);
        route.add(akademicheskaya);
        redLine.addStation(devaytkino);
        redLine.addStation(grazhProspect);
        redLine.addStation(akademicheskaya);
        blueLine.addStation(parnas);
        blueLine.addStation(prospectPros);
        greenLine.addStation(begovaya);
        greenLine.addStation(novokrestovskaya);
        connections1 = new ArrayList<>();
        connections1.add(devaytkino);
        connections1.add(parnas);
        connections2 = new ArrayList<>();
        connections2.add(begovaya);
        connections2.add(parnas);
        stationIndex = new StationIndex();
        stationIndex.number2line = new HashMap<>();
        stationIndex.stations = new TreeSet<>();
        stationIndex.connections = new TreeMap<>();
        stationIndex.addLine(redLine);
        stationIndex.addLine(blueLine);
        stationIndex.addLine(greenLine);
        stationIndex.addStation(devaytkino);
        stationIndex.addStation(prospectPros);
        stationIndex.addStation(akademicheskaya);
        stationIndex.addStation(parnas);
        stationIndex.addStation(grazhProspect);
        stationIndex.addStation(begovaya);
        stationIndex.addStation(novokrestovskaya);
        stationIndex.addConnection(connections1);
        stationIndex.addConnection(connections2);
        calculator = new RouteCalculator(stationIndex);
        route2 = new ArrayList<>();
        route2.add(akademicheskaya);
        route2.add(grazhProspect);
        route2.add(devaytkino);
        route2.add(parnas);
        route2.add(begovaya);
        route2.add(novokrestovskaya);
        this.station2 = akademicheskaya;
        station5 = novokrestovskaya;
        route1 = new ArrayList<>();
        route1.add(akademicheskaya);
        route1.add(grazhProspect);
        route1.add(devaytkino);
        route1.add(parnas);
        route1.add(prospectPros);
        this.station1 = akademicheskaya;
        station4 = prospectPros;
        station = devaytkino;
        this.station3 = akademicheskaya;
    }
    @Test
    public void testCalculateDurationOnTheLine() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 5;
        assertEquals(expected,actual);
    }
    @Test
    public void testCalculateDurationWithOneConnections() {
        double actual = RouteCalculator.calculateDuration(route1);
        double expected = 11;
        assertEquals(expected, actual);
    }
    @Test
    public void testCalculateDurationWithTwoConnections() {
        double actual = RouteCalculator.calculateDuration(route2);
        double expected = 14.5;
        assertEquals(expected, actual);
    }
    @Test
    public void testGetShortestRouteWithTwoConnections() {
        List<Station> actual = calculator.getShortestRoute(station2, station5);
        List<Station> expected = route2;
        assertEquals(expected,actual);
    }
    @Test
    public void testGetShortestRouteWithOneConnections() {
        List<Station> actual = calculator.getShortestRoute(station1, station4);
        List<Station> expected = route1;
        assertEquals(expected,actual);
    }
    @Test
    public void testGetShortestRouteOnTheLine() {
        List<Station> actual = calculator.getShortestRoute(station, station3);
        List<Station> expected = route;
        assertEquals(expected,actual);
    }
    @Override
    protected void tearDown() throws Exception {

    }
}
