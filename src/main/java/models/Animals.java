package models;

import org.sql2o.Connection;
import org.sql2o.Sql2oException;

import java.util.List;
import java.util.Objects;

public class Animals implements DatabaseManagement {
    public int id;
    public String name;
    public String type;
    public String health;
    public String age;
    public static final String ANIMAL_TYPE = "secured";

    public Animals(String name, String  type) {
        this.name = name;
        this.health = "";
        this.age ="";
        this.type = ANIMAL_TYPE;
    }

    public static Animals find(int animal_id) {
        try (Connection con=DB.sql2o.open()){
            String sql= "SELECT * FROM animals WHERE id=:id";
            Animals animal=  con.createQuery(sql)
                    .addParameter("id",animal_id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animals.class);
            return animal;
        }
    }

    public String getName() {
        return name;
    }

    public String getHealth() {
        return health;
    }

    public String getType() {
        return type;
    }

    public String getAge() {
        return age;
    }

    @Override
    public void save() {
        if(this.name.equals("")||this.type.equals("")||this.name.equals(null)||this.type.equals(null)){
            throw new IllegalArgumentException("Fields cannot be empty");
        }
        try (Connection con=DB.sql2o.open()){
            String sql ="INSERT INTO animals (name,type) VALUES (:name,:type)";
            this.id=(int) con.createQuery(sql,true)
                    .addParameter("name",this.name)
                    .addParameter("type",this.type)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Animals> all(){
        try (Connection con=DB.sql2o.open()) {
            String sql ="SELECT * FROM animals";
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animals.class);
        }
    }

    @Override
    public void delete() {
        try (Connection con=DB.sql2o.open()){
            String sql = "DELETE FROM animals WHERE id=:id";
            con.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }
    }
    public static void deleteAll(){
        try (Connection con=DB.sql2o.open()){
            String sql = "DELETE FROM animals";
            con.createQuery(sql)
                    .executeUpdate();
        }  catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animals animals = (Animals) o;
        return name.equals(animals.name) &&
                type.equals(animals.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,type);
    }
}
