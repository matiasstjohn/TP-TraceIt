package Users;

import java.util.ArrayList;
import java.util.List;

public class Citizen {

    private String phoneNumber;
    private String cuil;
    private boolean blocked;
    private List<String> symptoms;
    //aceptadas
    private List<MeetingRequest> meetings;
    //por aceptar
    private List<MeetingRequest> meetingRequests;
    private int rejectedRequests;

    public Citizen(String phoneNumber, String cuil){
        this.phoneNumber = phoneNumber;
        this.cuil = cuil;
        blocked = false;
        symptoms = new ArrayList<>();
        meetings = new ArrayList<MeetingRequest>(); // requests aceptadas
        meetingRequests = new ArrayList<MeetingRequest>(); // requests por aceptar
        rejectedRequests = 0;
    }

    public MeetingRequest createMeetingRequest(int date, List<Citizen> citizens){
        MeetingRequest aMeetingRequest = new MeetingRequest(this, citizens, date);
        meetings.add(aMeetingRequest);
        sendMeetingRequest(aMeetingRequest);
        return aMeetingRequest;
    }

    public void sendMeetingRequest(MeetingRequest meetingRequest){
        for (int i = 0; i < meetingRequest.getParticipants().size(); i++) {
            meetingRequest.getParticipants().get(i).reciveMeetingRequest(meetingRequest);
        }
    }

    public void reciveMeetingRequest(MeetingRequest meetingRequest){
        meetingRequests.add(meetingRequest);
    }

    public void acceptMeetingRequest(int i){ //elige cual meeting aceptar
        if(i > meetingRequests.size()){
            return;
        }
        MeetingRequest meetingRequest = meetingRequests.get(i);
        meetings.add(meetingRequest);
        meetingRequests.remove(i);
        meetingRequest.addParticipantToMeeting(this);
    }

    public void declineMeetingRequest(int i){
        meetingRequests.remove(i);
    }

    public List<MeetingRequest> getMeetingRequests(){
        return meetingRequests;
    }

    public List<MeetingRequest> getMeetings() {
        return meetings;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCuil() {
        return cuil;
    }

    public int getRejectedRequests(){
        return rejectedRequests;
    }

    public void addRejecetedRequest(){
        rejectedRequests++;
    }

    public boolean isBlocked(){
        return blocked;
    }

    public void blockUser(){
        blocked = true;
    }

    public void unBlockUser(){
        blocked = false;
    }

    public void addSymptom(String symptom){
        symptoms.add(symptom);
    }

    public void removeSymptom(String symptom){
        symptoms.remove(symptom);
    }

    public List<String> getSymptoms() {
        return symptoms;
    }


}
