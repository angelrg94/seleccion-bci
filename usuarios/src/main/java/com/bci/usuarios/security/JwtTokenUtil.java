package com.bci.usuarios.security;

import java.io.Serializable;
import java.util.Date;

import com.bci.usuarios.dto.UserRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = -4211581360290344026L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    @Value("${jwt.secret}")
    private  String secret;

    /**
     * Este metodo se encarga de generar un Token JWT
     * @param  user  datos del usuario
     */
    public String generateToken(UserRequestDTO user) {
        return Jwts.builder().setSubject(user.getName()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }


}