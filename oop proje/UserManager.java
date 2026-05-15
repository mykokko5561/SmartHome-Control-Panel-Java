package ev_kontrol_20_proje;

import java.io.*;
import java.util.Base64;

/**
 * @author Member 1
 * Manages writing and reading (File I/O) user data to a txt file.
 */
public class UserManager {
    private static final String FILE_NAME = "users.txt";

    // User Registration
    public void registerUser(String username, String password) throws AuthenticationException {
        if (userExists(username)) {
            throw new AuthenticationException("User already exists: " + username);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            // Base64 ile şifreleme (Encryption)
            String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());

            // Saving username and encrypted password separated by a comma
            bw.write(username + "," + encodedPassword);
            bw.newLine();
            System.out.println("[+] User successfully registered: " + username);
            DeviceLogger.log("SYSTEM: New user registered -> " + username);
        } catch (IOException e) {
            throw new AuthenticationException("File write error: " + e.getMessage());
        }
    }

    // User Login
    public boolean loginUser(String username, String password) throws AuthenticationException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String storedPassword = parts[1];
                    String decodedPassword;
                    try {
                        // Base64 ile şifre çözme (Decryption)
                        decodedPassword = new String(Base64.getDecoder().decode(storedPassword));
                    } catch (IllegalArgumentException e) {
                        // Eski (şifrelenmemiş) kayıtlar için geriye dönük uyumluluk
                        decodedPassword = storedPassword;
                    }

                    if (parts[0].equals(username) && decodedPassword.equals(password)) {
                        DeviceLogger.log("SYSTEM: User logged in -> " + username);
                        return true; // Login successful
                    }
                }
            }
            return false; // Invalid username or password
        } catch (FileNotFoundException e) {
            throw new AuthenticationException("User database not found. Please register first.");
        } catch (IOException e) {
            throw new AuthenticationException("File read error: " + e.getMessage());
        }
    }

    // Helper Method: Does the user exist in the system?
    private boolean userExists(String username) {
        File file = new File(FILE_NAME);
        if (!file.exists()) return false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException ignored) {} // Ignoring the exception as this is just a check
        return false;
    }
}