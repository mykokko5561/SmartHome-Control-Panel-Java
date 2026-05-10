package ev_kontrol_20_proje;

public abstract class TechDevice extends Smartdevice implements Playable {
    private boolean isPlaying;
    private String currentSource;

    public TechDevice(String deviceId, String deviceName, String roomLocation, boolean isOpen) {
        super(deviceId, deviceName, roomLocation, isOpen);
        this.isPlaying = false;
        this.currentSource = "No Connection";
    }

    public boolean getIsPlaying() { return isPlaying; }
    public void setIsPlaying(boolean isPlaying) { this.isPlaying = isPlaying; }
    public String getCurrentSource() { return currentSource; }
    public void setCurrentSource(String currentSource) { this.currentSource = currentSource; }

    @Override
    public String getDeviceInfo() {
        return "Tech Device: " + super.getDeviceName();
    }

    @Override
    public String toString() {
        return super.toString() + " | Playback Status: " + (isPlaying ? "Active" : "Standby") + " | Source: " + currentSource;
    }
}
