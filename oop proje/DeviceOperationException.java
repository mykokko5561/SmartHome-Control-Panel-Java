package ev_kontrol_20_proje;

/**
 * @author Umut
 * Cihaz operasyonlarında oluşabilecek hatalar için özel Exception sınıfı.
 */
public class DeviceOperationException extends Exception {
    public DeviceOperationException(String message) {
        super(message);
    }
}