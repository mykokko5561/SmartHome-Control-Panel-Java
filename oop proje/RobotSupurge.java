package ev_kontrol_20_proje;

/**
 * @author Member 1
 */
public class RobotSupurge extends CleaningDevice {
    private int batteryLevel;
    private int dustbinCapacity; // in percentage

    public RobotSupurge() {
        // Updated to English device name and location (Living Room)
        super("RS_01", "Smart Robot Vacuum", "Living Room", false);
        this.batteryLevel = 100;
        this.dustbinCapacity = 0;
        setCleaningMode("Mapped Vacuum and Mop");
    }

    public int getBatteryLevel() { return batteryLevel; }
    public void setBatteryLevel(int batteryLevel) { this.batteryLevel = batteryLevel; }
    public int getDustbinCapacity() { return dustbinCapacity; }
    public void setDustbinCapacity(int dustbinCapacity) { this.dustbinCapacity = dustbinCapacity; }

    @Override
    public void startCleaning() throws DeviceOperationException {
        if (batteryLevel > 20) {
            setIsCleaning(true);
            System.out.println(getDeviceName() + " started cleaning using mapping.");
        } else {
            throw new DeviceOperationException("Insufficient battery! Please send the device to the charging dock.");
        }
    }

    @Override
    public void stopCleaning() {
        setIsCleaning(false);
        System.out.println(getDeviceName() + " stopped cleaning and is returning to the dock.");
    }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Robot Vacuum)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Battery Level: %" + batteryLevel + " | Dustbin Capacity: %" + dustbinCapacity;
    }
}