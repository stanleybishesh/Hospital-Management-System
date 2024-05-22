package com.example.hms.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;


import java.io.IOException;

@Slf4j
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";


    @Autowired
    RequestHandler requestHandler;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String accessToken = requestHandler.getJwtFromRequest(request);
            if (StringUtils.hasText(accessToken) && jwtTokenProvider.validateAccessToken(accessToken)) {
                Long userId = jwtTokenProvider.getUserIdFromJWT(accessToken);
                log.info("Verifying token");

                UserDetails userDetails = userDetailsService.loadUserById(userId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                log.info("Token verified");
            }
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            handlerExceptionResolver.resolveException(request, response, null, ex);
        }
        //Issue on filter for allowed unauthorized endpoints
//        if (request.getRequestURI().contains("/signup") ||
//                request.getRequestURI().contains("/signin") ||
//                request.getRequestURI().contains("/sign-in") ||
//                request.getRequestURI().contains("/login")) {
//            filterChain.doFilter(request, response);
//            return;
//        }

//        try {
//            String accessToken = requestHandler.getJwtFromRequest(request);
//
//            if (Preconditions.checkNotNull(accessToken) && !accessToken.equals("Basic")) {
//                if (StringUtils.hasText(accessToken) && jwtTokenProvider.validateAccessToken(accessToken)) {
//                    Long userId = jwtTokenProvider.getUserIdFromJWT(accessToken);
//
//                    log.info("Verifying Token");
//                    UserDetails userDetails = userDetailsService.loadUserById(userId);

//                for(GrantedAuthority idx : userDetails.getAuthorities()) {
//                    log.info("Detail:: " + idx.getAuthority());
//                }
//                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                    log.info("Token Verified");
//                    filterChain.doFilter(request, response);
//                } else {
//                    log.error("Responding with unauthorized error. Message - {}", "token is empty");
//                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
//                            AuthConstants.NOT_AUTHORISED + "Token is empty");
//                }
//            } else {
//                filterChain.doFilter(request, response);
//            }
//        } catch (Exception ex) {
//            log.error("Cannot set user authentication: {}", ex.getMessage());
//            handlerExceptionResolver.resolveException(request, response, null, ex);
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
//                    AuthConstants.NOT_AUTHORISED + ex.getMessage());
//        }
    }
}
