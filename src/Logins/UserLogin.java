package Logins;

import Anses.Anses;
import Controllers.MeetingController;
import Controllers.UserController;
import Encounters.MeetingTest;
import Users.*;
import Util.Scanner;

import java.util.ArrayList;
import java.util.List;

/*
Esta clase funciona como menu de los citizens
 */

public class UserLogin {

    /*
    Primer menu al seleccionar que se quiere ingresar como citizen.
    Da la opcion de crear una cuenta o de ingresar a una ya creada
     */
    public void citizenInterfaze(UserController userController, MeetingController meetingController, Anses anses){
        while (true) {
            loginMenu();
            int a = Scanner.getInt("Select de action you want to perform: ");
            switch (a) {
                case 1:
                    createCitizen(userController, anses);
                    break;
                case 2:
                    manageCitizen(userController, meetingController);
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }

    /*
    Menu al seleccionar que se desea iniciar sesion como ciudadano
     */
    public void manageCitizen(UserController userController, MeetingController meetingController){

        //Busca un ciudadano
        Citizen citizen = searchCitizen(userController);

        //Condiciones para que el citizen pueda acceder
        if(citizen == null){
            System.out.println("User was not found. Try again.");
            return;
        }else if(citizen.isBlocked()){
            System.out.println("The user is currently blocked.");
            return;
        }
        /*
        Se despliega un menu para que el citizen elija que accion desea realizar
         */
        while (true) {
            citizenMenu();
            int a = Scanner.getInt("Select de action you want to perform: ");
            switch (a) {
                case 1:
                    String symptom = Scanner.getString("Preceded symptom: ");
                    citizen.addSymptom(symptom);
                    break;
                case 2:
                    String removeSymptom = Scanner.getString("Remove symptom: ");
                    citizen.removeSymptom(removeSymptom);
                    break;
                case 3:
                    for (int i = 0; i < citizen.getSymptoms().size(); i++) {
                        System.out.println(citizen.getSymptoms().get(i));
                    }
                    break;
                case 4:
                    displayMeetingRequestsTest(citizen, userController, meetingController);
                    break;
                case 5:
                    sendMeetingRequestByCuilTest(citizen, userController, meetingController);
                    break;
                case 6:
                    displayAttendedMeetingsTest(citizen, meetingController);
                    break;
                case 7:
                    return;
                default :
                    break;

            }
        }
    }


    /*
    Crea un usuario citizen a partir de un phone number y un numero de cuil
    Luego lo agrega a la lista de citizens
     */
    public Citizen createCitizen(UserController userController, Anses anses){
        String phoneNumber = Scanner.getString("Phone Number: ");
        String cuil = Scanner.getString("Cuil: ");
        if(anses.residentExists(cuil, phoneNumber)){
            Citizen citizen = new Citizen(phoneNumber, cuil);
            userController.addCitizen(citizen);
            System.out.println("Citizen registred correctly.");
            return citizen;
        }
        System.out.println("The citizen was not found in Anses data base.");
        return null;
    }

    /*
    Busca un citizen a partir de su phone number y su cil
     */
    public Citizen searchCitizen(UserController userController){
        String phoneNumber = Scanner.getString("Enter your phone number: ");
        String cuil = Scanner.getString("Enter your cuil: ");
        List<Citizen> citizens = userController.getCitizens();
        for (int i = 0; i < citizens.size(); i++) {
            if(citizens.get(i).getPhoneNumber().equals(phoneNumber) && citizens.get(i).getCuil().equals(cuil)){
                return citizens.get(i);
            }
        }
        return null;
    }

    public void displayMeetingRequestsTest(Citizen citizen, UserController userController, MeetingController meetingController){
        List<MeetingTest> meetingTests = meetingController.searchCitizenMeetingRequests(citizen.getCuil());
        for (int i = 0; i < meetingTests.size(); i++) {
            System.out.println("" + (i+1) + "." + meetingTests.get(i).getSender());
        }
        int accept = Scanner.getInt("Select a request: ");
        if(accept - 1 > meetingTests.size()){
            return;
        }
        int action = Scanner.getInt("Select 1 to accept or 2 to decline: ");
        if(action ==  1){
            citizen.acceptMeetingTest(meetingTests.get(accept - 1));
        }else if(action == 2){
            checkIfCitizenBlockTest(userController.getCitizenByCuil(meetingTests.get(accept-1).getSender()));
            citizen.declineMeetingTest(meetingTests.get(accept - 1));
        }
    }

    public void sendMeetingRequestByCuilTest(Citizen citizen, UserController userController, MeetingController meetingController){
        List<String> requestCitizens = new ArrayList<>();
        int size = Scanner.getInt("Enter the amount of people: ");
        int date = Scanner.getInt("Enter the date: ");
        for (int i = 0; i < size; i++) {
            String cuil = Scanner.getString("Enter guests cuil " + (i+1) + ": ");
            requestCitizens.add(cuil);
        }
        citizen.createMeetingTest(date, requestCitizens, meetingController);
    }

    public void displayAttendedMeetingsTest(Citizen citizen, MeetingController meetingController){
        List<MeetingTest> meetings = meetingController.searchCitizenAcceptedRequests(citizen.getCuil());
        for (int i = 0; i < meetings.size(); i++) {
            System.out.print("Date: " + meetings.get(i).getDate() + ". Participants: ");
            for (int j = 0; j < meetings.get(i).getAcceptedParticipants().size(); j++) {
                System.out.print("" + meetings.get(i).getAcceptedParticipants().get(j) + ", ");
            }
            System.out.print("\n");
        }
    }

    public void checkIfCitizenBlockTest(Citizen citizen){
        citizen.addRejectedRequest();
        if(citizen.getRejectedRequests() >= 5){
            citizen.blockUser();
        }
    }

    //menu
    public void loginMenu(){
        System.out.println("************");
        System.out.println("1. create account");
        System.out.println("2. login");
        System.out.println("3. Save and exit");
        System.out.println("************");
    }

    //menu
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
