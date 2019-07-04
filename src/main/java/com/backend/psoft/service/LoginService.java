package com.backend.psoft.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

/*
 * Classe que possui os serviços de um Login
 *
 * Abel Antunes de Lima Neto - 117210287
 */
@Service
public class LoginService {

    private HashMap<String, String> usersLog;

    public LoginService() {
        this.usersLog = new HashMap<String, String>();
    }
    
    public void addLogin(String email, String token) {
        usersLog.put(email, token);
    }

    /*
     * Retorna se um usuario esta logado
     */
    public Boolean getUserLogin(String token) {
        return  this.usersLog.containsValue(token);
    }

    public String getEmailUserLogin (String token) {
        return this.getEmailByToken(token);
    }

    private String getEmailByToken(String token) {
        for(String key : this.usersLog.keySet()){
            if(this.usersLog.get(key).equals(token)){
                return key; //return the first found
            }
        }
        return null;
    }

    /*
     * Apaga o token verificador de um usuário quando ele sai do sistema
     * Abel Antunes de Lima Neto - 117210287
     */
    public void logoutUser (String email) {
        if(this.usersLog.containsKey(email)) {
            this.usersLog.remove(email);
        }
    }
}
