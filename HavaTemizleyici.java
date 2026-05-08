package ev_kontrol_20_proje;

public class HavaTemizleyici extends CleaningDevice {
    private int airQualityIndex;
    private int filterLifePercentage;

    public HavaTemizleyici() {
        // Updated to English device name and location (Bedroom)
        super("HT_01", "Dyson Air Purifier", "Bedroom", true);
        this.airQualityIndex = 45; // Lower is better
        this.filterLifePercentage = 85;
        setCleaningMode("Auto Sensor Mode");
    }

    public int getAirQualityIndex() { return airQualityIndex; }
    public void setAirQualityIndex(int airQualityIndex) { this.airQualityIndex = airQualityIndex; }
    public int getFilterLifePercentage() { return filterLifePercentage; }
    public void setFilterLifePercentage(int filterLifePercentage) { this.filterLifePercentage = filterLifePercentage; }

    @Override
    public void startCleaning() throws DeviceOperationException {
        setIsCleaning(true);
        System.out.println(getDeviceName() + " is measuring air quality. Current AQI: " + airQualityIndex);
        if (airQualityIndex > 50) {
            System.out.println("Air quality is low, increasing fan speed!");
        }
    }

    @Override
    public void stopCleaning() {
        setIsCleaning(false);
        System.out.println(getDeviceName() + " switched to sleep mode.");
    }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Air Purifier)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Air Quality (AQI): " + airQualityIndex + " | Filter Life: %" + filterLifePercentage;
    }
}
