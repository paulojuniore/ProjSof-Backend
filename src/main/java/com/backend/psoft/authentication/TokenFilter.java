package com.backend.psoft.authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.apache.tomcat.jni.Time;
import org.springframework.web.bind.annotation.CrossOrigin;
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

        chain.doFilter(request, response);
    }
}
