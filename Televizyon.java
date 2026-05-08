package ev_kontrol_20_proje;

/**
 * @author Member 1
 */
public class Televizyon extends TechDevice {
    private int volumeLevel;
    private String activePlatform;

    // Constructor is kept flexible to create multiple different objects (Living Room TV, Kitchen TV) from the class.
    public Televizyon(String deviceId, String deviceName, String roomLocation) {
        super(deviceId, deviceName, roomLocation, false);
        this.volumeLevel = 20;
        this.activePlatform = "Cable TV";
    }

    public int getVolumeLevel() { return volumeLevel; }
    public void setVolumeLevel(int volumeLevel) { this.volumeLevel = volumeLevel; }
    public String getActivePlatform() { return activePlatform; }
    public void setActivePlatform(String activePlatform) { this.activePlatform = activePlatform; }

    @Override
    public void startPlaying() {
        setIsPlaying(true);
        System.out.println(getDeviceName() + " (" + getRoomLocation() + ") turned on. Watching " + activePlatform + ".");
    }

    @Override
    public void stopPlaying() {
        setIsPlaying(false);
        System.out.println(getDeviceName() + " turned off.");
    }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Smart TV)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Platform: " + activePlatform + " | Volume Level: " + volumeLevel;
    }
}