package data.veiwmodels;

import java.io.Serializable;

public class AnimalViewModel implements Serializable {
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

    public AnimalViewModel() {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setEyesColorCode(int eyesColorCode) {
        this.eyesColorCode = eyesColorCode;
    }

    public void setHasFur(boolean hasFur) {
        this.hasFur = hasFur;
    }

    @Override
    public String toString() {
        return "AnimalViewModel{" +
                "id=" + id +
                ", species='" + species + '\'' +
                ", eyesColorCode=" + eyesColorCode +
                ", hasFur=" + hasFur +
                '}';
    }
}
