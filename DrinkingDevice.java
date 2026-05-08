package ev_kontrol_20_proje;

public abstract class DrinkingDevice extends Smartdevice implements Drinkable {
    private boolean isBrewing;
    private int waterLevelPercentage; // Water level (%)

    public DrinkingDevice(String deviceId, String deviceName, String roomLocation, boolean isOpen) {
        super(deviceId, deviceName, roomLocation, isOpen);
        this.isBrewing = false;
        this.waterLevelPercentage = 100;
    }

    public boolean getIsBrewing() { return isBrewing; }
    public void setIsBrewing(boolean isBrewing) { this.isBrewing = isBrewing; }
    public int getWaterLevelPercentage() { return waterLevelPercentage; }
    public void setWaterLevelPercentage(int waterLevelPercentage) { this.waterLevelPercentage = waterLevelPercentage; }

    @Override
    public String getDeviceInfo() {
        return "Beverage Maker: " + super.getDeviceName();
    }

    @Override
    public String toString() {
        return super.toString() + " | Status: " + (isBrewing ? "Brewing" : "Standby") + " | Water Level: %" + waterLevelPercentage;
    }
}
