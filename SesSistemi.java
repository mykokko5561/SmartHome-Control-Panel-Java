package ev_kontrol_20_proje;

/**
 * @author Member 1
 */
public class SesSistemi extends TechDevice {
    private int bassLevel;
    private String surroundMode;

    public SesSistemi() {
        // Updated to English device name and location (Living Room)
        super("SS_01", "LG Home Theater System", "Living Room", false);
        this.bassLevel = 5;
        this.surroundMode = "Cinema Mode";
    }

    public int getBassLevel() { return bassLevel; }
    public void setBassLevel(int bassLevel) { this.bassLevel = bassLevel; }
    public String getSurroundMode() { return surroundMode; }
    public void setSurroundMode(String surroundMode) { this.surroundMode = surroundMode; }

    @Override
    public void startPlaying() {
        setIsPlaying(true);
        System.out.println(getDeviceName() + " is active. Sound is playing in " + surroundMode + ".");
    }

    @Override
    public void stopPlaying() {
        setIsPlaying(false);
        System.out.println(getDeviceName() + " stopped audio playback.");
    }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Sound System)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Mode: " + surroundMode + " | Bass Level: " + bassLevel;
    }
}