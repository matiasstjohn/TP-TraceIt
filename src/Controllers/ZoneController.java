package Controllers;

import Anses.Anses;
import Controllers.UserController;
import Encounters.Zone;

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

    //crea las zones
    public void createZones(Anses anses){
        for (int i = 0; i < anses.getAllLocations().size(); i++) {
            zones.add(new Zone(anses.getAllLocations().get(i)));
        }
    }

    //se fija si hay nuevos citizens en la zone
    public void updateCitizensInZones(Anses anses, UserController userController){
        for (int i = 0; i < zones.size(); i++) {
            zones.get(i).checkCitizensInZone(anses,userController);
        }
    }

    //devuelve una lista de listas con los 3 symptoms
    public List<List<String>> showThreeMostCommon(){
        List<List<String>> commonSymptomsByLocation = new ArrayList<>();
        for (int i = 0; i < zones.size(); i++) {
            commonSymptomsByLocation.add(zones.get(i).getMostCommonSymptomsAux());
        }
        return commonSymptomsByLocation;
        }


}

