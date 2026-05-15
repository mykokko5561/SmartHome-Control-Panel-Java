package ev_kontrol_20_proje;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Member 1
 * Veritabanı Sınıfı (Singleton & Separation of Concerns)
 * File I/O ile TXT dosyasına yazma ve okuma yapar. Performansı artırmak için RAM Cache (HashMap) kullanır.
 */
public class Database {
    private static Database instance;
    private final String FILE_NAME = "smarthome_states.txt";
    
    // RAM Cache: Dosyadan okunan veriler burada tutulur
    private Map<String, String> cache;

    private Database() {
        cache = new HashMap<>();
        loadCacheFromFile();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    // Başlangıçta verileri TXT dosyasından Cache'e yükler
    private void loadCacheFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    cache.put(parts[0], parts[1]);
                }
            }
            System.out.println("[Database] RAM Cache yüklendi. (Veri Sayısı: " + cache.size() + ")");
        } catch (IOException e) {
            System.err.println("[Database] Okuma hatası: " + e.getMessage());
        }
    }

    // Cache'teki verileri fiziksel TXT dosyasına yazar (Kapanışta çağrılır)
    public void saveCacheToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Map.Entry<String, String> entry : cache.entrySet()) {
                pw.println(entry.getKey() + "=" + entry.getValue());
            }
            System.out.println("[Database] Tüm sistem durumları TXT dosyasına kalıcı olarak kaydedildi.");
        } catch (IOException e) {
            System.err.println("[Database] Yazma hatası: " + e.getMessage());
        }
    }

    public void saveData(String key, String value) {
        cache.put(key, value);
    }

    public String getData(String key) {
        return cache.get(key);
    }
}