package ev_kontrol_20_proje;

public class AkilliLamba extends LightingDevice {
    private int scheduleTime;

    public AkilliLamba(String deviceId, String roomLocation) {
        // Updated device name and default color to English
        super(deviceId, "Smart LED Bulb", roomLocation, false, 50, "White");
        this.scheduleTime = 0;
    }

    public int getScheduleTime() { return scheduleTime; }
    public void setScheduleTime(int scheduleTime) { this.scheduleTime = scheduleTime; }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Smart Bulb)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Schedule: " + scheduleTime + " hours";
    }
}
