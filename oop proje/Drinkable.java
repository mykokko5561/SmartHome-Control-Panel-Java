package ev_kontrol_20_proje;

/**
 * @author Canberk
 */
public interface Drinkable {
    void startBrewing() throws DeviceOperationException; // Demlemeyi/Isıtmayı Başlat
    void stopBrewing();  // Durdur
}