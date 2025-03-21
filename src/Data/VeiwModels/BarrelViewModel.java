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

    public BarrelViewModel() {
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

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setStoredContents(String storedContents) {
        this.storedContents = storedContents;
    }

    public void setBodyMaterialCode(int bodyMaterialCode) {
        this.bodyMaterialCode = bodyMaterialCode;
    }
}
