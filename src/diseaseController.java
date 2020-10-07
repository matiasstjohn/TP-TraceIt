import java.util.ArrayList;
import java.util.List;

public class diseaseController {

    private List<String> diseases;
    private List<String> symptoms;

    public diseaseController(){
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
