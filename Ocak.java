package ev_kontrol_20_proje;

public class Ocak extends CookingDevice {
    private int activeBurners;

    public Ocak() {
        super("OCK_01", "Electric Stove", "Kitchen", false);
        this.activeBurners = 0;
    }

    public int getActiveBurners() { return activeBurners; }
    public void setActiveBurners(int activeBurners) { this.activeBurners = activeBurners; }

    @Override
    public void startCooking() throws DeviceOperationException {
        if(activeBurners > 0) {
            setIsCooking(true);
            System.out.println(activeBurners + " burner(s) active on the stove.");
        } else {
            throw new DeviceOperationException("Please turn on a burner first.");
        }
    }

    @Override
    public void stopCooking() {
        setIsCooking(false);
        this.activeBurners = 0;
        System.out.println("Stove completely turned off.");
    }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Stove)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Active Burners: " + activeBurners;
    }
}
