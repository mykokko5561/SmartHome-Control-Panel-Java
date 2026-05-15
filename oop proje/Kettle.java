package ev_kontrol_20_proje;

/**
 * @author Member 1
 */
public class Kettle extends DrinkingDevice {
    private int targetTemperature;
    private boolean isKeepWarmActive;

    public Kettle() {
        // Updated to English device name and location (Kitchen)
        super("KTL_01", "Smart Water Kettle", "Kitchen", false);
        this.targetTemperature = 100;
        this.isKeepWarmActive = false;
    }

    public int getTargetTemperature() { return targetTemperature; }
    public void setTargetTemperature(int targetTemperature) { this.targetTemperature = targetTemperature; }
    public boolean getIsKeepWarmActive() { return isKeepWarmActive; }
    public void setIsKeepWarmActive(boolean isKeepWarmActive) { this.isKeepWarmActive = isKeepWarmActive; }

    @Override
    public void startBrewing() throws DeviceOperationException {
        if (getWaterLevelPercentage() > 10) {
            setIsBrewing(true);
            System.out.println(getDeviceName() + " started heating water to " + targetTemperature + " degrees.");
        } else {
            throw new DeviceOperationException("Not enough water in the kettle! Please add water.");
        }
    }

    @Override
    public void stopBrewing() {
        setIsBrewing(false);
        System.out.println(getDeviceName() + " stopped heating.");
    }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Kettle)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Target Temperature: " + targetTemperature + "°C | Keep Warm: " + isKeepWarmActive;
    }
}