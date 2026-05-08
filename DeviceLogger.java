package ev_kontrol_20_proje;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A simple logging system to record system activities into a text file.
 */
public class DeviceLogger {
    private static final String LOG_FILE = "device_logs.txt";

    public static void log(String message) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            pw.println("[" + now.format(formatter) + "] " + message);
        } catch (IOException e) {
            System.out.println("Failed to write log: " + e.getMessage());
        }
    }
}
