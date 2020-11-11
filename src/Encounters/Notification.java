package Encounters;

import java.util.List;

public class Notification {

    String senderCuil;
    List<String> symptoms;

    public Notification(String senderCuil, List<String> symptoms) {
        this.senderCuil = senderCuil;
        this.symptoms = symptoms;
    }

    public String getSenderCuil() {
        return senderCuil;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }
}
