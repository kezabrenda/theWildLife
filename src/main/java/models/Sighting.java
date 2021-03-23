package models;

import interfaces.SightingsInterface;

import java.sql.Timestamp;

public class Sighting implements SightingsInterface {
    private int sightingId;
    private int animalId;
    private int locationId;
    private int rangerId;
    private Timestamp date;

    public Sighting(int sightingId, int animalId, int locationId, int rangerId){
        this.sightingId = sightingId;
        this.animalId = animalId;
        this.locationId = locationId;
        this.rangerId = rangerId;
    }

    public int getSightingId() {
        return sightingId;
    }

    public int getAnimalId() {
        return animalId;
    }

    public int getLocationId() {
        return locationId;
    }

    public int getRangerId() {
        return rangerId;
    }

    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }
}
