package org.taxiapp.interfaces;
import org.taxiapp.classes.LinkedList;
import org.taxiapp.classes.Location;
import org.taxiapp.classes.Map;
import org.taxiapp.classes.users.Taxi;

import java.util.List;

public interface VehicleHiringTest {
        // Inserts the vehicle with registration number reg to the map at location loc if it has not been already added to map.
        // It should return false if the vehicle is not registered or is already on map
    public abstract boolean testAddToMap(Map map, String reg, Location loc);

        // Update the location of the vehicle with the specified reg number to location loc if vehicle exists and return true.
        //Return false if vehicle not registered or has not been added to the map
    public abstract boolean testMoveVehicle(Map map, Taxi taxi, Location loc);

        // Remove the vehicle with the specified reg number from the map if it is registered and return true.
        // If vehicle is not registered or is not on map the method returns false.
    public abstract boolean testRemoveVehicle(Map map, Taxi taxi);

        // Return the location of vehicle specified by the reg number if it is registered and added to the map, null otherwise.
    public abstract Location testGetVehicleLoc(Map map, Taxi taxi);

        // Return a list of all vehicles registration numbers located within a square of side 2*r centered at location loc (inclusive
        //of the boundaries).
    public abstract LinkedList<Taxi> testGetVehiclesInRange(Map map, Location loc, int r);
}
