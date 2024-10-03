package com.iotdehumidifier.iotdehumidifier.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.iotdehumidifier.iotdehumidifier.models.ArduinoObject;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public boolean sendMail(ArduinoObject response) {
    
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated()) {
            try {
                String userEmail = authentication.getName(); 
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(userEmail);
                message.setSubject("Dehumidifier Service Notification");
                message.setText("Hi! \n\tThe humidity reading is currently " + response.getHumidity() + 
                                    "%. It might be a good idea to turn on your dehumidifier!\n\n\tKind regards,\n\n\t\tYour Arduino.");
                message.setFrom("noreplydehumidifier@gmail.com");
                
                javaMailSender.send(message);
                return true;

            } catch (MailSendException e) {
                return false;
            }
        } else {
            return false;
        }
    }
    
}
