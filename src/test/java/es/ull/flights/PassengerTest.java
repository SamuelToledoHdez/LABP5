package es.ull.flights;

import es.ull.flights.Flight;
import es.ull.passengers.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PassengerTest {

    @BeforeEach

    @Test
    public void testValidPassengerCreation() {
        assertDoesNotThrow(() -> new Passenger("ID001", "John Doe", "US"));
        assertDoesNotThrow(() -> new Passenger("ID002", "Jane Doe", "CA"));
    }

    @Test
    public void testInvalidCountryCode() {
        assertThrows(RuntimeException.class, () -> new Passenger("ID001", "John Doe", "XYZ"));
        assertThrows(RuntimeException.class, () -> new Passenger("ID002", "Jane Doe", "INVALID"));
    }

    @Test
    public void testJoinFlight() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("ID001", "John Doe", "US");

        passenger.joinFlight(flight);

        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    public void testJoinFlightWithPreviousFlight() {
        Flight flight1 = new Flight("AB123", 2);
        Flight flight2 = new Flight("XY567", 3);
        Passenger passenger = new Passenger("ID001", "John Doe", "US");

        passenger.joinFlight(flight1);
        passenger.joinFlight(flight2);

        assertEquals(flight2, passenger.getFlight());
        assertEquals(1, flight2.getNumberOfPassengers());
        assertEquals(0, flight1.getNumberOfPassengers());
    }


}