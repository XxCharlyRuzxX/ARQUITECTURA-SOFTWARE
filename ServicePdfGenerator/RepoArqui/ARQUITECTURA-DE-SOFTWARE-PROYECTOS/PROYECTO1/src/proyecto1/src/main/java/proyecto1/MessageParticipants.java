package proyecto1;

public class MessageParticipants {

    private String sender;
    private String adresser;

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