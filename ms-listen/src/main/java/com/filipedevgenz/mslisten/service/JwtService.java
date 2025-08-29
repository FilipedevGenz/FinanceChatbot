package com.filipedevgenz.mslisten.service;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@AllArgsConstructor
@Service
public class JwtService {
    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

        //so sera usado na requisao ao ms-info
        public String generateToken(String hashedNumber){
            var claims = JwtClaimsSet.builder()
                    .issuer("ms-listen")
                    .issuedAt(Instant.now())
                    .expiresAt(Instant.now().plusSeconds(300))
                    .subject(hashedNumber)
                    .build();
            return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }


}
