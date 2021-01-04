public class Animal {
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


}
