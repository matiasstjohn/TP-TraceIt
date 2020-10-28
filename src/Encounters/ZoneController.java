package Encounters;

import Anses.Anses;
import Controllers.UserController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZoneController {

    List<Zone> zones;

    public ZoneController() {
        this.zones = new ArrayList<>();
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void createZones(Anses anses){
        for (int i = 0; i < anses.getAllLocations().size(); i++) {
            zones.add(new Zone(anses.getAllLocations().get(i)));
        }
    }

    public void updateCitizensInZones(Anses anses, UserController userController){
        for (int i = 0; i < zones.size(); i++) {
            zones.get(i).checkCitizensInZone(anses,userController);
        }
    }

    //devuelve una lista de listas con los 3 symptoms
    public List<List<String>> showThreeMostCommon(){
        List<List<String>> commonSymptomsByLocation = new ArrayList<>();
        for (int i = 0; i < zones.size(); i++) {
            commonSymptomsByLocation.add(zones.get(i).getMostCommonSymptoms());
        }
        return commonSymptomsByLocation;
        }


}

