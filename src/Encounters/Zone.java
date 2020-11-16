package Encounters;

import Anses.Anses;
import Controllers.UserController;
import Events.DeclaredSymptom;
import Users.Citizen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zone {

    String locationName;
    List<Citizen> citizens;

    public Zone(String locationName) {
        this.locationName = locationName;
        this.citizens = new ArrayList<>();
    }

    public String getLocationName() {
        return locationName;
    }

    //agrega citizens a la zone
    public void checkCitizensInZone(Anses anses, UserController userController){
        List<Citizen> allCitizens = userController.getCitizens();
        for (int i = 0; i < allCitizens.size(); i++) {
            Citizen citizen = allCitizens.get(i);
            String citizenLocation = anses.getResidentLocation(citizen.getCuil());
            if(citizenLocation.equals(locationName) && !citizens.contains(citizen)){
                citizens.add(citizen);
            }
        }
    }

    //no se usa
    //devuelve una lista con los 3 symptoms mas comunes en la location
   /* public List<String> getMostCommonSymptoms(){

        HashMap<String, Integer> eventsInZone = new HashMap<>();

        //arreglar esto
        for (int i = 0; i < citizens.size(); i++) {
            Citizen citizen = citizens.get(i);
            for (int j = 0; j < citizen.getSymptoms().size(); j++) {
                DeclaredSymptom aSymptom = citizen.getSymptoms().get(j);
                if(eventsInZone.containsKey(aSymptom.getSymptomName())){
                    Integer aux = eventsInZone.get(aSymptom.getSymptomName());
                    aux++;
                    eventsInZone.remove(aSymptom.getSymptomName());
                    eventsInZone.put(aSymptom.getSymptomName(),aux);
                }else{
                    eventsInZone.put(aSymptom.getSymptomName(),1);
                }
            }
        }

        List<String> threeMostCommon = new ArrayList<>();

        //int max = 0;
        String symptom = "nada";
        int i = 0;
        while(i < 3){
            int max = 0;
            for (Map.Entry<String, Integer> entry : eventsInZone.entrySet()) {
               if(entry.getValue() > max){
                   symptom = entry.getKey();
               }
            }
            if(!threeMostCommon.contains(symptom) && !symptom.equals("nada")){
                threeMostCommon.add(symptom);
            }
            eventsInZone.remove(symptom);
            i++;
        }

        return threeMostCommon;
    }*/

    public List<String> getMostCommonSymptoms(){

        List<EventsAux> eventsAux = new ArrayList<>();
        List<String> events = new ArrayList<>();

        for (int i = 0; i < citizens.size(); i++) {
            Citizen citizen = citizens.get(i);
            for (int j = 0; j < citizen.getSymptoms().size(); j++) {
                String symptom = citizen.getSymptoms().get(j).getSymptomName();
                if(!events.contains(symptom)){
                    events.add(symptom);
                    eventsAux.add(new EventsAux(symptom, 1));
                }else if(events.contains(symptom)){
                    //sumar uno al counter
                    int index = events.indexOf(symptom);
                    eventsAux.get(index).addCounter();
                }
            }
        }

        List<String> threeMostCommon = new ArrayList<>();

        int counter = 0;

        while(counter < 3){
            int max = 0;
            String symptom = " ";
            for (int i = 0; i < eventsAux.size(); i++) {
                if(eventsAux.get(i).getCounter() > max){
                    max = eventsAux.get(i).getCounter();
                    symptom = eventsAux.get(i).getSymptomName();
                }
            }
            if(!threeMostCommon.contains(symptom)){
                threeMostCommon.add(symptom);
            }
            int index = events.indexOf(symptom);
            if(index != -1){
                eventsAux.remove(index);
                events.remove(index);
            }

            counter++;
        }

        return threeMostCommon;

    }



}
