package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class LocationsTest {
    @Rule
    public DatabaseRule databaseRule=new DatabaseRule();

    @Test
    public void Locations_instantiatesCorrectly_true() {
        Locations location=setUpNewLocation();
        assertEquals(true,location instanceof Locations);
    }

    private Locations setUpNewLocation() {
        return new Locations("Area 5");
    }

   /* @Test
    public void save() {
        Locations location=setUpNewLocation();
        Locations newLocation=new Locations(" ");
        try {
            location.save();
            assertTrue(Locations.all().get(0).equals(location));
            newLocation.save();

        }catch (IllegalArgumentException e){
            System.out.println(e);
        }
    }*/

    @Test
    public void delete() {
        Locations location=setUpNewLocation();
        Locations newLocation=new Locations("Area 5");
        location.save();
        newLocation.save();
        location.delete();
        assertEquals(null,Locations.find(location.getId()));
    }

   /* @Test
    public void getLocationSightings() {
        Locations location = setUpNewLocation();
        try {
            location.save();
            Sightings sighting=new Sightings(location.getId(),1,1);
            Sightings otherSighting=new Sightings(location.getId(),1,1);
            sighting.save();
            otherSighting.save();
            assertEquals(location.getLocationSightings().get(0),sighting);
            assertEquals(location.getLocationSightings().get(1),otherSighting);
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }
    }*/
}