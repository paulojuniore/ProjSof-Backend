package com.backend.psoft.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.filter.GenericFilterBean;

import com.backend.psoft.exception.login.InvalidTokenException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*
 * Classe responsável por filtar Url's privados
 * será necessário um token de autenticação para visitar
 * essas rotas.
 * 
 * @author Abel Antunes de Lima 
 *
 */
public class TokenFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws InvalidTokenException, IOException, ServletException {
        String token = ((HttpServletRequest) request).getHeader("Authorization");
        // Se for null, nao veio token
        if (token != null && token.split("Bearer ").length < 2) {
           throw new InvalidTokenException("Token vazio.");
        }
        if (token != null && !token.startsWith("Bearer")) {
            throw new InvalidTokenException("Token inexistente ou mal formatado!");
        }
        // Se o token nao esta no mapa, o usuario nao esta logado
        if (token != null) {
            token = token.substring(7);
            try {
                Jwts.parser().setSigningKey("psoft").parseClaimsJws(token).getBody();
            } catch(SignatureException e){
                     throw new InvalidTokenException("Token invalido ou expirado!");
            }
        }
        chain.doFilter(request, response);
    }
}
