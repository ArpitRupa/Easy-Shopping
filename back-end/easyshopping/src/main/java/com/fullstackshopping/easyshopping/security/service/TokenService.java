package com.fullstackshopping.easyshopping.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    private JwtEncoder jwtEncoder;

    private JwtDecoder jwtDecoder;

    public TokenService(){

    }

    @Autowired
    public TokenService (JwtEncoder jwtEncoder, JwtDecoder jwtDecoder){
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    /**
     * Generates a JSON Web Token (JWT) for the provided authentication.
     *
     * @param authentication The authentication object representing the user's credentials and authorities.
     * @return A JWT string containing user-specific claims, including the user's role.
     */
    public String generateJwt(Authentication authentication){

        // get current timestamp
        Instant now = Instant.now();

        // only have one role in authorities
        String scope = authentication.getAuthorities().isEmpty() ? "" : authentication.getAuthorities().iterator().next().getAuthority();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                // username of jwt holder
                .subject(authentication.getName())
                .claim("role", scope)
                // expire after 3 hours
                .expiresAt(now.plus(Duration.ofHours(3)))
                .build();

        // take JWT claims and encode into JWT via encoder and return as a string
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


    public String getUsernameFromToken(String jwtToken) {
        // Decode the JWT token
        Jwt jwt = jwtDecoder.decode(jwtToken);

        // Get the subject (username) from the decoded JWT

        return jwt.getSubject();
    }

}
