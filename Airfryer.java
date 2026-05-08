package ev_kontrol_20_proje;

/**
 * @author 1. üye
 */
public class Airfryer extends CookingDevice {
    private int temperature;

    public Airfryer() {
        // Cihaz adı ve konumu (Kitchen) İngilizce standartına çekildi
        super("AF_01", "Smart Airfryer", "Kitchen", false);
        this.temperature = 180;
    }

    public int getTemperature() { return temperature; }
    public void setTemperature(int temperature) { this.temperature = temperature; }

    @Override
    public void startCooking() throws DeviceOperationException {
        setIsCooking(true);
        setCurrentPowerUsage(1500);
        System.out.println(getDeviceName() + " cooking started at " + temperature + " degrees.");
    }

    @Override
    public void stopCooking() {
        setIsCooking(false);
        setCurrentPowerUsage(0);
        System.out.println(getDeviceName() + " stopped.");
    }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Airfryer)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Temperature: " + temperature + "°C";
    }
}