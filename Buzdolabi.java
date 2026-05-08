package ev_kontrol_20_proje;

/**
 * @author Member 1
 */
public class Buzdolabi extends SicaklikDevice {
    private boolean isEcoMode;
    private boolean doorAlarmRinging;

    public Buzdolabi() {
        // Updated to English device name and location
        super("BUZ_01", "Smart Refrigerator", "Kitchen", true);
        this.isEcoMode = true;
        this.doorAlarmRinging = false;
        setTargetTemperature(4); 
    }

    public boolean getIsEcoMode() { return isEcoMode; }
    public void setIsEcoMode(boolean isEcoMode) { this.isEcoMode = isEcoMode; }
    public boolean getDoorAlarmRinging() { return doorAlarmRinging; }
    public void setDoorAlarmRinging(boolean doorAlarmRinging) { this.doorAlarmRinging = doorAlarmRinging; }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Refrigerator)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Eco Mode: " + isEcoMode + " | Door Alarm: " + doorAlarmRinging;
    }
}