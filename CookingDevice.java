package ev_kontrol_20_proje;

public abstract class CookingDevice extends Smartdevice implements Cookable {
    private boolean isCooking;
    private int currentTimerMinutes;
    private int currentPowerUsage;

    public CookingDevice(String deviceId, String deviceName, String roomLocation, boolean isOpen) {
        super(deviceId, deviceName, roomLocation, isOpen);
        this.isCooking = false;
        this.currentTimerMinutes = 0;
        this.currentPowerUsage = 0;
    }

    // Encapsulation - Getter and Setter methods for all variables
    public boolean getIsCooking() { return isCooking; }
    public void setIsCooking(boolean isCooking) { this.isCooking = isCooking; }
    public int getCurrentTimerMinutes() { return currentTimerMinutes; }
    public void setCurrentTimerMinutes(int currentTimerMinutes) { this.currentTimerMinutes = currentTimerMinutes; }
    public int getCurrentPowerUsage() { return currentPowerUsage; }
    public void setCurrentPowerUsage(int powerUsage) { this.currentPowerUsage = powerUsage; }

    // Method Overriding - Required sections for the project checklist
    @Override
    public String getDeviceInfo() {
        return "Cooking Device: " + super.getDeviceName() + " | Current Power Usage: " + currentPowerUsage + "W";
    }

    @Override
    public String toString() {
        return super.toString() + " | Cooking Status: " + isCooking + " | Time Remaining: " + currentTimerMinutes + " min";
    }
}
