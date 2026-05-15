package ev_kontrol_20_proje;

/**
 * @author Member 1
 */
public class AkilliKilit extends SecurityDevice {
    private boolean isLocked;
    private String lastAccessMethod;

    public AkilliKilit() {
        // Updated to English labels and locations
        super("LOCK_01", "Biometric Smart Lock", "Front Door", false);
        this.isLocked = true;
        this.lastAccessMethod = "System Initialized";
    }

    public boolean getIsLocked() { return isLocked; }
    public void setIsLocked(boolean isLocked) { this.isLocked = isLocked; }
    public String getLastAccessMethod() { return lastAccessMethod; }
    public void setLastAccessMethod(String lastAccessMethod) { this.lastAccessMethod = lastAccessMethod; }

    @Override
    public void activateSecurity() {
        setIsSecurityActive(true);
        isLocked = true;
        System.out.println(getDeviceName() + " (" + getRoomLocation() + ") locked. Security alarm armed.");
    }

    @Override
    public void deactivateSecurity() {
        setIsSecurityActive(false);
        isLocked = false;
        System.out.println(getDeviceName() + " unlocked. Alarm disarmed. (Method: " + lastAccessMethod + ")");
    }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Smart Door Lock)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Lock Status: " + (isLocked ? "LOCKED" : "UNLOCKED") + " | Last Access: " + lastAccessMethod;
    }
}