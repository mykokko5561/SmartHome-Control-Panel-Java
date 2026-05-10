package ev_kontrol_20_proje;

public class MainTest {
    public static void main(String[] args) {
        
        // --- 0. USER LOGIN / REGISTRATION SYSTEM (FILE I/O) ---
        System.out.println("======= SMART HOME LOGIN SYSTEM =======");
        UserManager userManager = new UserManager();
        boolean isLoggedin = false;

        try {
            // Register a sample user for the scenario (if it doesn't exist)
            userManager.registerUser("admin", "123456");
        } catch (AuthenticationException e) {
            System.out.println("INFO: " + e.getMessage()); // Displays if already registered
        }

        try {
            // Attempting to login
            System.out.println("\n[SYSTEM] Logging in...");
            isLoggedin = userManager.loginUser("admin", "123456");
            if (!isLoggedin) {
                System.err.println("[-] Invalid username or password! System shutting down.");
                return; // Terminate program if login fails
            }
            System.out.println("[+] Login Successful! Welcome to the Smart Home System.\n");
        } catch (AuthenticationException e) {
            System.err.println("[-] LOGIN ERROR: " + e.getMessage());
            return; // Terminate if database cannot be read
        }

        // Singleton Design Pattern Usage: Creating the Central Hub
        SmartHomeHub hub = SmartHomeHub.getInstance();

        // --- 1. DEVICE DEFINITIONS ---
        
        // Kitchen Group
        Airfryer airfryer = new Airfryer();
        Ocak ocak = new Ocak();
        Buzdolabi buzdolabi = new Buzdolabi();
        Kettle kettle = new Kettle();
        KahveMakinesi kahveMakinesi = new KahveMakinesi();

        // Cleaning and Climate Group
        RobotSupurge robot = new RobotSupurge();
        HavaTemizleyici havaTemizleyici = new HavaTemizleyici();
        Derindondurucu dondurucu = new Derindondurucu();

        // Tech and Entertainment Group
        Televizyon salonTv = new Televizyon("TV_01", "Samsung 4K TV", "Living Room");
        Televizyon mutfakTv = new Televizyon("TV_02", "Philips Android TV", "Kitchen");
        SesSistemi sesSistemi = new SesSistemi();

        // Security Group
        AkilliKamera disKapiCam = new AkilliKamera("CAM_01", "Front Door");
        AkilliKamera bahceCam = new AkilliKamera("CAM_02", "Backyard");
        AkilliKilit kilit = new AkilliKilit();
        SiberGuvenlikBotu firewall = new SiberGuvenlikBotu();

        // Lighting Group
        AkilliLamba salonLamba = new AkilliLamba("L_01", "Living Room");
        SeritLamba yatakOdasiLed = new SeritLamba("L_02", "Bedroom", 150);

        // Adding devices to the Central Hub
        hub.addDevice(airfryer);
        hub.addDevice(ocak);
        hub.addDevice(buzdolabi);
        hub.addDevice(kettle);
        hub.addDevice(kahveMakinesi);
        hub.addDevice(robot);
        hub.addDevice(havaTemizleyici);
        hub.addDevice(dondurucu);
        hub.addDevice(salonTv);
        hub.addDevice(mutfakTv);
        hub.addDevice(sesSistemi);
        hub.addDevice(disKapiCam);
        hub.addDevice(bahceCam);
        hub.addDevice(kilit);
        hub.addDevice(firewall);
        hub.addDevice(salonLamba);
        hub.addDevice(yatakOdasiLed);

        // --- 2. SYSTEM TEST AND OPERATIONS ---
        System.out.println("======= SMART HOME SYSTEM OPERATIONS =======\n");

        try {
            System.out.println("[+] Preparing Kitchen...");
            kettle.startBrewing();
            kahveMakinesi.setCoffeeType("Espresso");
            kahveMakinesi.startBrewing();
        } catch (DeviceOperationException e) {
            System.err.println("[-] ERROR (Kitchen): " + e.getMessage());
        }

        try {
            System.out.println("\n[+] Cleaning and Air Control started...");
            robot.startCleaning();
            havaTemizleyici.setAirQualityIndex(45);
            havaTemizleyici.startCleaning();
        } catch (DeviceOperationException e) {
            System.err.println("[-] ERROR (Cleaning): " + e.getMessage());
        }

        System.out.println("\n[+] Entertainment system activating...");
        salonTv.setActivePlatform("Netflix");
        salonTv.startPlaying();
        sesSistemi.setSurroundMode("Cinema Mode");
        sesSistemi.startPlaying();

        System.out.println("\n[+] Configuring Security and Lighting...");
        firewall.activateSecurity();
        firewall.setBlockedThreatsCount(2);
        kilit.activateSecurity();
        salonLamba.changeBrightness(40); 
        yatakOdasiLed.setColorCode("Daylight");

        // --- 3. DETAILED SYSTEM REPORT ---
        System.out.println("\n============================================================");
        System.out.println("                SMART HOME CURRENT STATUS REPORT                ");
        System.out.println("============================================================\n");

        System.out.println("--- KITCHEN AND COOKING GROUP ---");
        System.out.println(airfryer.toString());
        System.out.println(ocak.toString());
        System.out.println(buzdolabi.toString());
        System.out.println(kettle.toString());
        System.out.println(kahveMakinesi.toString());

        System.out.println("\n--- CLEANING AND CLIMATE CONTROL ---");
        System.out.println(robot.toString());
        System.out.println(havaTemizleyici.toString());
        System.out.println(dondurucu.toString());

        System.out.println("\n--- TECHNOLOGY AND ENTERTAINMENT ---");
        System.out.println(salonTv.toString());
        System.out.println(mutfakTv.toString());
        System.out.println(sesSistemi.toString());

        System.out.println("\n--- SECURITY SYSTEMS ---");
        System.out.println(disKapiCam.toString());
        System.out.println(bahceCam.toString());
        System.out.println(kilit.toString());
        System.out.println(firewall.toString());

        System.out.println("\n--- LIGHTING SYSTEMS ---");
        System.out.println(salonLamba.toString()); 
        System.out.println(yatakOdasiLed.toString()); 

        System.out.println("\n============================================================");
        System.out.println("           REPORT END - ALL SYSTEMS ACTIVE");
        System.out.println("           Total Registered Devices: " + hub.getTotalDeviceCount());
        System.out.println("============================================================");
    }
}
