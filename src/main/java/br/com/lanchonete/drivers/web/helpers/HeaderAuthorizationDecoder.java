package br.com.lanchonete.drivers.web.helpers;

import br.com.lanchonete.core.userCases.exception.AuthorizationErro;
import io.jsonwebtoken.Claims;
import jakarta.ws.rs.core.HttpHeaders;

public class HeaderAuthorizationDecoder {
    public static Claims decode(HttpHeaders headers) {
        String authHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            var token = authHeader.substring(7).trim();
            var claims = JwtDecoder.decode(token);
            return claims;
        }
        throw new AuthorizationErro();
    }
}
