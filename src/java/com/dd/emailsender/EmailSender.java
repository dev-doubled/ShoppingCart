/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dd.emailsender;

import com.sendgrid.*;
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
//            System.out.println(response.getStatusCode());
//            System.out.println(response.getBody());
//            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}

