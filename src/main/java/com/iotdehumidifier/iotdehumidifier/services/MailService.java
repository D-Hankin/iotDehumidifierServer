package com.iotdehumidifier.iotdehumidifier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.iotdehumidifier.iotdehumidifier.models.ArduinoObject;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(ArduinoObject response) {
        System.out.println("send email here");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("d_hankin@outlook.com");
        message.setSubject("Dehumidifier Service Notification");
        message.setText("Hi! \n\tThe humidity reading is currently " + response.getHumidity() +"%. It might be a good idea to turn on your dehumidifier!\n\n\tKind regards,\n\n\t\tYour Arduino.");
        message.setFrom("noreplydehumidifier@gmail.com");
        
        javaMailSender.send(message);
    }
    
}
