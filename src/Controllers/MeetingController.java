package Controllers;

import Anses.Anses;
import Encounters.Date;
import Encounters.Meeting;
import Encounters.Notification;
import Events.DeclaredSymptom;
import Events.Disease;
import Exceptions.InvalidDate;
import Main.TesterNuevo;
import Users.Citizen;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingController {

    List<Meeting> meetings;
    String filePath = "src/Persistencia/Meetings";

    public MeetingController() throws IOException, InvalidDate {
        //meetings = new ArrayList<>();
        meetings = getMeetingFromFile();
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
                for (int j = 0; j < meeting.getAcceptedParticipants().size(); j++) {
                    Citizen reciever = userController.getCitizenByCuil(meeting.getAcceptedParticipants().get(j));
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


    public List<Citizen> searchCitizensRelatedWithSameDiseaseIn48Hours(Anses anses, Date date, Citizen citizen, Disease disease, UserController userController){
        List<Meeting> meetings = searchCitizenAcceptedRequests(citizen.getCuil());
        List<Citizen> relatedCitizens = new ArrayList<>();
        for (int i = 0; i < meetings.size(); i++) {
            int dateDiff = meetings.get(i).getDate().compareDates(date);
            if(dateDiff < 48){
                Meeting meeting = meetings.get(i);
                for (int j = 0; j < meeting.getAcceptedParticipants().size(); j++) {
                    Citizen closeCitizen = userController.getCitizenByCuil(meeting.getAcceptedParticipants().get(j));
                    if(closeCitizen.getCuil().equals(citizen.getCuil())){
                        continue;
                    }
                    // si tiene la disease y vive en el mismo lugar y si no esta en la lista: lo agrega
                    if(closeCitizen.containsDisease(disease) && anses.getResidentLocation(citizen.getCuil()).equals(anses.getResidentLocation(closeCitizen.getCuil())) && !relatedCitizens.contains(closeCitizen)){
                        relatedCitizens.add(closeCitizen);
                    }
                }
            }
        }
        return relatedCitizens;
    }

    public void addMeeting(Meeting meeting){
        meetings.add(meeting);
    }

    private List<Meeting> getMeetingFromFile() throws IOException, InvalidDate {
        List<Meeting> meetingsAux = new ArrayList<>();

        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String userInfo;
        while ((userInfo = br.readLine()) != null) {
            String[] userParts = userInfo.split(";");
            ArrayList accepted = new ArrayList(Arrays.asList(userParts[2].split(",")));
            //ArrayList sendTo = new ArrayList(Arrays.asList(userParts[3].split(",")));
            ArrayList sendTo = new ArrayList();
            if(userParts.length > 3) {
              sendTo = new ArrayList(Arrays.asList(userParts[3].split(",")));
            }
            Meeting meeting = new Meeting(userParts[0], MetodosPersistencia.toDate(userParts[1]), accepted, sendTo);
            meetingsAux.add(meeting);
        }

        return meetingsAux;
    }

    /*public static Date toDate(String date) throws InvalidDate {
        Integer month =Integer.parseInt(String.valueOf(date.substring(0,2)));
        Integer day = Integer.parseInt(String.valueOf(date.substring(3,5)));
        Integer hours = Integer.parseInt(String.valueOf(date.substring(6,8)));
        return new Date(month,day,hours);
    }*/

    public void writeMeetingsToFile(){
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Meeting meeting : meetings) {
                fw.write(meeting.getSender() + ";" + meeting.getDate().toString() + ";" + TesterNuevo.listToString(meeting.getAcceptedParticipants()) + ";" + TesterNuevo.listToString(meeting.getSendTo()) +"\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
