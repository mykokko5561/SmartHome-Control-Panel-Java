package ev_kontrol_20_proje;

/**
 * @author Canberk, Gökdeniz, Mustafa Yavuz, Umut
 */
public class MainTest {
    public static void main(String[] args) {
        
        // --- 0. KULLANICI GİRİŞ / KAYIT SİSTEMİ (FILE I/O) ---
        System.out.println("======= AKILLI EV GİRİŞ SİSTEMİ =======");
        UserManager userManager = new UserManager();
        boolean isLoggedin = false;

        try {
            // Senaryo gereği önce örnek bir kullanıcı kaydedelim (Eğer yoksa)
            userManager.registerUser("admin", "123456");
        } catch (AuthenticationException e) {
            System.out.println("BİLGİ: " + e.getMessage()); // Zaten kayıtlıysa bu mesajı verir
        }

        try {
            // Giriş denemesi yapıyoruz
            System.out.println("\n[SİSTEM] Giriş yapılıyor...");
            isLoggedin = userManager.loginUser("admin", "123456");
            if (!isLoggedin) {
                System.err.println("[-] Hatalı kullanıcı adı veya şifre! Sistem kapatılıyor.");
                return; // Giriş başarısızsa programı sonlandır
            }
            System.out.println("[+] Giriş Başarılı! Akıllı Ev Sistemine Hoş Geldiniz.\n");
        } catch (AuthenticationException e) {
            System.err.println("[-] GİRİŞ HATASI: " + e.getMessage());
            return; // Veritabanı okunamadıysa programı sonlandır
        }

        // Singleton Design Pattern Kullanımı: Merkezi Hub oluşturuluyor
        SmartHomeHub hub = SmartHomeHub.getInstance();

        // --- 1. CIHAZLARIN TANIMLANMASI ---
        
        // Mutfak Grubu
        Airfryer airfryer = new Airfryer();
        Ocak ocak = new Ocak();
        Buzdolabi buzdolabi = new Buzdolabi();
        Kettle kettle = new Kettle();
        KahveMakinesi kahveMakinesi = new KahveMakinesi();

        // Temizlik ve İklimlendirme Grubu
        RobotSupurge robot = new RobotSupurge();
        HavaTemizleyici havaTemizleyici = new HavaTemizleyici();
        Derindondurucu dondurucu = new Derindondurucu();

        // Teknoloji ve Eğlence Grubu
        Televizyon salonTv = new Televizyon("TV_01", "Samsung 4K TV", "Salon");
        Televizyon mutfakTv = new Televizyon("TV_02", "Philips Android TV", "Mutfak");
        SesSistemi sesSistemi = new SesSistemi();

        // Güvenlik Grubu
        AkilliKamera disKapiCam = new AkilliKamera("CAM_01", "Dış Kapı");
        AkilliKamera bahceCam = new AkilliKamera("CAM_02", "Arka Bahçe");
        AkilliKilit kilit = new AkilliKilit();
        SiberGuvenlikBotu firewall = new SiberGuvenlikBotu();

        // Aydınlatma Grubu[cite: 1, 3]
        AkilliLamba salonLamba = new AkilliLamba("L_01", "Salon");
        SeritLamba yatakOdasiLed = new SeritLamba("L_02", "Yatak Odası", 150);

        // Cihazları Merkezi Hub'a ekleyelim
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

        // --- 2. SISTEM TESTI VE OPERASYONLAR ---
        System.out.println("======= AKILLI EV SISTEM OPERASYONLARI =======\n");

        try {
            System.out.println("[+] Mutfak hazirlaniyor...");
            kettle.startBrewing();
            kahveMakinesi.setCoffeeType("Espresso");
            kahveMakinesi.startBrewing();
        } catch (DeviceOperationException e) {
            System.err.println("[-] HATA (Mutfak): " + e.getMessage());
        }

        try {
            System.out.println("\n[+] Temizlik ve Hava Kontrolü baslatildi...");
            robot.startCleaning();
            havaTemizleyici.setAirQualityIndex(45);
            havaTemizleyici.startCleaning();
        } catch (DeviceOperationException e) {
            System.err.println("[-] HATA (Temizlik): " + e.getMessage());
        }

        System.out.println("\n[+] Eglence sistemi aktive ediliyor...");
        salonTv.setActivePlatform("Netflix");
        salonTv.startPlaying();
        sesSistemi.setSurroundMode("Sinema Modu");
        sesSistemi.startPlaying();

        System.out.println("\n[+] Güvenlik ve Aydinlatma ayarlaniyor...");
        firewall.activateSecurity();
        firewall.setBlockedThreatsCount(2);
        kilit.activateSecurity();
        salonLamba.changeBrightness(40); //[cite: 1]
        yatakOdasiLed.setColorCode("Gün Isigi");

        // --- 3. DETAYLI SISTEM RAPORU ---
        System.out.println("\n============================================================");
        System.out.println("                AKILLI EV GÜNCEL DURUM RAPORU               ");
        System.out.println("============================================================\n");

        System.out.println("--- MUTFAK VE PISIRME GRUBU ---");
        System.out.println(airfryer.toString());
        System.out.println(ocak.toString());
        System.out.println(buzdolabi.toString());
        System.out.println(kettle.toString());
        System.out.println(kahveMakinesi.toString());

        System.out.println("\n--- TEMIZLIK VE IKLIMLENDIRME ---");
        System.out.println(robot.toString());
        System.out.println(havaTemizleyici.toString());
        System.out.println(dondurucu.toString());

        System.out.println("\n--- TEKNOLOJI VE EĞLENCE ---");
        System.out.println(salonTv.toString());
        System.out.println(mutfakTv.toString());
        System.out.println(sesSistemi.toString());

        System.out.println("\n--- GÜVENLIK SISTEMLERI ---");
        System.out.println(disKapiCam.toString());
        System.out.println(bahceCam.toString());
        System.out.println(kilit.toString());
        System.out.println(firewall.toString());

        System.out.println("\n--- AYDINLATMA SISTEMLERI ---");
        System.out.println(salonLamba.toString()); //[cite: 1]
        System.out.println(yatakOdasiLed.toString()); //[cite: 3]

        System.out.println("\n============================================================");
        System.out.println("           RAPOR SONU - TÜM SISTEMLER AKTIF");
        System.out.println("           Sisteme Kayıtlı Toplam Cihaz: " + hub.getTotalDeviceCount());
        System.out.println("============================================================");
    }
}