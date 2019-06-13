package com.backend.psoft.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/*
 * Classe responsavel pela configuração do sistema de envio de e-mails
 * precisa da anotação @Configuration para que o sistema identifique como uma
 * classe de configrações.
 *
 * Usa os dados do arquivo email.properties na pasta resources.
 *
 * * Abel Antunes de Lima Neto - 117210287
 */
@Configuration

// Caminho do arquivo de configurações.
@PropertySource("classpath:/email.properties")
public class EmailConfig {

    // Fornece controle sobre onde e como a ligação entre os beans deve ser realizada.
    @Autowired
    private Environment env;

    /*
     * Anotação que serve para o sistema saber que o resultado dessas configurações serão
     * usados posteriormente.
     */
    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(env.getProperty("mail.smtp.host"));
        mailSender.setPort(env.getProperty("mail.smtp.port", Integer.class));
        mailSender.setUsername(env.getProperty("mail.smtp.username"));
        mailSender.setPassword(env.getProperty("mail.smtp.password"));

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.connectiontimeout", 10000);

        mailSender.setJavaMailProperties(props);

        return mailSender;
    }
}
