package ev_kontrol_20_proje;

public class KahveMakinesi extends DrinkingDevice {
    private String coffeeType;
    private int coffeeBeansPercentage; // Coffee bean level (%)

    public KahveMakinesi() {
        // Updated to English device name and location (Kitchen)
        super("KM_01", "Fully Automatic Coffee Machine", "Kitchen", false);
        this.coffeeType = "Filter Coffee";
        this.coffeeBeansPercentage = 80;
    }

    public String getCoffeeType() { return coffeeType; }
    public void setCoffeeType(String coffeeType) { this.coffeeType = coffeeType; }
    public int getCoffeeBeansPercentage() { return coffeeBeansPercentage; }
    public void setCoffeeBeansPercentage(int coffeeBeansPercentage) { this.coffeeBeansPercentage = coffeeBeansPercentage; }

    @Override
    public void startBrewing() throws DeviceOperationException {
        // Checking both water and coffee bean levels
        if (getWaterLevelPercentage() > 10 && coffeeBeansPercentage > 5) {
            setIsBrewing(true);
            System.out.println(getDeviceName() + " started brewing " + coffeeType + ". Smells delicious!");
        } else {
            throw new DeviceOperationException("Insufficient coffee beans or water level! Cannot brew coffee.");
        }
    }

    @Override
    public void stopBrewing() {
        setIsBrewing(false);
        System.out.println(getDeviceName() + " stopped brewing coffee.");
    }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Coffee Machine)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Coffee Type: " + coffeeType + " | Bean Level: %" + coffeeBeansPercentage;
    }
}
