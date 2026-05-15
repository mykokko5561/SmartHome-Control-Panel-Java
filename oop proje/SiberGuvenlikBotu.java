package ev_kontrol_20_proje;

/**
 * @author Member 1
 */
public class SiberGuvenlikBotu extends SecurityDevice {
    private int blockedThreatsCount;
    private boolean isNetworkScanActive;

    public SiberGuvenlikBotu() {
        // Updated to English device name and location (System Room)
        super("SEC_BOT", "Network Security Bot (Firewall)", "System Room", true);
        this.blockedThreatsCount = 0;
        this.isNetworkScanActive = false;
    }

    public int getBlockedThreatsCount() { return blockedThreatsCount; }
    public void setBlockedThreatsCount(int blockedThreatsCount) { this.blockedThreatsCount = blockedThreatsCount; }
    public boolean getIsNetworkScanActive() { return isNetworkScanActive; }
    public void setIsNetworkScanActive(boolean isNetworkScanActive) { this.isNetworkScanActive = isNetworkScanActive; }

    @Override
    public void activateSecurity() {
        setIsSecurityActive(true);
        isNetworkScanActive = true;
        System.out.println(getDeviceName() + " started network scanning. Listening to Wi-Fi traffic.");
        System.out.println("System is protected against rogue networks and unauthorized access.");
    }

    @Override
    public void deactivateSecurity() {
        setIsSecurityActive(false);
        isNetworkScanActive = false;
        System.out.println(getDeviceName() + " firewall relaxed, network scan stopped.");
    }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Software Security)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Active Network Scan: " + isNetworkScanActive + " | Blocked Threats: " + blockedThreatsCount;
    }
}