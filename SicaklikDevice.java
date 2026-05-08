package ev_kontrol_20_proje;

public abstract class SicaklikDevice extends Smartdevice {
    private int targetTemperature;
    private int currentTemperature;
    private String compressorStatus;

    public SicaklikDevice(String deviceId, String deviceName, String roomLocation, boolean isOpen) {
        super(deviceId, deviceName, roomLocation, isOpen);
        this.currentTemperature = 20;  
        this.compressorStatus = "Standby";
    }

    public int getTargetTemperature() { return targetTemperature; }
    public void setTargetTemperature(int targetTemperature) { this.targetTemperature = targetTemperature; }
    public int getCurrentTemperature() { return currentTemperature; }
    public void setCurrentTemperature(int currentTemperature) { this.currentTemperature = currentTemperature; }
    public String getCompressorStatus() { return compressorStatus; }
    public void setCompressorStatus(String compressorStatus) { this.compressorStatus = compressorStatus; }

    @Override
    public String getDeviceInfo() {
        return "Climate Control Device: " + super.getDeviceName();
    }

    @Override
    public String toString() {
        return super.toString() + " | Target Temperature: " + targetTemperature + "°C | Compressor: " + compressorStatus;
    }
}
