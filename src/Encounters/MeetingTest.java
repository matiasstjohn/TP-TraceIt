package Encounters;

import Controllers.MeetingController;

import java.util.ArrayList;
import java.util.List;

public class MeetingTest {

    Date date;
    List<String> acceptedParticipants;
    List<String> sendTo;
    String sender;

    public MeetingTest(Date date, String sender, List<String> sendTo, MeetingController meetingController) {
        this.date = date;
        this.acceptedParticipants = new ArrayList<>();
        this.sendTo = sendTo;
        this.sender = sender;
        meetingController.addMeeting(this);
    }

    public String getSender(){
        return sender;
    }

    public Date getDate(){
        return date;
    }
/*
    public String getDateAsString(){
        return date.getMonth().toString() + date.getDay().toString() + date.getHours().toString();
    }
*/

    public void confirmParticipant(String citizenCuil){
        if(sendTo.contains(citizenCuil)){
            acceptedParticipants.add(citizenCuil);
            sendTo.remove(citizenCuil);
        }
    }

    public void declineParticipation(String citizenCuil){
        if(sendTo.contains(citizenCuil)){
            sendTo.remove(citizenCuil);
        }
    }

    public List<String> getSendTo(){
        return sendTo;
    }

    public List<String> getAcceptedParticipants(){
        return acceptedParticipants;
    }


}
