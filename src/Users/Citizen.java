package Users;

import java.util.ArrayList;
import java.util.List;

/*
Explicacion de las meetings y meetings requests:
Al usuario le llega una meeting request a la lista de meeting requests. si las acepta esta request se borra de esa
lista y se agreag a la lista de las requests aceptadas
 */

public class Citizen {

    private String phoneNumber;
    private String cuil;
    private boolean blocked;
    private List<String> symptoms;
    //meeting requests aceptadas
    private List<MeetingRequest> meetings;
    //meeting requests por aceptar
    private List<MeetingRequest> meetingRequests;
    private int rejectedRequests;

    public Citizen(String phoneNumber, String cuil){
        this.phoneNumber = phoneNumber;
        this.cuil = cuil;
        blocked = false;
        symptoms = new ArrayList<>();
        meetings = new ArrayList<MeetingRequest>();
        meetingRequests = new ArrayList<MeetingRequest>();
        rejectedRequests = 0;
    }

    //permite al usuario crar una meeting request y que se mande a los citizens que se le pase
    public MeetingRequest createMeetingRequest(int date, List<Citizen> citizens){
        MeetingRequest aMeetingRequest = new MeetingRequest(this, citizens, date);
        meetings.add(aMeetingRequest);
        sendMeetingRequest(aMeetingRequest);
        return aMeetingRequest;
    }

    //envia la meeting request a cada participante
    public void sendMeetingRequest(MeetingRequest meetingRequest){
        for (int i = 0; i < meetingRequest.getParticipants().size(); i++) {
            meetingRequest.getParticipants().get(i).reciveMeetingRequest(meetingRequest);
        }
    }

    //recive la meeting request y la pone en la listas de las requests por aceptar
    public void reciveMeetingRequest(MeetingRequest meetingRequest){
        meetingRequests.add(meetingRequest);
    }

    //perimte al usuario aceptar la meeting request
    public void acceptMeetingRequest(int i){ //elige cual meeting aceptar
        if(i > meetingRequests.size()){
            return;
        }
        MeetingRequest meetingRequest = meetingRequests.get(i);
        meetings.add(meetingRequest);
        meetingRequests.remove(i);
        meetingRequest.addParticipantToMeeting(this);
    }

    //permite al usuario rechazar una meeting request y esta es elimidada de sus meetings por aceptar
    public void declineMeetingRequest(int i){
        meetingRequests.remove(i);
    }

    //devuelve todas las meeting requests por aceptar
    public List<MeetingRequest> getMeetingRequests(){
        return meetingRequests;
    }

    //devuelve las meetings que acepto
    public List<MeetingRequest> getMeetings() {
        return meetings;
    }

    //devuelve el phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    //devuelve el cuil
    public String getCuil() {
        return cuil;
    }

    //devuelve la cantidad de meeting requests enviadas que le rechazaron
    public int getRejectedRequests(){
        return rejectedRequests;
    }

    //suma una meeting request rechazada
    public void addRejecetedRequest(){
        rejectedRequests++;
    }

    //devuelve true si esta bloqueado
    public boolean isBlocked(){
        return blocked;
    }

    //bloquea al citizen
    public void blockUser(){
        blocked = true;
    }

    //desbloquea al usuario
    public void unBlockUser(){
        blocked = false;
    }

    //agrega un sintoma a la lista de los sintomas que tiene
    public void addSymptom(String symptom){
        symptoms.add(symptom);
    }

    //saca un sintoma de la lista
    public void removeSymptom(String symptom){
        symptoms.remove(symptom);
    }

    //devuelve todos los sintomas que agrego el usuario
    public List<String> getSymptoms() {
        return symptoms;
    }


}
