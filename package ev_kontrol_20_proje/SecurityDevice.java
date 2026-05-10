package ev_kontrol_20_proje;

public abstract class SecurityDevice extends Smartdevice implements Securable {
    private boolean isSecurityActive;
    private int threatLevel; // 0: Safe, 1: Suspicious, 2: Danger

    public SecurityDevice(String deviceId, String deviceName, String roomLocation, boolean isOpen) {
        super(deviceId, deviceName, roomLocation, isOpen);
        this.isSecurityActive = false;
        this.threatLevel = 0;
    }

    public boolean getIsSecurityActive() { return isSecurityActive; }
    public void setIsSecurityActive(boolean isSecurityActive) { this.isSecurityActive = isSecurityActive; }
    public int getThreatLevel() { return threatLevel; }
    public void setThreatLevel(int threatLevel) { this.threatLevel = threatLevel; }

    @Override
    public String getDeviceInfo() {
        return "Security Device: " + super.getDeviceName();
    }

    @Override
    public String toString() {
        return super.toString() + " | Security Mode: " + (isSecurityActive ? "Armed" : "Disarmed") + " | Threat Level: " + threatLevel;
    }
}
