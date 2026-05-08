package ev_kontrol_20_proje;


public class Firin extends CookingDevice {
    private String cookingMode;

    public Firin() {
        // Updated to English device name and location (Kitchen)
        super("FIR_01", "Built-in Oven", "Kitchen", false);
        this.cookingMode = "Top-Bottom Heating";
    }

    public String getCookingMode() { return cookingMode; }
    public void setCookingMode(String cookingMode) { this.cookingMode = cookingMode; }

    @Override
    public void startCooking() throws DeviceOperationException {
        setIsCooking(true);
        System.out.println(getDeviceName() + " is running in " + cookingMode + " mode.");
    }

    @Override
    public void stopCooking() {
        setIsCooking(false);
        System.out.println(getDeviceName() + " turned off.");
    }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Oven)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Cooking Mode: " + cookingMode;
    }
}
