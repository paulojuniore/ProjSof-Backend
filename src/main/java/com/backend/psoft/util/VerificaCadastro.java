package com.backend.psoft.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;

/*
 *Classe que gera um token para um usuário no momento de seu cadastro
 * esse token é enviado por e-mail e serve para confirmar o cadastro do
 * usuário;
 *
 * Abel Antunes de Lima Neto - 117210287
 */
@Service
public class VerificaCadastro {


    private final String TOKEN_KEY = "psoft";

    public VerificaCadastro() {

    }

    // Método que gera o token para confirmar o usuário
    @CrossOrigin
    public String geraToken (String email) {

        String token = Jwts.builder()
                .setSubject(email)
                .signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
                .setExpiration(new Date(System.currentTimeMillis() + 50 * 60 * 1000))
                .compact();

        return token;
    }
}
