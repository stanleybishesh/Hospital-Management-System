package com.example.hms.security;

import com.example.hms.mapper.user.UserMapper;
import com.example.hms.model.User;
import com.example.hms.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
@RequiredArgsConstructor
@Configuration
public class AuthUserBL {


    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepo userRepository;

    private final UserMapper userMapper;

    @Autowired
    private AuthenticationProvider authenticationProvider;


    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Arrays.asList(authenticationProvider));
    }


    public AuthenticationResponse authentication(AuthenticationRequest dto) {

        try {
            Authentication authentication = authenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getUsername(),
                            dto.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("Testing !!!");
            String accessToken = jwtTokenProvider.generateAccessToken(authentication);
            log.info(accessToken);
            String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);
            log.info(refreshToken);

            User user = userRepository.findByUsername(((UserPrincipal) authentication.getPrincipal()).getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
            return new AuthenticationResponse(accessToken, refreshToken, userMapper.toDto(user));
        } catch (DisabledException e) {
            throw new AccessDeniedException("Not verified !",e);
        } catch (BadCredentialsException e) {
            throw new AccessDeniedException("Bad !", e);
        } catch (Exception e) {
            log.error("Exception while login: ");
            e.printStackTrace();
        }
        return null;
    }
}
