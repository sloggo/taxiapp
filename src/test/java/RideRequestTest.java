import org.junit.jupiter.api.Test;
import org.taxiapp.classes.Map;
import org.taxiapp.classes.RideRequestSystem;
import org.taxiapp.classes.users.Customer;
import org.taxiapp.classes.users.Taxi;
import static org.junit.Assert.*;

public class RideRequestTest{
    // tests if taxi is found in immediate tile
    @Test
    public void testImmediateVicinity(){
        Map map = new Map(20);
        Customer user = new Customer("testCustomer", map,10,10);
        Taxi taxiClosest = new Taxi("testTaxiOnTile", map,10,10);
        Taxi taxiFurther = new Taxi("testTaxiOnTileFurther", map,0,0);

        RideRequestSystem request = new RideRequestSystem(map, user);
        Taxi closest = request.closestTaxi(20);

        assertEquals(closest.getId(), taxiClosest.getId());
    }
    // test all corners and sides to see if the radius search returns the correct taxi
    @Test
    public void testLeftSide(){
        Map map = new Map(20);
        Customer user = new Customer("testCustomer", map,10,10);
        Taxi taxiClosest = new Taxi("testTaxiOnTile", map,9,10);
        Taxi taxiFurther = new Taxi("testTaxiOnTileFurther", map,0,0);

        RideRequestSystem request = new RideRequestSystem(map, user);
        Taxi closest = request.closestTaxi(20);

        assertEquals(closest.getId(), taxiClosest.getId());
    }
    @Test
    public void testRightSide(){
        Map map = new Map(20);
        Customer user = new Customer("testCustomer", map,10,10);
        Taxi taxiClosest = new Taxi("testTaxiOnTile", map,11,10);
        Taxi taxiFurther = new Taxi("testTaxiOnTileFurther", map,0,0);
        map.printMap();

        RideRequestSystem request = new RideRequestSystem(map, user);
        Taxi closest = request.closestTaxi(20);

        assertEquals(closest.getId(), taxiClosest.getId());
    }
    @Test
    public void testTopLeftCorner(){
        Map map = new Map(20);
        Customer user = new Customer("testCustomer", map,10,10);
        Taxi taxiClosest = new Taxi("testTaxiOnTile", map,8,8);
        Taxi taxiFurther = new Taxi("testTaxiOnTileFurther", map,0,0);
        map.printMap();

        RideRequestSystem request = new RideRequestSystem(map, user);
        Taxi closest = request.closestTaxi(20);

        assertEquals(closest.getId(), taxiClosest.getId());
    }

    @Test
    public void testTopRightCorner(){
        Map map = new Map(20);
        Customer user = new Customer("testCustomer", map,10,10);
        Taxi taxiClosest = new Taxi("testTaxiOnTile", map,11,9);
        Taxi taxiFurther = new Taxi("testTaxiOnTileFurther", map,0,0);
        map.printMap();

        RideRequestSystem request = new RideRequestSystem(map, user);
        Taxi closest = request.closestTaxi(20);

        assertEquals(closest.getId(), taxiClosest.getId());
    }

    @Test
    public void testBottomLeftCorner(){
        Map map = new Map(20);
        Customer user = new Customer("testCustomer", map,10,10);
        Taxi taxiClosest = new Taxi("testTaxiOnTile", map,9,11);
        Taxi taxiFurther = new Taxi("testTaxiOnTileFurther", map,0,0);
        map.printMap();

        RideRequestSystem request = new RideRequestSystem(map, user);
        Taxi closest = request.closestTaxi(20);

        assertEquals(closest.getId(), taxiClosest.getId());
    }
    @Test
    public void testBottomRightCorner(){
        Map map = new Map(20);
        Customer user = new Customer("testCustomer", map,10,10);
        Taxi taxiClosest = new Taxi("testTaxiOnTile", map,11,11);
        Taxi taxiFurther = new Taxi("testTaxiOnTileFurther", map,0,0);
        map.printMap();

        RideRequestSystem request = new RideRequestSystem(map, user);
        Taxi closest = request.closestTaxi(20);

        assertEquals(closest.getId(), taxiClosest.getId());
    }
    // test if the radius search returns the correct value when no taxis are found
    @Test
    public void testNoneFound(){
        Map map = new Map(20);
        Customer user = new Customer("testCustomer", map,10,10);

        RideRequestSystem request = new RideRequestSystem(map, user);
        Taxi closest = request.closestTaxi(20);

        assertNull(closest);
    }
    // test if the radius search works with the 0 x and y values
    @Test
    public void testZeroValues(){
        Map map = new Map(20);
        Customer user = new Customer("testCustomer", map,4,4);
        Taxi taxiFurther = new Taxi("testTaxiOnTile", map, 0,0);
        map.printMap();

        RideRequestSystem request = new RideRequestSystem(map, user);
        Taxi closest = request.closestTaxi(20);

        assertEquals(closest.getId(), taxiFurther.getId());
    }
    // test if the radius search still works when a part of the radius falls outside the range of the map
    @Test
    public void testFallOffMap(){
        Map map = new Map(20);
        Customer user = new Customer("testCustomer", map,2,10);
        Taxi taxiFurther = new Taxi("testTaxiOnTile", map, 10,8);
        map.printMap();

        RideRequestSystem request = new RideRequestSystem(map, user);
        Taxi closest = request.closestTaxi(20);

        assertEquals(closest.getId(), taxiFurther.getId());
    }

    @Test
    public void testOppositeCorners(){
        Map map = new Map(20);
        Customer user = new Customer("testCustomer", map,0,0);
        Taxi taxiFurther = new Taxi("testTaxiOnTile", map, 19,19);
        map.printMap();

        RideRequestSystem request = new RideRequestSystem(map, user);
        Taxi closest = request.closestTaxi(20);

        assertEquals(closest.getId(), taxiFurther.getId());
    }

    @Test
    public void testUpperBoundIndex(){
        Map map = new Map(20);
        Customer user = new Customer("testCustomer", map,18,19);
        Taxi taxiFurther = new Taxi("testTaxiOnTile", map, 19,19);
        map.printMap();

        RideRequestSystem request = new RideRequestSystem(map, user);
        Taxi closest = request.closestTaxi(20);

        assertEquals(closest.getId(), taxiFurther.getId());
    }
}
