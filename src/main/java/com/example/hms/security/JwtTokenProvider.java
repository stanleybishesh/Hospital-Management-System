package com.example.hms.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.refreshToken.secretKey}")
    private String refreshTokenSecretKey;

    @Value("${jwt.accessToken.secretKey}")
    private String accessTokenSecretKey;

    @Value("${jwt.refreshToken.expiresIn}")
    private Long refreshTokenExpireInMs;

    @Value("${jwt.accessToken.expiresIn}")
    private Long accessTokenExpireInMs;

    public String generateAccessToken(Authentication authentication) throws Exception {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + accessTokenExpireInMs);
        final String authorities = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
//        log.info("Access token expiry date {} of user {} ", expiryDate, userPrincipal.getUsername());

        Map<String, Object> claims = new HashMap<>();
        claims.put("Username", userPrincipal.getUsername());
        claims.put("Roles", authorities);
          String token = Jwts.builder()
                  .setClaims(claims)
                  .setSubject(Long.toString(userPrincipal.getId()))
                  .setIssuedAt(now)
                  .setExpiration(expiryDate)
                  .signWith(SignatureAlgorithm.HS512, accessTokenSecretKey)
                  .compact();
          log.info(token);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, accessTokenSecretKey)
                .compact();
    }

    public String generateRefreshToken(Authentication authentication) throws Exception {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + refreshTokenExpireInMs);
        final String authorities = userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
//        log.info("Access token expiry date {} of user {} ", expiryDate, userPrincipal.getUsername());

        Map<String, Object> claims = new HashMap<>();
        claims.put("Username", userPrincipal.getUsername());
        claims.put("Roles", authorities);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, refreshTokenSecretKey)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(accessTokenSecretKey)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public String getUserNameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(accessTokenSecretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.get("Username").toString();
    }

//    public UserDetailsResponse getUserNameAndRolesFromJWT(String token) throws Exception {
//        Claims claims = Jwts.parser()
//                .setSigningKey(accessTokenSecretKey)
//                .parseClaimsJws(token)
//                .getBody();
//
//        return new UserDetailsResponse(claims.get(AuthConstants.USERNAME_KEY).toString() , claims.get(AuthConstants.ROLES_KEY).toString());
//    }

    public boolean validateAccessToken(String accessToken)
            throws SignatureException, MalformedJwtException, ExpiredJwtException, UnsupportedOperationException, IllegalArgumentException {
        try {
            Jwts.parser().setSigningKey(accessTokenSecretKey).parseClaimsJws(accessToken);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
            throw new SignatureException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
            throw new MalformedJwtException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
            throw new ExpiredJwtException(null, null, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
            throw new UnsupportedJwtException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
            throw new IllegalArgumentException("JWT claims string is empty.");
        }
    }

    public boolean validateRefreshToken(String refreshToken)
            throws SignatureException, MalformedJwtException, ExpiredJwtException, UnsupportedOperationException, IllegalArgumentException {
        Jwts.parser().setSigningKey(refreshTokenSecretKey).parseClaimsJws(refreshToken);
        return true;
    }
}
