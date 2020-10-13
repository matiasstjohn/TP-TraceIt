package Events;

import java.util.ArrayList;
import java.util.List;


public class DiseaseController {

    /*
    la gestion de eventos todavia no fue implementada. Creemos que va a ser necesario
    tener un abm de eventos para guardarlos
    Por ahora un usuario solo puede indicar un sintoma y un administrador puede darlo de alta pero estas dos cosas no
    estan relacionadas entre si
     */

    private List<Disease> diseases;
    private List<String> symptoms;

    public DiseaseController(){ // se rellenan con la info de los txt
        diseases = new ArrayList<>();
        symptoms = new ArrayList<>();
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void addSymptom(String symptom){
        if(symptoms.contains(symptom)){
            return;
        }
        symptoms.add(symptom);
    }

    public void removeSymptom(String symptom){
        symptoms.remove(symptom);
    }



}
