package Events;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Disease {

    /*
    La gestion de eventos todavia no fue implementada
    La idea es agrupar a los sintomas en diseases.
     */

    String diseaseName;
    List<String> symptoms;

    public Disease(String diseaseName, List<String> symptoms){
        this.symptoms = symptoms;
        this.diseaseName = diseaseName;
    }

    public String getDiseaseName(){
        return diseaseName;
    }

    public void removeSymptom(String position){
        symptoms.remove(position);
    }

    public void addSymptom(String symptom){
        if(symptoms.contains(symptom)){
            return;
        }
        symptoms.add(symptom);
    }

    public List<String> getSymptoms(){
        return symptoms;
    }

    public String symptomsToString(){
        String symptomsString = "";
        for (int i = 0; i < symptoms.size(); i++) {
            if(symptomsString.equals("")){
                symptomsString = symptomsString + symptoms.get(i);
            }else{
                symptomsString = symptomsString + "," + symptoms.get(i);
            }

        }
        return symptomsString;
    }




}
