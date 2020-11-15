package Controllers;

import Encounters.Outbreak;
import Events.Disease;
import Users.Citizen;

import java.util.ArrayList;
import java.util.List;

public class OutbreakController {

    List<Outbreak> outbreaks;

    public OutbreakController() {
        this.outbreaks = new ArrayList<>();
    }

    public void addOutbrake(Outbreak outbreak){
        outbreaks.add(outbreak);
    }

    public List<Outbreak> checkCitizenRelatedOutbreaks(Citizen citizen, Disease disease){
        List<Outbreak> possibleOutbreaks = new ArrayList<>();
        for (int i = 0; i < outbreaks.size(); i++) {
            if(outbreaks.get(i).getDisease().equals(disease) && outbreaks.get(i).containsCitizen(citizen)){
                possibleOutbreaks.add(outbreaks.get(i));
            }
        }
        return possibleOutbreaks;
    }

    public List<Outbreak> getOutbreaks() {
        return outbreaks;
    }

    public List<Outbreak> getSortedOutBreaks(){
            List<Outbreak> outbreaksAux = new ArrayList<>();
            outbreaksAux.addAll(outbreaks);
            List<Outbreak> outbrakesOrdenados = new ArrayList<>();
            while(outbreaksAux.size() > 0){
                Outbreak outbreak = outbreaksAux.get(0);
                for (int j = 0; j < outbreaksAux.size(); j++) {
                    if(outbreak.getAmountOfPeople() < outbreaksAux.get(j).getAmountOfPeople()){
                        outbreak = outbreaksAux.get(j);
                    }
                }
                outbrakesOrdenados.add(outbreak);
                outbreaksAux.remove(outbreak);
            }
            return outbrakesOrdenados;
    }

}
