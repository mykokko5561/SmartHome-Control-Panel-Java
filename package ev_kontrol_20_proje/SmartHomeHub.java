package ev_kontrol_20_proje;

import java.util.ArrayList;
import java.util.List;

/**
 * Central Smart Home System (Singleton Design Pattern)
 * Ensures the entire home system is managed from a single center.
 */
public class SmartHomeHub {
    // Holding a private static instance of its own type (for Singleton)
    private static SmartHomeHub instance;
    
    // List to hold the devices
    private List<Smartdevice> deviceList;

    // 2. Making the constructor private so it cannot be instantiated with 'new' from outside.
    private SmartHomeHub() {
        deviceList = new ArrayList<>();
        System.out.println("[SYSTEM] Smart Home Hub initialized.");
    }

    // 3. Providing a global access point (getInstance) to reach the single instance of the class.
    public static SmartHomeHub getInstance() {
        if (instance == null) {
            instance = new SmartHomeHub();
        }
        return instance;
    }

    public void addDevice(Smartdevice device) {
        deviceList.add(device);
    }

    public int getTotalDeviceCount() {
        return deviceList.size();
    }

    public List<Smartdevice> getDeviceList() {
        return deviceList;
    }
}
