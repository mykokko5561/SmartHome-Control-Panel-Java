package ev_kontrol_20_proje;

public abstract class Smartdevice {
    private String deviceId;
    private String deviceName;
    private String roomLocation;
    private boolean isOpen;

    public Smartdevice(String deviceId, String deviceName, String roomLocation, boolean isOpen) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.roomLocation = roomLocation;
        this.isOpen = isOpen;
    }

    public String getDeviceId() { return deviceId; }
    public String getDeviceName() { return deviceName; }
    public String getRoomLocation() { return roomLocation; }
    public boolean getIsOpen() { return isOpen; }

    public String getDeviceInfo() {
        return "Device: " + deviceName;
    }

    @Override
    public String toString() {
        return "ID: " + deviceId + " | Location: " + roomLocation + " | Is On: " + isOpen;
    }
}
