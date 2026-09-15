package com.example.items;

public class GmailSender {
    public GmailSender() {
    }

    public void send(String email, String message) {
        System.out.println("Email sent to " + email);
        System.out.println(message);
    }
}
