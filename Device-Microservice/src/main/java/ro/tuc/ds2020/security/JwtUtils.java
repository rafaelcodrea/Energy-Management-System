package ro.tuc.ds2020.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {

    private final String key = "catdesexiifanaiidacutarabanadacavreipacateainimeritpersoana";



//    public String createToken(Map<String, Object> claims, String subject){
//        byte[] secretBytes = key.getBytes(StandardCharsets.UTF_8);
//        Key specKey = new SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.getJcaName());
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() +  864000000))
//                .signWith(specKey, SignatureAlgorithm.HS256)
//                .compact();
//    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) {
        byte[] secretBytes = key.getBytes(StandardCharsets.UTF_8);
        Key keys = Keys.hmacShaKeyFor(secretBytes);

        return Jwts.parserBuilder()
                .setSigningKey(keys)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean validateToken(String token, String username) {
        final String tokenUserName = getUsernameFromToken(token);
        return (tokenUserName.equals(username) && !isTokenExpired(token));
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getClaimFromToken(token, Claims::getExpiration);
        return expiration.before(new Date());
    }
}