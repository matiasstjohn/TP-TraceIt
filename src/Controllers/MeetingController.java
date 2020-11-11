package Controllers;

import Encounters.Date;
import Encounters.Meeting;
import Encounters.Notification;
import Users.Citizen;

import java.util.ArrayList;
import java.util.List;

public class MeetingController {

    List<Meeting> meetings;

    public MeetingController(){
        meetings = new ArrayList<>();
    }

    //devuelve las requests que todavia no acepto ni rechazo un citizen
    public List<Meeting> searchCitizenMeetingRequests(String cuil){
        List<Meeting> meetingsRecived = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            if(meetings.get(i).getSendTo().contains(cuil)){
                meetingsRecived.add(meetings.get(i));
            }
        }
        return meetingsRecived;
    }

    //busca las requests que un citizen acepto
    public List<Meeting> searchCitizenAcceptedRequests(String cuil){
        List<Meeting> meetingsAccepted = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            if(meetings.get(i).getAcceptedParticipants().contains(cuil)){
                meetingsAccepted.add(meetings.get(i));
            }
        }
        return meetingsAccepted;
    }

    public void searchCitizenMeetings48Hours(Date date, Citizen citizen, String symptom, UserController userController){
        List<Meeting> meetings = searchCitizenAcceptedRequests(citizen.getCuil());
        for (int i = 0; i < meetings.size(); i++) {
            int dateDiff = meetings.get(i).getDate().compareDates(date);
            if(dateDiff < 48){
              Meeting meeting = meetings.get(i);
                for (int j = 0; j < meeting.getSendTo().size(); j++) {
                    Citizen reciever = userController.getCitizenByCuil(meeting.getSendTo().get(j));
                    if(reciever.getCuil().equals(citizen.getCuil())){
                        continue;
                    }
                    List<String> symptomsByName = citizen.allSymptomsByName();
                    symptomsByName.add(symptom);
                    reciever.recieveNotification(new Notification(citizen.getCuil(), symptomsByName));
                }
            }
        }
    }

    public void addMeeting(Meeting meeting){
        meetings.add(meeting);
    }





}
