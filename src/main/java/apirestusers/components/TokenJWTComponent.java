package apirestusers.components;

import apirestusers.utils.Constants;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenJWTComponent {

    @Value("${jwt.iss}")
    private String jwtIss;

    @Value("${jwt.sub}")
    private String jwtSub;

    @Value("${jwt.secret}")
    private String secret;

    /**
     * Método que obtiene el token con JWT y el algoritmo de cifrado HS256.
     * @param password - Parámetro password del usuario.
     * @return String - Retorna el token JWT.
     */
    public String getToken(String password) {
        Long now = (Instant.now().toEpochMilli() + 300000) / 1000L;

        Map<String, Object> map = new HashMap<>();
        map.put("iss", jwtIss);
        map.put("iat", now);
        map.put("aud",Constants.PROPERTY_JWT_AUD);
        map.put("password", password);
        map.put("sub", jwtSub);

        JwtBuilder builder = Jwts.builder();

        Object jwt = "JWT";
        Object alg = "HS256";

        builder.setHeaderParam("alg", alg);
        builder.setHeaderParam("jwt", jwt);
        builder.addClaims(map);

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        return builder.signWith(signatureAlgorithm, secret.getBytes()).compact();

    }

}