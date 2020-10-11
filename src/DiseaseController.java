import java.util.ArrayList;
import java.util.List;


public class DiseaseController {

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
