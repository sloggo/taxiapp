import org.taxiapp.classes.Map;
import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Taxi;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

public class MapUserIntegrationTest {
    // main intent of this test is to ensure the locations holding the users and taxis are updated in every class when updated,
    // maintain consistency between the classes.
    @Test
    public void addCustomerToMapTest(){
        Map map = new Map(5);
        Customer customer = new Customer("customerTest", map.getLocation(1,1));

        assertTrue(false); // ensure location is the same point in memory
        assertEquals(map.getLocation(1,1).getCustomers().get(0), customer); // ensure customer is the same point in memory
    }

    @Test
    public void addTaxiToMapTest(){
        Map map = new Map(5);
        Taxi taxi = new Taxi("taxiTest", map.getLocation(1,1));

        assertEquals(map.getLocation(1,1), taxi.getLocation());
        assertEquals(map.getLocation(1,1).getTaxis().get(0), taxi);
    }

    @Test
    public void addBothToMapTest(){
        Map map = new Map(5);
        Customer customer = new Customer("customerTest", map.getLocation(1,1));
        Taxi taxi = new Taxi("taxiTest", map.getLocation(1,1));

        assertEquals(map.getLocation(1,1), taxi.getLocation());
        assertEquals(map.getLocation(1,1).getTaxis().get(0), taxi);
        assertEquals(map.getLocation(1,1), customer.getLocation());
        assertEquals(map.getLocation(1,1).getCustomers().get(0), customer);
    }
}
