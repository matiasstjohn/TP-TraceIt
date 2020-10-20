package Controllers;

import Encounters.MeetingTest;

import java.util.ArrayList;
import java.util.List;

public class MeetingController {

    List<MeetingTest> meetings;

    public MeetingController(){
        meetings = new ArrayList<>();
    }

    public List<MeetingTest> searchCitizenMeetingRequests(String cuil){
        List<MeetingTest> meetingsRecived = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            if(meetings.get(i).getSendTo().contains(cuil)){
                meetingsRecived.add(meetings.get(i));
            }
        }
        return meetingsRecived;
    }

    public List<MeetingTest> searchCitizenAcceptedRequests(String cuil){
        List<MeetingTest> meetingsAccepted = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            if(meetings.get(i).getAcceptedParticipants().contains(cuil)){
                meetingsAccepted.add(meetings.get(i));
            }
        }
        return meetingsAccepted;
    }

    public void addMeeting(MeetingTest meetingTest){
        meetings.add(meetingTest);
    }





}
