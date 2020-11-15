package Users;

import Anses.Anses;
import Controllers.MeetingController;
import Controllers.OutbreakController;
import Controllers.UserController;
import Encounters.*;
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

    public Citizen(String phoneNumber, String cuil, boolean blocked, int rejectedRequests, List<DeclaredSymptom> declaredSymptoms, List<Disease> confirmedDiseases) {
        this.phoneNumber = phoneNumber;
        this.cuil = cuil;
        this.blocked = blocked;
        this.rejectedRequests = rejectedRequests;
        this.declaredSymptoms = declaredSymptoms;
        this.confirmedDiseases = confirmedDiseases;
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

    public boolean containsDisease(Disease disease){
        return confirmedDiseases.contains(disease);
    }

    //agrega un sintoma a la lista de los sintomas que tiene
    public void addSymptom(DeclaredSymptom declaredSymptom){
        declaredSymptoms.add(declaredSymptom);
    }

    public void recieveNotification(Notification notification){
        notifications.add(notification);
    }

    public void checkIfDisease(DiseaseController diseaseController, Date date, MeetingController meetingController, OutbreakController outbreakController, UserController userController, Anses anses){
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
                manageOutbreaks(meetingController, outbreakController, anses, date, diseases.get(i), userController);
            }else if(counter < 2 && confirmedDiseases.contains(diseases.get(i))){
                confirmedDiseases.remove(diseases.get(i));
            }
        }
    }

    public void manageOutbreaks(MeetingController meetingController, OutbreakController outbreakController, Anses anses, Date date, Disease disease, UserController userController){
        List<Citizen> relatedCitizens = meetingController.searchCitizensRelatedWithSameDiseaseIn48Hours(anses, date, this, disease, userController);
        List<OutbreakAux> possibleOutbreaksAux = new ArrayList<>();
        List<Outbreak> possibleOutbreaks = new ArrayList<>();
        for (int i = 0; i < relatedCitizens.size(); i++) {
            List<Outbreak> relatedCitizenOutbrakes = outbreakController.checkCitizenRelatedOutbreaks(relatedCitizens.get(i), disease);
            for (int j = 0; j < relatedCitizenOutbrakes.size(); j++) {
                if(!possibleOutbreaks.contains(relatedCitizenOutbrakes.get(j))){
                    possibleOutbreaksAux.add( new OutbreakAux(relatedCitizenOutbrakes.get(j), relatedCitizens.get(i)));
                    possibleOutbreaks.add(relatedCitizenOutbrakes.get(j));
                }
            }
        }

        /*if(!possibleOutbreaks.contains(relatedCitizenOutbrakes.get(j))){
            possibleOutbreaks.add( new OutbreakAux(relatedCitizenOutbrakes.get(j), relatedCitizens.get(i)));
        }*/

        for (int i = 0; i < possibleOutbreaksAux.size(); i++) {
           possibleOutbreaksAux.get(i).checkSecondGrade();
           possibleOutbreaksAux.get(i).getOutbreak().addCitizen(this);
           possibleOutbreaksAux.get(i).getOutbreak().changeLastDate(date);
        }

        if(possibleOutbreaksAux.size() == 0){
            Outbreak newOutbreak = new Outbreak(disease, anses.getResidentLocation(this.getCuil()), date);
            newOutbreak.addCitizen(this);
            outbreakController.addOutbrake(newOutbreak);
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

    public void checkToRemoveDisease(DiseaseController diseaseController) {
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
            if(counter < 2 && confirmedDiseases.contains(diseases.get(i))){
                confirmedDiseases.remove(diseases.get(i));
            }
        }
    }

    public boolean containsSymptom(String symptomName) {
        for (int i = 0; i < declaredSymptoms.size(); i++) {
            if(declaredSymptoms.get(i).getSymptomName().equals(symptomName)){
                return true;
            }
        }
        return false;
    }

    public String declaredSymptomsToString(){
        String symptomsString = "";
        for (int i = 0; i < declaredSymptoms.size(); i++) {
            if(symptomsString.equals("")){
                symptomsString = symptomsString + declaredSymptoms.get(i).toString();
            }else{
                symptomsString = symptomsString + "," + declaredSymptoms.get(i).toString();
            }

        }
        return symptomsString;
    }

    public String diseasesToString(){
        String symptomsString = "";
        for (int i = 0; i < confirmedDiseases.size(); i++) {
            if(symptomsString.equals("")){
                symptomsString = symptomsString + confirmedDiseases.get(i).getDiseaseName();
            }else{
                symptomsString = symptomsString + "," + confirmedDiseases.get(i).getDiseaseName();
            }

        }
        return symptomsString;
    }

}
