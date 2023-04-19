/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dd.emailsender;


import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;

public class EmailSender {

    private final String API_KEY = "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";
    private String EMAIL_SHOP;

    public EmailSender(String emailShop) {
        EMAIL_SHOP = emailShop;
    }
    
    public void SendMessage(String toEmail, String subject, String message) throws IOException
    {
        Email from = new Email(EMAIL_SHOP);
        Email to = new Email(toEmail);
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(API_KEY);
        Request request = new Request();
        
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}

