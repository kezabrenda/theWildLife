package models;

import interfaces.AnimalInterface;
import org.sql2o.Connection;

import java.util.List;

public class Animal implements AnimalInterface {
    private int animalId;
    private int animalAge;
    private String animalName;
    public  String animalSituation;
    public static final String ANIMAL_SITUATION = "animal";

    public Animal(int animalAge, String animalName){
        this.animalAge= animalAge;
        this.animalName= animalName;
        this.animalSituation = ANIMAL_SITUATION;
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
    public static String getAnimalSituation() {
        return ANIMAL_SITUATION;
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
        String sql = "INSERT INTO animals (name, age, animalSituation) VALUES (:name, :age, :animalSituation)";
        try(Connection con = DB.sql2o.open()) {
            this.animalId = (int) con.createQuery(sql, true)
                    .addParameter("name", animalName)
                    .addParameter("age", animalAge)
                    .addParameter("animalSituation", animalSituation)
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
        String sql = "SELECT * FROM animals WHERE id = :id;";
        try (Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", animalId)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
        }
    }

    @Override
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM animals WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", this.animalId)
                    .executeUpdate();
        }
    }
}
