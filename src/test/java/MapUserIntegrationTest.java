import org.taxiapp.classes.Map;
import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Taxi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MapUserIntegrationTest {
    // main intent of this test is to ensure the locations holding the users and taxis are updated in every class when updated,
    // maintain consistency between the classes.
    @Test
    public void addCustomerToMapTest(){
        Map map = new Map(5);
        Customer customer = new Customer("customerTest", map, 1,1);

        assertEquals(map.getLocation(1,1), customer.getLocation()); // ensure location is the same point in memory
        assertEquals(map.getLocation(1,1).getCustomers().get(0), customer); // ensure customer is the same point in memory
    }

    @Test
    public void addTaxiToMapTest(){
        Map map = new Map(5);
        Taxi taxi = new Taxi("taxiTest", map, 1,1);

        assertEquals(map.getLocation(1,1), taxi.getLocation());
        assertEquals(map.getLocation(1,1).getTaxis().get(0), taxi);
    }

    @Test
    public void addBothToMapTest(){
        Map map = new Map(5);
        Customer customer = new Customer("customerTest", map, 1,1);
        Taxi taxi = new Taxi("taxiTest", map, 1,1);

        assertEquals(map.getLocation(1,1), taxi.getLocation());
        assertEquals(map.getLocation(1,1).getTaxis().get(0), taxi);
        assertEquals(map.getLocation(1,1), customer.getLocation());
        assertEquals(map.getLocation(1,1).getCustomers().get(0), customer);
    }

    // ensures map is logging taxis correctly
    @Test
    public void mapTaxiLoggingTest(){
        Map map = new Map(5);
        Customer customer = new Customer("customerTest", map, 1,1);
        Taxi taxiOne = new Taxi("taxiOne", map, 0,3);
        Taxi taxiTwo = new Taxi("taxiTwo", map, 4,4);
        Taxi taxiThree = new Taxi("taxiThree", map, 2,4);
        Taxi[] listOfTaxis = {taxiOne,taxiTwo,taxiThree};

        Taxi[] listOfTaxisMap = map.logCurrentTaxis();

        assertArrayEquals(listOfTaxis, listOfTaxisMap);
    }

    @Test
    public void mapCustomerLoggingTest(){
        Map map = new Map(5);
        Taxi taxi = new Taxi("taxiTest", map, 1,1);
        Customer cOne = new Customer("cOne", map, 0,3);
        Customer cTwo = new Customer("cTwo", map, 4,4);
        Customer cThree = new Customer("cThree", map, 2,4);
        Customer[] listOfCustomers = {cOne,cTwo,cThree};

        Customer[] listOfCustomersMap = map.logCurrentCustomers();

        assertArrayEquals(listOfCustomers, listOfCustomersMap);
    }
}
