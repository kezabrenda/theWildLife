package models;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AnimalsTest {

    @Rule
    public DatabaseRule databaseRule=new DatabaseRule();

    @Test
    public void Animals_instantiatesCorrectly_true() {
        Animals testAnimals = new Animals("panda","secured");
        assertEquals(true, testAnimals instanceof Animals);
    }
    @Test
    public void Animals_instantiatesWithName_String() {
        Animals testAnimals = new Animals("panda","secured");
        assertEquals("panda", testAnimals.getName());
    }
    @Test
    public void Animals_instantiatesWithType_StringAndIsTheAssignedConstant() {
        Animals testAnimals = new Animals("panda","secured");
        assertEquals("secured", testAnimals.getType());
    }
    @Test
    public void Animals_instantiatesWithHealth_StringAndIsTheAssignedValue() {
        Animals testAnimals = new Animals("panda","secured");
        assertEquals("", testAnimals.getHealth());
    }
    @Test
    public void Animals_instantiatesWithAge_StringAndIsTheAssignedValue() {
        Animals testAnimals = new Animals("panda","secured");
        assertEquals("", testAnimals.getAge());
    }
}