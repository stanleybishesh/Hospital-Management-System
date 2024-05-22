package com.example.hms.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


@Component
public class RequestHandler {

    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        } else if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Basic ")) {
            return "Basic";
        }
        return null;
//        throw new NOTESException("Jwt is empty or Basic/Bearer missing", ErrorCodes.UNNAUTHORIZED.toString());
    }

    /**
     * Get jwt from string request
     *
     * @return JWT
     */
    public String getJwtFromStringRequest(String request) {
        if (StringUtils.hasText(request) && request.startsWith("Bearer ")) {
            return request.substring(7, request.length());
        } else if (StringUtils.hasText(request) && request.startsWith("Basic ")) {
            return "Basic";
        }
        return null;
//        throw new NOTESException("Jwt is empty or Bearer missing", ErrorCodes.UNNAUTHORIZED.toString());
    }
}
