package com.backend.psoft.authentication;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
            throws IOException, ServletException {

        chain.doFilter(request, response);
    }
}
