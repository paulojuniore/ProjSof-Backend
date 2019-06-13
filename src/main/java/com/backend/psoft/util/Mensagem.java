package com.backend.psoft.util;

import java.util.List;

/*
 * Classe que representa o modelo de uma mensagem à ser enviada,
 * Atributos do construtor:
 * - remetente - "nome <email>"
 * - lista de destinatarios - "nome <email>"
 * - assunto - "Assunto"
 * - corpo - "corpo da mensagem"
 *
 * * Abel Antunes de Lima Neto - 117210287
 */
public class Mensagem {

    private String remetente;

    private List<String> destinatarios;

    private String assunto;

    private String corpo;

    public Mensagem(String remetente, List<String> destinatarios,
                    String assunto, String corpo) {
        this.remetente = remetente;
        this.destinatarios = destinatarios;
        this.assunto = assunto;
        this.corpo = corpo;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public List<String> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<String> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

}
