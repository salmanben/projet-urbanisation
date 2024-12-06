package ma.ensa.urgence.controller.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "9a8b7c6d5e4f3g2h1i0jK9L8M7N6O5P4";

    // Méthode pour obtenir la clé de signature
    private static Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    // Méthode pour générer le token JWT
    public static String generateToken(String email) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // Durée de validité du token (par exemple, 10 heures)
        long validityMillis = 1000 * 60 * 60 * 10; // 10 heures en millisecondes
        Date expiryDate = new Date(nowMillis + validityMillis);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }





}
