package com.example.hms.security;

import com.example.hms.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
//@RequestMapping(WebSecurityConfig.BASE_URL + AuthResource.AUTHENTICATED_PATH)
public class AuthResource {

    public static final String AUTHENTICATED_PATH = "/auth";

    private final RequestHandler requestHandler;

    private final AuthUserBL authService;

//    private final URegistrationBL registrationService;

    private final JwtTokenProvider tokenProvider;

    private final UserDetailsService userDetailsService;

//    @PostMapping("/registration")
//    public ResponseEntity<?> userRegistration(@RequestBody URegistrationDto registrationDto) {
//        UUserDto user = registrationService.registerUser(registrationDto);
//        return new ResponseEntity<>(user, new HttpHeaders(), HttpStatus.OK);
//    }

    /**
     * Validate the refresh token and generate access token
     *
     * @return access token
     */
    @GetMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String authRefreshToken) throws Exception {
        try {
            if (StringUtils.hasText(authRefreshToken)) {

                String refreshJwt = requestHandler.getJwtFromStringRequest(authRefreshToken);
                String userName = tokenProvider.getUserNameFromJWT(refreshJwt);

                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

                String accessJwtToken = tokenProvider.generateAccessToken(authentication);

                return ResponseEntity.ok(new RefreshJwtAuthenticationResponse(accessJwtToken));
            } else
                return ResponseEntity.ok("Empty Refresh Token");
        } catch (Exception ex) {
            log.error("Could not set user authentication in security context", ex.getMessage());
            return ResponseEntity.ok("Sorry!");
        }
    }

    /**
     * Validate the token
     *
     * @return status of token
     */
//    @GetMapping("/validateToken")
//    public ResponseEntity<?> validate(@RequestHeader(AuthConstants.AUTH_KEY) String authToken) throws Exception {
//        return ResponseEntity.ok(new ApiResponse(true, "Token is valid"));
//    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody AuthenticationRequest authenticationRequest) {

        if (authenticationRequest.getUsername() == "") {
            throw new BadRequestException("Username can't be null or blank.");
        }

        if (authenticationRequest.getPassword() == "") {
            throw new BadRequestException("Password can't be null or blank.");
        }

        AuthenticationResponse result = authService.authentication(authenticationRequest);
        return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
    }
}