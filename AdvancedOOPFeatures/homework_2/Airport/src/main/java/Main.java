import com.skillbox.airport.Aircraft;
import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static final long HOUR = 3600 * 1000;

    public static void main(String[] args) {
        Airport airport = Airport.getInstance();
        findPlanesLeavingInTheNextTwoHours(airport);
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        Date oldDate = new Date();
        Date newDate = new Date(oldDate.getTime() + 2 * HOUR);
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        return airport.getTerminals().stream()
                .map(Terminal::getFlights)
                .flatMap(List::stream)
                .filter(flight -> flight.getType() == Flight.Type.DEPARTURE).filter(flight -> {
                    if (flight.getDate().before(newDate) && flight.getDate().after(oldDate) || flight.getDate().equals(oldDate)) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());
    }
}
