package Data.VeiwModels;

public class BarrelViewModel {
    private double volume;
    private String storedContents;
    private int bodyMaterialCode;

    public BarrelViewModel(double volume, String storedContents, int bodyMaterialCode) {
        this.volume = volume;
        this.storedContents = storedContents;
        this.bodyMaterialCode = bodyMaterialCode;
    }

    public double getVolume() {
        return volume;
    }

    public String getStoredContents() {
        return storedContents;
    }

    public int getBodyMaterialCode() {
        return bodyMaterialCode;
    }
}
