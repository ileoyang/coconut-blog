package io.leo.coconut.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;

/**
 * @author Leo
 */
public class JwtUtil {

    private static final String SECRET = "gdE8XpSFcE3eFeLCYd9UxWT4ZqfDDpnG";
    private static final long EXPIRATION = 100000000;

    public static String getToken(Integer userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        String jwt = Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return jwt;
    }

    public static Integer getUserId(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(jwt)
                .getBody();
        return (Integer) claims.get("userId");
    }

}
