package com.bruquest.bruquestapi.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bruquest.bruquestapi.dto.userDTO.UserDTO;
import com.bruquest.bruquestapi.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC256;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Component
public class UserAuthProvider {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secret;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(UserDTO userDTO) {
        return JWT.create()
                .withIssuer(userDTO.username())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 86400000))
                .withClaim("id", userDTO.id())
                .withClaim("email", userDTO.email())
                .withClaim("role", userDTO.role())
                .sign(HMAC256(secret.getBytes()));
    }

    public Authentication validateToken(String token) {
        Algorithm algorithm = HMAC256(secret);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        UserDTO userDTO = new UserDTO(
                decodedJWT.getClaim("id").asLong(),
                decodedJWT.getIssuer(),
                decodedJWT.getClaim("email").asString(),
                decodedJWT.getClaim("role").asString()
        );

        return new UsernamePasswordAuthenticationToken(userDTO, null, null);
    }


}
