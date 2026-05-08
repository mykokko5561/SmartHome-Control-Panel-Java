package ev_kontrol_20_proje;

/**
 * @author Canberk
 */
public interface Cookable {
    void startCooking() throws DeviceOperationException; 
    void stopCooking();  
}