package ev_kontrol_20_proje;

/**
 * @author Member 1
 */
public abstract class LightingDevice extends Smartdevice implements Lightable {
    private int brightness;
    private String colorCode;

    public LightingDevice(String deviceId, String deviceName, String roomLocation, boolean isOpen, int brightness, String colorCode) {
        super(deviceId, deviceName, roomLocation, isOpen);
        this.brightness = brightness;
        this.colorCode = colorCode;
    }

    public int getBrightness() { return brightness; }
    public void setBrightness(int brightness) { this.brightness = brightness; }
    public String getColorCode() { return colorCode; }
    public void setColorCode(String colorCode) { this.colorCode = colorCode; }

    // Moved the method previously written in two separate classes here. Polymorphism and DRY principle!
    @Override
    public void changeBrightness(int amount) {
        this.brightness += amount;
        if(this.brightness > 100) this.brightness = 100; // Brightness cannot exceed 100
        if(this.brightness < 0) this.brightness = 0;   // Brightness cannot drop below 0
        System.out.println(getDeviceName() + " brightness set to %" + this.brightness + ".");
    }

    @Override
    public String getDeviceInfo() {
        return "Lighting Device: " + super.getDeviceName();
    }

    @Override
    public String toString() {
        return super.toString() + " | Brightness: %" + brightness + " | Color Code: " + colorCode;
    }
}