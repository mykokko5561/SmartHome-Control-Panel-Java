package ev_kontrol_20_proje;

public class BulasikMakinesi extends CleaningDevice {
    private int waterTemperature;
    private boolean isEcoWashActive;

    public BulasikMakinesi() {
        // Updated to English device name and location (Kitchen)
        super("BM_01", "Smart Dishwasher", "Kitchen", false);
        this.waterTemperature = 65;
        this.isEcoWashActive = true;
        setCleaningMode("Eco Wash");
    }

    public int getWaterTemperature() { return waterTemperature; }
    public void setWaterTemperature(int waterTemperature) { this.waterTemperature = waterTemperature; }
    public boolean getIsEcoWashActive() { return isEcoWashActive; }
    public void setIsEcoWashActive(boolean isEcoWashActive) { this.isEcoWashActive = isEcoWashActive; }

    @Override
    public void startCleaning() throws DeviceOperationException {
        setIsCleaning(true);
        // Log message updated to English
        System.out.println(getDeviceName() + " started washing at " + waterTemperature + " degrees.");
    }

    @Override
    public void stopCleaning() {
        setIsCleaning(false);
        // Log message updated to English
        System.out.println(getDeviceName() + " program finished.");
    }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Dishwasher)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Water Temperature: " + waterTemperature + "°C | Eco Wash: " + isEcoWashActive;
    }
}
