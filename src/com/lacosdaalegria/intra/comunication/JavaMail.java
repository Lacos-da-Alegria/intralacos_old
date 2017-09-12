package com.lacosdaalegria.intra.comunication;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.lacosdaalegria.intra.hibernate.model.Voluntario;

public class JavaMail {
	
	public void emailRecuperaSenha(Voluntario voluntario, String token) {
        Properties props = new Properties();
        /** Par�metros de conex�o com servidor Gmail */
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                         protected PasswordAuthentication getPasswordAuthentication() 
                         {
                               return new PasswordAuthentication("intralacos@gmail.com", "desenvol");
                         }
                    });

        /** Ativa Debug para sess�o */
        session.setDebug(false);

        try {

              Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress("intralacos@gmail.com")); //Remetente

              Address[] toUser = InternetAddress.parse(voluntario.getEmail());  

              message.setRecipients(Message.RecipientType.TO, toUser);
              message.setSubject("Recuperando de Senha do Intra La�os");//Assunto
              message.setText("Oie " + voluntario.getNome() + " , ai esta um link para voc� realizar "
              		+ "seu reset de senha.\n  Esse link tem validade de 2 horas, certo?.\n" 
              		+ "Estamos esperando voc� em uma das atividades do La�os da Alegria:\n\n"
              		+ "link: www.intralacos.com/resetSenha?token=" + token);
              
              /**M�todo para enviar a mensagem criada*/
              Transport.send(message);


         } catch (MessagingException e) {
              throw new RuntimeException(e);
        }
  }

}
