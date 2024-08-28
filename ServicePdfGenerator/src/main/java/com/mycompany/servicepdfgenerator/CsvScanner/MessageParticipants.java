package com.mycompany.servicepdfgenerator.CsvScanner;


public class MessageParticipants {

    private final String sender;
    private final String adresser;

    public MessageParticipants(String sender, String adresser){
        this.sender = sender;
        this.adresser = adresser;
    }

    public String getAdresser() {
        return adresser;
    }

    public String getSender() {
        return sender;
    }

}
