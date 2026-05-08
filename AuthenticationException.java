package ev_kontrol_20_proje;

/**
 * @author Umut
 * Kullanıcı giriş ve kayıt işlemlerinde oluşabilecek hatalar için özel Exception.
 */
public class AuthenticationException extends Exception {
    public AuthenticationException(String message) {
        super(message);
    }
}