package com.backend.psoft.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/*
* Classe responsável por contruir uma mensagem no formato  SimpleMailMessage
* e após isso enviar-la pelo método send da classe JavaMailSender
*
* Abel Antunes de Lima Neto - 117210287
 */
@Service
public class EnviaEmail {

    @Autowired
    private JavaMailSender javaMailSender;

    /*
     * Método que recebe um objeto do tipo Mensagem, transforma em SimpleMailMessage
     * e faz o envio dessa mensagem ao destinatário desejado.
     *
     * * Abel Antunes de Lima Neto - 117210287
     */
    public void enviar(Mensagem mensagem) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(mensagem.getRemetente());
        simpleMailMessage.setTo(mensagem.getDestinatarios()
                .toArray(new String[mensagem.getDestinatarios().size()]));
        simpleMailMessage.setSubject(mensagem.getAssunto());
        simpleMailMessage.setText(mensagem.getCorpo());

        javaMailSender.send(simpleMailMessage);
    }

}
