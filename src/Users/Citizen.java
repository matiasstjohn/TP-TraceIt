package Users;

import Controllers.MeetingController;
import Encounters.Date;
import Encounters.MeetingTest;

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
    private int rejectedRequests;
    private List<String> symptoms;

    public Citizen(String phoneNumber, String cuil){
        this.phoneNumber = phoneNumber;
        this.cuil = cuil;
        blocked = false;
        symptoms = new ArrayList<>();
        rejectedRequests = 0;
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
    public void addRejectedRequest(){
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


    public MeetingTest createMeetingTest(Date date, List<String> citizensCuil, MeetingController meetingController){
        citizensCuil.add(this.getCuil());
        MeetingTest meetingTest = new MeetingTest(date, this.getCuil(), citizensCuil, meetingController);
        meetingTest.confirmParticipant(this.getCuil());
        return meetingTest;
    }

    public void acceptMeetingTest(MeetingTest meetingTest){
        meetingTest.confirmParticipant(this.getCuil());
    }

    public void declineMeetingTest(MeetingTest meetingTest){
        meetingTest.declineParticipation(this.getCuil());
    }

}
