package Logins;

import Users.Citizen;
import Users.MeetingRequest;
import Users.UserController;
import Util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class UserLogin {

    public void citizenInterfaze(UserController userController){
        while (true) {
            loginMenu();
            int a = Scanner.getInt("Seleccione la accion desea realizar: ");
            switch (a) {
                case 1:
                    createCitizen(userController);
                    break;
                case 2:
                    manageCitizen(userController);
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }

    public void manageCitizen(UserController userController){

        Citizen citizen = searchCitizen(userController);

        if(citizen == null){
            System.out.println("No se encontro el usuario. Intente Nuevamente");
            return;
        }else if(citizen.isBlocked()){
            System.out.println("El usuario se encuentra bloqueado.");
            return;
        }

        while (true) {
            citizenMenu();
            int a = Scanner.getInt("Seleccione la accion desea realizar: ");
            switch (a) {
                case 1:
                    String symptom = Scanner.getString("Sintoma presenciado: ");
                    citizen.addSymptom(symptom);
                    break;
                case 2:
                    String removeSymptom = Scanner.getString("Sintoma que desea remover: ");
                    citizen.removeSymptom(removeSymptom);
                    break;
                case 3:
                    for (int i = 0; i < citizen.getSymptoms().size(); i++) {
                        System.out.println(citizen.getSymptoms().get(i));
                    }
                    break;
                case 4:
                    displayMeetingRequests(citizen, userController);
                    break;
                case 5:
                    sendMeetingRequestByCuil(citizen, userController);
                    break;
                case 6:
                    displayAttendedMeetings(citizen, userController);
                    break;
                case 7:
                    return;
                default :
                    break;

            }
        }
    }



    public Citizen createCitizen(UserController userController){
        String phoneNumber = Scanner.getString("Numero de telefono: ");
        String cuil = Scanner.getString("Cuil: ");
        Citizen citizen = new Citizen(phoneNumber, cuil);
        userController.addCitizen(citizen);
        return citizen;
    }

    public Citizen searchCitizen(UserController userController){
        String phoneNumber = Scanner.getString("Ingrese numero de telefono: ");
        String cuil = Scanner.getString("Ingrese cuil: ");
        List<Citizen> citizens = userController.getCitizens();
        for (int i = 0; i < citizens.size(); i++) {
            if(citizens.get(i).getPhoneNumber().equals(phoneNumber) && citizens.get(i).getCuil().equals(cuil)){
                return citizens.get(i);
            }
        }
        return null;
    }

    public void displayMeetingRequests(Citizen citizen, UserController userController){
        List<MeetingRequest> meetingRequests = citizen.getMeetingRequests();
        for (int i = 0; i < meetingRequests.size(); i++) {
            System.out.println("" + (i+1) + "." + meetingRequests.get(i).getSender().getCuil());
        }
        int accept = Scanner.getInt("Indique una invitacion: ");
        int action = Scanner.getInt("Seleccione 1 para aceptar o 2 para rechazar: ");
        if(action ==  1){
            citizen.acceptMeetingRequest(accept - 1);
        }else if(action == 2){
            checkIfCitizenBlock(citizen.getMeetingRequests().get(accept - 1).getSender());
            citizen.declineMeetingRequest(accept - 1);
        }
    }

    public void displayAttendedMeetings(Citizen citizen, UserController userController){
        List<MeetingRequest> meetings = citizen.getMeetings();
        for (int i = 0; i < meetings.size(); i++) {
            System.out.println("Date: " + meetings.get(i).getMeeting().getDate() + ". Participants: " + meetings.get(i).getMeeting().getParticipantsCuil());
        }
    }

    public void sendMeetingRequestByCuil(Citizen citizen, UserController userController){
        List<Citizen> requestCitizens = new ArrayList<>();
        int size = Scanner.getInt("Indique la cantidad de invitados: ");
        int date = Scanner.getInt("Indique la fecha: ");
        for (int i = 0; i < size; i++) {
            String cuil = Scanner.getString("Ingrese el cuil del invitado " + (i+1) + ": ");
            Citizen aCitizen = userController.getCitizenByCuil(cuil);
            requestCitizens.add(aCitizen);
        }
        citizen.createMeetingRequest(date, requestCitizens);
    }

    public void checkIfCitizenBlock(Citizen citizen){
        citizen.addRejecetedRequest();
        if(citizen.getRejectedRequests() >= 5){
            citizen.blockUser();
        }
    }

    public void loginMenu(){
        System.out.println("************");
        System.out.println("1. create account");
        System.out.println("2. login");
        System.out.println("3. Save and exit");
        System.out.println("************");
    }

    public void citizenMenu(){
        System.out.println("************");
        System.out.println("1. Add Symptom");
        System.out.println("2. Remove symptom");
        System.out.println("3. My symptoms");
        System.out.println("4. Display meeting requests");
        System.out.println("5. Send meeting requests");
        System.out.println("6. Display attended meetings");
        System.out.println("7. Log out");
        System.out.println("************");
    }

}
