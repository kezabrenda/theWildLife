package models;

public class Sighting {
    private String animal;
    private String location;
    private String rangerName;

    public Sighting(String animal, String location, String rangerName){
        this.animal=animal;
        this.location=location;
        this.rangerName=rangerName;
    }

    public String getAnimal() {
        return animal;
    }

    public String getLocation() {
        return location;
    }

    public String getRangerName() {
        return rangerName;
    }
}
