package ev_kontrol_20_proje;

/**
 * @author Member 1
 */
public class Ocak extends CookingDevice {
    private int activeBurners;

    public Ocak() {
        // Updated to English device name and location (Kitchen)
        super("OCK_01", "Electric Stove", "Kitchen", false);
        this.activeBurners = 0;
    }

    public int getActiveBurners() { return activeBurners; }
    public void setActiveBurners(int activeBurners) throws DeviceOperationException { 
        if (activeBurners < 0 || activeBurners > 4) {
            throw new DeviceOperationException("Geçersiz işlem! Ocak gözü 0 ile 4 arasında olmalıdır.");
        }
        this.activeBurners = activeBurners; 
    }

    @Override
    public void startCooking() throws DeviceOperationException {
        if(activeBurners > 0) {
            setIsCooking(true);
            System.out.println(activeBurners + " burner(s) active on the stove.");
        } else {
            throw new DeviceOperationException("HATA: Hiçbir ocak gözü seçilmedi!\nLütfen ocağı başlatmadan önce en az bir gözü aktif hale getirin.");
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