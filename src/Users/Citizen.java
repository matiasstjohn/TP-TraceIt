package Users;

import Controllers.MeetingController;
import Encounters.Date;
import Encounters.Meeting;
import Encounters.Notification;
import Events.DeclaredSymptom;
import Events.Disease;
import Controllers.DiseaseController;

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
    private List<DeclaredSymptom> declaredSymptoms;
    private List<Disease> confirmedDiseases;
    private List<Notification> notifications;

    public Citizen(String phoneNumber, String cuil){
        this.phoneNumber = phoneNumber;
        this.cuil = cuil;
        blocked = false;
        declaredSymptoms = new ArrayList<>();
        rejectedRequests = 0;
        confirmedDiseases = new ArrayList<>();
        notifications = new ArrayList<>();
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
    public void addSymptom(DeclaredSymptom declaredSymptom){
        declaredSymptoms.add(declaredSymptom);
    }

    public void recieveNotification(Notification notification){
        notifications.add(notification);
    }

    public void checkIfDisease(DiseaseController diseaseController){
        List<Disease> diseases = diseaseController.getDiseases();
        for (int i = 0; i < diseases.size(); i++) {
            int counter = 0;
            for (int j = 0; j < diseases.get(i).getSymptoms().size(); j++) {
                String symptom = diseases.get(i).getSymptoms().get(j);
                for (int k = 0; k < declaredSymptoms.size(); k++) {
                    if(declaredSymptoms.get(k).getSymptomName().equals(symptom)){
                        counter++;
                    }
                }
            }
            if(counter >= 2 && !confirmedDiseases.contains(diseases.get(i))){
                confirmedDiseases.add(diseases.get(i));
            }else if(counter < 2 && confirmedDiseases.contains(diseases.get(i))){
                confirmedDiseases.remove(diseases.get(i));
            }
        }
    }

    public List<Disease> getConfirmedDiseases(){
        return confirmedDiseases;
    }

    public void addConfirmedDisease(Disease disease){
        confirmedDiseases.add(disease);
    }

    public void removeConfirmedDisease(Disease disease){
        confirmedDiseases.add(disease);
    }

    public boolean hasDisease(Disease disease){
        return confirmedDiseases.contains(disease);
    }

    //saca un sintoma de la lista
    public void removeSymptom(DeclaredSymptom symptom){
        declaredSymptoms.remove(symptom);
    }

    //devuelve todos los sintomas que agrego el usuario
    public List<DeclaredSymptom> getSymptoms() {
        return declaredSymptoms;
    }


    public Meeting createMeeting(Date date, List<String> citizensCuil, MeetingController meetingController){
        citizensCuil.add(this.getCuil());
        Meeting meeting = new Meeting(date, this.getCuil(), citizensCuil, meetingController);
        meeting.confirmParticipant(this.getCuil());
        return meeting;
    }

    public void acceptMeeting(Meeting meeting){
        meeting.confirmParticipant(this.getCuil());
    }

    public void declineMeeting(Meeting meeting){
        meeting.declineParticipation(this.getCuil());
    }

    public boolean lastSymptomDate(Date date){
        if(declaredSymptoms.size() == 0){
            return false;
        }
        int dateDiff = declaredSymptoms.get(declaredSymptoms.size() - 1).getDate().compareDates(date);
        if(dateDiff < 24){
            return true;
        }else{
            return false;
        }
    }

    public List<Notification> getNotifications(){
        return notifications;
    }

    public List<String> allSymptomsByName(){
        List<String> symptomsByName = new ArrayList<>();
        for (int i = 0; i < declaredSymptoms.size(); i++) {
            symptomsByName.add(declaredSymptoms.get(i).getSymptomName());
        }
        return symptomsByName;
    }




}
