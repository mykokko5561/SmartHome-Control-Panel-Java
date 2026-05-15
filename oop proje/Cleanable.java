package ev_kontrol_20_proje;

/**
 * @author Canberk
 */
public interface Cleanable {
    void startCleaning() throws DeviceOperationException; 
    void stopCleaning();  
}