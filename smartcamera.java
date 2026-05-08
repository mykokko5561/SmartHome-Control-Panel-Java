package ev_kontrol_20_proje;

public class AkilliKamera extends SecurityDevice {
    private boolean isNightVisionOn;
    private boolean isMotionDetected;

    public AkilliKamera(String deviceId, String roomLocation) {
        super(deviceId, "360° IP Security Camera", roomLocation, true);
        this.isNightVisionOn = false;
        this.isMotionDetected = false;
    }

    public boolean getIsNightVisionOn() { return isNightVisionOn; }
    public void setIsNightVisionOn(boolean isNightVisionOn) { this.isNightVisionOn = isNightVisionOn; }
    public boolean getIsMotionDetected() { return isMotionDetected; }
    public void setIsMotionDetected(boolean isMotionDetected) { this.isMotionDetected = isMotionDetected; }

    @Override
    public void activateSecurity() {
        setIsSecurityActive(true);
        System.out.println(getDeviceName() + " (" + getRoomLocation() + ") recording started.");
        if (isNightVisionOn) {
            System.out.println("Night vision active. Motion sensors are on duty.");
        }
    }

    @Override
    public void deactivateSecurity() {
        setIsSecurityActive(false);
        System.out.println(getDeviceName() + " standby mode.");
    }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Camera)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Night Vision: " + isNightVisionOn + " | Motion Detected: " + isMotionDetected;
    }
}
