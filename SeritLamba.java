package ev_kontrol_20_proje;

/**
 * @author Member 1
 */
public class SeritLamba extends LightingDevice {
    private int ledCount;

    public SeritLamba(String deviceId, String roomLocation, int ledCount) {
        // Updated to English device name
        super(deviceId, "RGB Strip LED", roomLocation, false, 80, "RGB-Mix");
        this.ledCount = ledCount;
    }

    public int getLedCount() { return ledCount; }
    public void setLedCount(int ledCount) { this.ledCount = ledCount; }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Strip LED)";
    }

    @Override
    public String toString() {
        return super.toString() + " | LED Count: " + ledCount + " units";
    }
}