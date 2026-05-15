package ev_kontrol_20_proje;

/**
 * @author Member 1
 */
public class CamasirMakinesi extends CleaningDevice {
    private int spinSpeed;
    private boolean isDetergentEmpty;

    public CamasirMakinesi() {
        // Updated to English device name and location (Bathroom)
        super("CM_01", "Smart Washing Machine", "Bathroom", false);
        this.spinSpeed = 1000;
        this.isDetergentEmpty = false;
        setCleaningMode("Cottons");
    }

    public int getSpinSpeed() { return spinSpeed; }
    public void setSpinSpeed(int spinSpeed) { this.spinSpeed = spinSpeed; }
    public boolean getIsDetergentEmpty() { return isDetergentEmpty; }
    public void setIsDetergentEmpty(boolean isDetergentEmpty) { this.isDetergentEmpty = isDetergentEmpty; }

    @Override
    public void startCleaning() throws DeviceOperationException {
        if (!isDetergentEmpty) {
            setIsCleaning(true);
            System.out.println(getDeviceName() + " locked the door and started washing on " + getCleaningMode() + " program.");
        } else {
            throw new DeviceOperationException("Detergent dispenser is empty! Cannot start washing.");
        }
    }

    @Override
    public void stopCleaning() {
        setIsCleaning(false);
        System.out.println(getDeviceName() + " canceled the program and is draining water.");
    }

    @Override
    public String getDeviceInfo() {
        return super.getDeviceInfo() + " (Washing Machine)";
    }

    @Override
    public String toString() {
        return super.toString() + " | Spin Speed: " + spinSpeed + " RPM | Detergent Status: " + (isDetergentEmpty ? "EMPTY" : "FULL");
    }
}