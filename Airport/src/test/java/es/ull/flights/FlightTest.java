package es.ull.flights;
import es.ull.flights.Flight;
import es.ull.passengers.Passenger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlightTest {

    @Test
    public void testValidFlightNumber() {
        assertDoesNotThrow(() -> new Flight("AB123", 100));
        assertDoesNotThrow(() -> new Flight("XY5678", 200));
    }

    @Test
    public void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> new Flight("Invalid", 150));
        assertThrows(RuntimeException.class, () -> new Flight("ABCD", 50));
    }

    @Test
    public void testAddPassenger() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger1 = new Passenger("ID001", "John Doe", "US");
        Passenger passenger2 = new Passenger("ID002", "Jane Doe", "CA");

        assertTrue(flight.addPassenger(passenger1));
        assertTrue(flight.addPassenger(passenger2));
        assertEquals(2, flight.getNumberOfPassengers());
    }

    @Test
    public void testAddPassengerExceedingSeats() {
        Flight flight = new Flight("AB123", 1);
        Passenger passenger1 = new Passenger("ID001", "John Doe", "US");
        Passenger passenger2 = new Passenger("ID002", "Jane Doe", "CA");

        assertTrue(flight.addPassenger(passenger1));
        assertThrows(RuntimeException.class, () -> flight.addPassenger(passenger2));
    }

    @Test
    public void testRemovePassenger() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger1 = new Passenger("ID001", "John Doe", "US");
        Passenger passenger2 = new Passenger("ID002", "Jane Doe", "CA");

        flight.addPassenger(passenger1);
        flight.addPassenger(passenger2);

        assertTrue(flight.removePassenger(passenger1));
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    public void testRemoveNonexistentPassenger() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger1 = new Passenger("ID001", "John Doe", "US");

        assertFalse(flight.removePassenger(passenger1));
    }
}