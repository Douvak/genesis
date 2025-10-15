package com.example.Genesis.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.Genesis.domain.entity.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String gerarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256("teste");
            return   JWT.create()
                    .withIssuer("Marmoraria")
                    .withSubject(usuario.getUsername())
                    .withClaim("empresaID", usuario.getEmpresa().getId())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao criar o token", exception);
        }

    }
    public String validarToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("teste");
            return  JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("Marmoraria")
                    .build().verify(token).getSubject();


        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token invalido ou expirado.");
        }
    }


    private Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
