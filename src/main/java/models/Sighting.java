package models;

import interfaces.SightingsInterface;
import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;

public class Sighting implements SightingsInterface {
    private int sightingId;
    private int animalId;
    private int locationId;
    private int rangerId;
    private String animalSituation;
    private Timestamp date;

    public Sighting(int sightingId, int animalId, int locationId, int rangerId, String animalSituation){
        this.sightingId = sightingId;
        this.animalId = animalId;
        this.locationId = locationId;
        this.rangerId = rangerId;
        this.animalSituation = animalSituation;
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

    public String getAnimalSituation() {
        return animalSituation;
    }

    public String getLocationName() {
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT locationName FROM locations WHERE id=:id")
                    .addParameter("id", this.locationId)
                    .executeAndFetchFirst(String.class);
        }
    }

    public String getAnimalName() {
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT animalName FROM animals WHERE id=:id")
                    .addParameter("id", this.animalId)
                    .executeAndFetchFirst(String.class);
        }
    }


    public String getRangerName() {
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT rangerName FROM rangers WHERE id=:id")
                    .addParameter("id", this.rangerId)
                    .executeAndFetchFirst(String.class);
        }
    }


    @Override
    public void save() {
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO sightings (animalId, rangerId, locationId, date, animalSituation) VALUES (:animalId," +
                    " " +
                    ":rangerId, :locationId, :date, :animalSituation)";
            this.sightingId = (int) con.createQuery(sql,true)
                    .addParameter("animalId", this.animalId)
                    .addParameter("rangerId", this.rangerId)
                    .addParameter("locationId", this.locationId)
                    .addParameter("date", this.date)
                    .addParameter("animalSituation", this.animalSituation)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }


    public static List<Sighting> all(){
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM sightings")
                    .executeAndFetch(Sighting.class);
        }
    }

    public static List<Sighting> allAnimals() {
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM sightings WHERE animalSituation = :animalSituation")
                    .addParameter("animalSituation", Animal.ANIMAL_SITUATION)
                    .executeAndFetch(Sighting.class);
        }
    }
    public static Sighting find(int id){
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM sightings WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sighting.class);
        }
    }

    @Override
    public boolean equals (Object otherSighting){
        if (!(otherSighting instanceof Sighting)){
            return false;
        }else{
            Sighting sighting =(Sighting) otherSighting;
            return this.getSightingId() == sighting.getSightingId()&&
                    this.getAnimalId()==sighting.getAnimalId() &&
                    this.getLocationId()==sighting.getLocationId()&&
                    this.getRangerId()==sighting.getRangerId();
        }
    }

    @Override
    public void delete() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM sightings WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", sightingId)
                    .executeUpdate();
        }
    }

}
