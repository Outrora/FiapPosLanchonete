package br.com.lanchonete.drivers.web.helpers;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import org.eclipse.microprofile.config.ConfigProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtDecoder {

    public static Claims decode(String token) {
        var secretKey = ConfigProvider.getConfig().getValue("secret", String.class);

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}
