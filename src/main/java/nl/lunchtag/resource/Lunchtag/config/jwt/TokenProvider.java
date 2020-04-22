package nl.lunchtag.resource.Lunchtag.config.jwt;

import io.jsonwebtoken.*;
import nl.lunchtag.resource.Lunchtag.entity.Account;
import nl.lunchtag.resource.Lunchtag.entity.enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class TokenProvider {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${JWT_SECRET_KEY:verysecretkey}")
    private String secretKey;

    @Value("${JWT_VALIDITY_MS:86400000}")
    private int validityInMilliseconds;

    @PostConstruct
    public void initSecret() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    private UUID getID(String token) {
        return UUID.fromString(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());
    }

    private String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("username", String.class);
    }

    private String getRole(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("role", String.class);
    }

    public Authentication getAuthentication(String token) {
        Account user = new Account();
        user.setId(getID(token));
        user.setEmail(getUsername(token));
        user.setRole(Role.valueOf(getRole(token)));

        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }

    public String resolveToken(String bearer) {
        if(bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        } else {
            return null;
        }
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch(JwtException | IllegalArgumentException e) {
            logger.error(e.getMessage());
            throw new IllegalArgumentException("Invalid JWT");
        }
    }

    public String createToken(UUID username, String firstName, String lastName, Role role) {
        Claims claims = Jwts.claims().setSubject(username.toString());
        claims.put("username", firstName + " " + lastName);
        claims.put("role", role);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}