package Logins;

import Users.Citizen;
import Users.MeetingRequest;
import Users.UserController;
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
    public void citizenInterfaze(UserController userController){
        while (true) {
            loginMenu();
            int a = Scanner.getInt("Select de action you want to perform: ");
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

    /*
    Menu al seleccionar que se desea iniciar sesion como ciudadano
     */
    public void manageCitizen(UserController userController){

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


    /*
    Crea un usuario citizen a partir de un phone number y un numero de cuil
    Luego lo agrega a la lista de citizens
     */
    public Citizen createCitizen(UserController userController){
        String phoneNumber = Scanner.getString("Phone Number: ");
        String cuil = Scanner.getString("Cuil: ");
        Citizen citizen = new Citizen(phoneNumber, cuil);
        userController.addCitizen(citizen);
        return citizen;
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

    /*
    Muestra las meeting requests de un citizen y le da la opcion de aceptarla o rechazarla
     */
    public void displayMeetingRequests(Citizen citizen, UserController userController){
        List<MeetingRequest> meetingRequests = citizen.getMeetingRequests();
        for (int i = 0; i < meetingRequests.size(); i++) {
            System.out.println("" + (i+1) + "." + meetingRequests.get(i).getSender().getCuil());
        }
        int accept = Scanner.getInt("Select a request: ");
        int action = Scanner.getInt("Select 1 to accept or 2 to decline: ");
        if(action ==  1){
            citizen.acceptMeetingRequest(accept - 1);
        }else if(action == 2){
            checkIfCitizenBlock(citizen.getMeetingRequests().get(accept - 1).getSender());
            citizen.declineMeetingRequest(accept - 1);
        }
    }

    /*
    Muestra las meetings a las que un citizen asistio
     */
    public void displayAttendedMeetings(Citizen citizen, UserController userController){
        List<MeetingRequest> meetings = citizen.getMeetings();
        for (int i = 0; i < meetings.size(); i++) {
            System.out.println("Date: " + meetings.get(i).getMeeting().getDate() + ". Participants: " + meetings.get(i).getMeeting().getParticipantsCuil());
        }
    }

    /*
    Le permite a un citizen mandar una meeting request ingresando la cantidad de gente, la fecha(por ahora es un int)
    y los nummero de cuil de las personas que asistieron
     */
    public void sendMeetingRequestByCuil(Citizen citizen, UserController userController){
        List<Citizen> requestCitizens = new ArrayList<>();
        int size = Scanner.getInt("Enter the amount of people: ");
        int date = Scanner.getInt("Enter the date: ");
        for (int i = 0; i < size; i++) {
            String cuil = Scanner.getString("Enter guests cuil " + (i+1) + ": ");
            Citizen aCitizen = userController.getCitizenByCuil(cuil);
            requestCitizens.add(aCitizen);
        }
        citizen.createMeetingRequest(date, requestCitizens);
    }

    /*
    se  ejecuta si alguien rechaza una meeting request
    suma 1  a las rejected requests del citizen que envio la request
    y luego si este llego a 5 requests rechazadas lo bloquea
     */
    public void checkIfCitizenBlock(Citizen citizen){
        citizen.addRejecetedRequest();
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
