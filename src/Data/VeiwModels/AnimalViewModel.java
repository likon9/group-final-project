package Data.VeiwModels;

public class AnimalViewModel {
    private int id;
    private String species;
    private int eyesColorCode;
    private boolean hasFur;

    public AnimalViewModel(int id, String species, int eyesColorCode, boolean hasFur) {
        this.id = id;
        this.species = species;
        this.eyesColorCode = eyesColorCode;
        this.hasFur = hasFur;
    }

    public int getId() {
        return id;
    }

    public String getSpecies() {
        return species;
    }

    public int getEyesColorCode() {
        return eyesColorCode;
    }

    public boolean isHasFur() {
        return hasFur;
    }
}
