package models;

import interfaces.AnimalInterface;
import org.sql2o.Connection;

import java.util.List;

public class Animal implements AnimalInterface {
    private int animalId;
    private int animalAge;
    private String animalName;

    public Animal(int animalId, int animalAge, String animalName){
        this.animalId= animalId;
        this.animalAge= animalAge;
        this.animalName= animalName;
    }

    public int getAnimalId() {
        return animalId;
    }

    public int getAnimalAge() {
        return animalAge;
    }

    public String getAnimalName() {
        return animalName;
    }

    @Override
    public boolean equals(Object otherAnimal){
        if (!(otherAnimal instanceof Animal)) {
            return false;
        } else {
            Animal newAnimal = (Animal) otherAnimal;
            return this.getAnimalName().equals(newAnimal.getAnimalName());
        }
    }

    @Override
    public void save() {
        String sql = "INSERT INTO animals (animalName, animalAge) VALUES (:animalName, :animalAge)";
        try(Connection con = DB.sql2o.open()) {
            this.animalId = (int) con.createQuery(sql, true)
                    .addParameter("AnimalName", animalName)
                    .addParameter("animalAge", animalAge)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Animal> all() {
        String sql = "SELECT * FROM animals;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animal.class);
        }
    }

    public static Animal find(int animalId) {
        String sql = "SELECT * FROM animals WHERE animalId = :animalId;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("animalId", animalId)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
        }
    }

    @Override
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM animals WHERE animalId = :animalId;";
            con.createQuery(sql)
                    .addParameter("animalId", this.animalId)
                    .executeUpdate();
        }
    }
}
