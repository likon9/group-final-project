package entity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Animal implements Item, Comparable<Animal> {

    private int id;
    private String animalSpecies;
    private AnimalEyeColor eyeColor;
    private boolean hasFur;

    public Animal(AnimalBuilder animalBuilder) {
        this.id = animalBuilder.id;
        this.animalSpecies = animalBuilder.animalSpecies;
        this.eyeColor = animalBuilder.eyeColor;
        this.hasFur = animalBuilder.hasFur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnimalSpecies() {
        return animalSpecies;
    }

    public void setAnimalSpecies(String animalSpecies) {
        this.animalSpecies = animalSpecies;
    }

    public AnimalEyeColor getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(AnimalEyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public boolean isHasFur() {
        return hasFur;
    }

    public void setHasFur(boolean hasFur) {
        this.hasFur = hasFur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id && hasFur == animal.hasFur && Objects.equals(animalSpecies, animal.animalSpecies) && Objects.equals(eyeColor, animal.eyeColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, animalSpecies, eyeColor, hasFur);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "animalSpecies='" + animalSpecies + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", hasFur=" + hasFur + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public int compareTo(Animal o) {
        Comparator<Animal> comparator = Comparator.
                comparing(Animal::getAnimalSpecies).
                thenComparing(Animal::getEyeColor).
                thenComparing(Animal::isHasFur).
                thenComparing(Animal::getId);

        return comparator.compare(this, o);
    }

    @Override
    public boolean isValid() {
        if (id <= 0) return false;
        if (eyeColor == null) return false;
        if (animalSpecies == null || animalSpecies.isBlank()) return false;
        return true;
    }

    public static class AnimalBuilder {
        private int id;
        private String animalSpecies;
        private AnimalEyeColor eyeColor;
        private boolean hasFur;

        public AnimalBuilder(String animalSpecies) {
            this.id = 0;
            this.animalSpecies = animalSpecies;
            this.eyeColor = AnimalEyeColor.BLACK;
            this.hasFur = false;
        }

        public AnimalBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public AnimalBuilder setEyeColor(AnimalEyeColor eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        public AnimalBuilder setHasFur(boolean hasFur) {
            this.hasFur = hasFur;
            return this;
        }


        public Animal build() {
            return new Animal(this);
        }
    }
}
