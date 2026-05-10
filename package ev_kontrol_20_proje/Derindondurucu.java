package ev_kontrol_20_proje;

public class Derindondurucu extends SicaklikDevice {
    private boolean isFastFreezeActive;
    private int iceStockPercentage;

    public Derindondurucu() {
        // Updated to English device name and location (Pantry)
        super("DD_01", "Ugur Deep Freezer", "Pantry", false);
        this.isFastFreezeActive = false;
        this.iceStockPercentage = 100;
        setTargetTemperature(-18); 
    }

    public boolean getIsFastFreezeActive() { return isFastFreezeActive; }
    public void setIsFastFreezeActive(boolean isFastFreezeActive) { this.isFastFreezeActive = isFastFreezeActive; }
    public int getIceStockPercentage() { return iceStockPercentage; }
    public void setIceStockPercentage(int iceStockPercentage) { this.iceStockPercentage = iceStockPercentage; }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Deep Freezer)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Fast Freeze: " + isFastFreezeActive + " | Ice Stock: %" + iceStockPercentage;
    }
}
