package org.taxiapp;

import org.junit.Test;
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
        Customer user = new Customer("testCustomer", map.getLocation(10,10));
        Taxi taxiClosest = new Taxi("testTaxiOnTile", map.getLocation(10,10));
        Taxi taxiFurther = new Taxi("testTaxiOnTile", map.getLocation(0,0));

        RideRequestSystem request = new RideRequestSystem(map, user);
        Taxi closest = request.closestTaxi();

        assertEquals(closest.getId(), taxiClosest.getId());
    }

    @Test
    public void testFirstIteration(){
        Map map = new Map(20);
        Customer user = new Customer("testCustomer", map.getLocation(10,10));
        Taxi taxiClosest = new Taxi("testTaxiOnTile", map.getLocation(9,10));
        Taxi taxiFurther = new Taxi("testTaxiOnTile", map.getLocation(0,0));

        RideRequestSystem request = new RideRequestSystem(map, user);
        Taxi closest = request.closestTaxi();
        System.out.println(closest);

        assertEquals(closest.getId(), taxiClosest.getId());
    }

    @Test
    public void testNoneFound(){
        Map map = new Map(20);
        Customer user = new Customer("testCustomer", map.getLocation(10,10));

        RideRequestSystem request = new RideRequestSystem(map, user);
        Taxi closest = request.closestTaxi();

        assertNull(closest);
    }
}
