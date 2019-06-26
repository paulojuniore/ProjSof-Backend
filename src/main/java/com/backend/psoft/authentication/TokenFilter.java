package com.backend.psoft.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.apache.tomcat.jni.Time;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

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
            throws IOException, ServletException {

        String header = ((HttpServletRequest) request).getHeader("Authorization");
        System.out.println(header);


        //if (header == null || !header.startsWith("Bearer")) {
          //  throw new ServletException("Token inexistente ou mal formatado!");
        //}

        //Extraindo apenas o token do cabecalho
        //String token = header.substring(7);

       //try {
         //   Jwts.parser().setSigningKey("psoft").parseClaimsJws(token).getBody();
        //} catch(SignatureException e){
          //  throw new ServletException("Token invalido ou expirado!");

       //}

        chain.doFilter(request, response);
    }
}
