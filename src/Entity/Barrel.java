package Entity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class Barrel implements Serializable, Comparable<Barrel> {

    private double volume;
    private String storedContents ;
    private BarrelMaterial bodyMaterial;

    public Barrel(BarrelBuilder barrelBuilder) {
        this.volume = barrelBuilder.volume;
        this.storedContents = barrelBuilder.storedContents;
        this.bodyMaterial = barrelBuilder.bodyMaterial;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getStoredContents() {
        return storedContents;
    }

    public void setStoredContents(String storedContents) {
        this.storedContents = storedContents;
    }

    public BarrelMaterial getBodyMaterial() {
        return bodyMaterial;
    }

    public void setBodyMaterial(BarrelMaterial bodyMaterial) {
        this.bodyMaterial = bodyMaterial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Barrel barrel = (Barrel) o;
        return Double.compare(volume, barrel.volume) == 0 && Objects.equals(storedContents, barrel.storedContents) && bodyMaterial == barrel.bodyMaterial;
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume, storedContents, bodyMaterial);
    }

    @Override
    public String toString() {
        return "Barrel{" +
                "volume=" + volume +
                ", storedContents='" + storedContents + '\'' +
                ", bodyMaterial=" + bodyMaterial +
                '}';
    }

    @Override
    public int compareTo(Barrel o) {
        Comparator<Barrel> comparator = Comparator.
                comparing(Barrel::getVolume).
                thenComparing(Barrel::getStoredContents).
                thenComparing(Barrel::getBodyMaterial);

        return comparator.compare(this, o);
    }

    public static class BarrelBuilder {
        private double volume;
        private String storedContents ;
        private BarrelMaterial bodyMaterial;

        public BarrelBuilder(double volume) {
            this.volume = volume;
            this.storedContents = "iron";
            this.bodyMaterial = BarrelMaterial.WOOD;
        }

        public BarrelBuilder setStorageMaterial(String storedContents) {
            this.storedContents = storedContents;
            return this;
        }

        public BarrelBuilder setManufacturingMaterial(BarrelMaterial bodyMaterial) {
            this.bodyMaterial = bodyMaterial;
            return this;
        }

        public Barrel build() {
            return new Barrel(this);
        }
    }
}
