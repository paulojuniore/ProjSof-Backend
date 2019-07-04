package com.backend.psoft.util;

import java.util.Arrays;

/*
 *Classe para E-mail de boas vindas, criada pelo fato de ser um E-mail generico,
 * só muda o remetente.
 *
 * Está enviando um link com um token único para o e-mail do usuário, o cadastro
 * só é confirmado quando ele clica no link.
 * Parametros: Nome do destinatario já concatenado - "nome sobrenome"
 *             Email do destinatario
 *
 * Abel Antunes de Lima Neto - 117210287
 */
public class EmailBoasVindas {

    private String destNome, destEmail, remetente, assunto, mensagem;

    public EmailBoasVindas(String destNome, String destEmail, String token) {
        this.destNome = destNome;
        this.destEmail = destEmail;
        this.remetente = "Avalia Disciplinas UFCG <avaliadisciplinasufcg@gmail.com>";
        this.assunto = "Bem vindo ao Avalia Disciplinas UFCG!!";
        this.mensagem = "Seja bem vindo ao sistema de avaliações de disciplinas da UFCG!\n\n" +
        "https://projsof.herokuapp.com/api/users/verify/" + token;
    }


    /*
     * Esse método converte a mensagem de boas vindas em uma mensagem do tipo reconhecido pelo
     * método de envio.
     *
     * Abel Antunes de Lima Neto - 117210287
     */
    public Mensagem converteMensagem() {

        String destinatario = this.destNome + " <" + this.destEmail + ">";
        Mensagem retorno = new Mensagem(this.remetente, Arrays.asList(destinatario), this.assunto, this.mensagem);

        return retorno;
    }
}
