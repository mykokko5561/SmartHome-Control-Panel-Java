package ev_kontrol_20_proje;

/**
 * @author Member 1
 */
public abstract class CleaningDevice extends Smartdevice implements Cleanable {
    private boolean isCleaning;
    private String cleaningMode;

    // Designed according to Abstract class structure and Constructor chaining rules
    public CleaningDevice(String deviceId, String deviceName, String roomLocation, boolean isOpen) {
        super(deviceId, deviceName, roomLocation, isOpen);
        this.isCleaning = false;
        this.cleaningMode = "Standard"; // "Standart" -> "Standard"
    }

    public boolean getIsCleaning() { return isCleaning; }
    public void setIsCleaning(boolean isCleaning) { this.isCleaning = isCleaning; }
    public String getCleaningMode() { return cleaningMode; }
    public void setCleaningMode(String cleaningMode) { this.cleaningMode = cleaningMode; }

    @Override
    public String getDeviceInfo() {
        return "Cleaning Device: " + super.getDeviceName();
    }

    @Override
    public String toString() {
        return super.toString() + " | Cleaning Status: " + (isCleaning ? "Active" : "Standby") + " | Mode: " + cleaningMode;
    }
}