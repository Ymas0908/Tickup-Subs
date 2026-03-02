package org.tickup.adapters.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@Slf4j
public class JwtHelper {

//    // Clé secrète générée dynamiquement avec HS256
//    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    //30 days
    private static final int REFRESH_TOKEN_MINUTES = 43200;
    private static final int ACCES_TOKEN_MINUTES = 30;
    private static SecretKey SECRET_KEY;
    @Value("${secret-key}")
    private String secretKeyString;  // La clé secrète venant de application.properties


    // Générer le access Token
    public static String generateAccessToken(String username, Map<String, Object> claims) {
        log.info("Generating access token for user: ****");
        Instant now = Instant.now();
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(ACCES_TOKEN_MINUTES, ChronoUnit.MINUTES)))
                .signWith(SECRET_KEY)
                .compact();
    }


    public static int getAccesTokenMinutes() {
        return ACCES_TOKEN_MINUTES * 60;
    }


    // Générer le Refresh Token
    public static String generateRefreshToken(String username) {
        log.info("Generating refresh token for user: *****");
        Instant now = Instant.now();
        return Jwts.builder()
                .subject(username)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(REFRESH_TOKEN_MINUTES, ChronoUnit.MINUTES)))
                .signWith(SECRET_KEY)
                .compact();
    }


    public static String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }

    public static Boolean validateToken(String token, UserDetails userDetails) {
        String username;
        username = extractUsername(token);
        return Objects.equals(username, userDetails.getUsername()) && !isTokenExpired(token);
    }

    private static boolean isTokenExpired(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            log.error("Error while validating token: {}", e.getMessage());
            return true;
        }
    }

    @PostConstruct
    public void init() {
        // Convertir la clé secrète en tableau de bytes et la transformer en clé de 256 bits
        byte[] apiKeySecretBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);
        SECRET_KEY = Keys.hmacShaKeyFor(apiKeySecretBytes);  // Créer une clé sécurisée
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public List<String> extractRoles(String token) {
        return (List<String>) extractAllClaims(token).get("roles");
    }
}
