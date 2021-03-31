package models;

import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;

public class Locations {
    private int id;
    private String locationName;

    public Locations(String locationName) {
        this.locationName = locationName;
    }

    public int getId() {
        return id;
    }

    public String getLocationName() {
        return locationName;
    }

    @Override
    public boolean equals(Object otherLocation){
        if (!(otherLocation instanceof Locations)) {
            return false;
        } else {
            Locations newLocation = (Locations) otherLocation;
            return this.getLocationName().equals(newLocation.getLocationName());
        }
    }

    public void save(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO locations (locationName) VALUES(:locationName)";
            this.id = (int) con.createQuery(sql,true)
                    .addParameter("locationName", this.locationName)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Locations> all(){
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM locations")
                    .executeAndFetch(Locations.class);
        }
    }
    public static Locations find(int id){
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery("SELECT * FROM locations WHERE id=:id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Locations.class);
        }
    }
    public void delete() {
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM locations WHERE id=:id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    public List<Sightings> getLocationSightings() {
        try (Connection con=DB.sql2o.open()){
            String sql="SELECT sighting_id FROM locations_sightings WHERE location_id=:location_id";
            List<Integer> sightings_ids=con.createQuery(sql)
                    .addParameter("location_id",this.getId())
                    .executeAndFetch(Integer.class);
            List<Sightings> sightings=new ArrayList<Sightings>();

            for(Integer sighting_id:sightings_ids){
                String sightingsQuery="SELECT * FROM sightings WHERE id=:sighting_id";
                Sightings sighting=con.createQuery(sightingsQuery)
                        .addParameter("sighting_id",sighting_id)
                        .executeAndFetchFirst(Sightings.class);
                sightings.add(sighting);
            }
            if(sightings.size()==0){
                throw new IllegalArgumentException("Location has no sighting");
            }
            else {return sightings;}


        }
    }
}
