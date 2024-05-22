package com.example.hms.security;

import com.example.hms.security.JwtAuthEntryPoint;
import com.example.hms.security.JwtAuthTokenFilter;
import com.example.hms.security.UserDetailsServiceImpl;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    public static final String BASE_URL = "/api";

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/",
            "/auth/login",
            "/api/guest/"
    };

    private  final UserDetailsServiceImpl userDetailsService;

    private  final JwtAuthEntryPoint authEntryPoint;

    @Bean
    public JwtAuthTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/administration/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/administration/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/administration/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/appointment/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/appointment/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/appointment/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/bill/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/bill/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/bill/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/cashier/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/cashier/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/cashier/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/medicine/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/medicine/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/medicine/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/role/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/role/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/doctor/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/doctor/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/doctor/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/patient/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/patient/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/patient/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/ward/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/ward/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/ward/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/report/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/report/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/report/**").permitAll()
                .requestMatchers("/api/secured/registration").permitAll()
//                .requestMatchers("/admin/").hasAnyRole("ADMIN","SUPERADMIN")
//                .requestMatchers("/api/auth/user/").authenticated()
                .anyRequest().authenticated()

                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setAllowCredentials(false);
        configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Access-Control-Allow-Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin", "Cache-Control", "Content-Type", "Authorization"));
        configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PATCH", "PUT"));
        CorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        ((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/", configuration);
        return source;
    }
}