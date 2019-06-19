package com.backend.psoft.controller;

import com.backend.psoft.util.EnviaEmail;
import com.backend.psoft.util.Mensagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;

@EnableAutoConfiguration
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/")
    public String test (){
        return "ola";
    }

    @Autowired
    //pivate JavaMailSender mailSender;
    private  EnviaEmail enviaEmail;

    /*
    Método que envia um email para um determinado usuario,
    Pode ser definido um nome de Remetente, um Usuário, um Assunto e
    o Corpo da mensagem, é necessario o email do destinatário.
     */
    @RequestMapping(path = "/email-send", method = RequestMethod.GET)
    public String sendMail() {
        Mensagem mensagemTest = new Mensagem("Avalia Disciplinas <avaliadisciplinasufcg@gmail.com>",
                Arrays.asList("Abel Antunes <abel.neto@ccc.ufcg.edu.br>"),"Teste de envio",
                        "Olá, estou testando!");
        try {
            enviaEmail.enviar(mensagemTest);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar email.";
        }
    }

}
