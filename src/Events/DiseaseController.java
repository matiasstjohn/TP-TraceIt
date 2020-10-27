package Events;

import Users.Administrator;
import Users.Citizen;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DiseaseController {

    /*
    la gestion de eventos todavia no fue implementada. Creemos que va a ser necesario
    tener un abm de eventos para guardarlos
    Por ahora un usuario solo puede indicar un sintoma y un administrador puede darlo de alta pero estas dos cosas no
    estan relacionadas entre si
     */

    private List<Disease> diseases;
    private String filePath = "src/Persistencia/Diseases";

    public DiseaseController() throws IOException { // se rellenan con la info de los txt
        diseases = getDiseasesFromFile();
    }

    public List<Disease> getDiseases(){
        return diseases;
    }

    public void addDisease(Disease disease){
        diseases.add(disease);
        writeDiseasesToFile();
    }

    public Disease getDiseaseByName(String diseaseName){
        for (int i = 0; i < diseases.size(); i++) {
            if(diseases.get(i).getDiseaseName().equals(diseaseName)){
                return diseases.get(i);
            }
        }
        return null;
    }

    public boolean symptomExists(String symptom){
        for (int i = 0; i < diseases.size(); i++) {
            for (int j = 0; j < diseases.get(i).getSymptoms().size(); j++) {
                if(diseases.get(i).getSymptoms().contains(symptom)){
                    return true;
                }
            }
        }
        return false;
    }

    public void matchCitizensWithDisease(List<Citizen> citizens){
        for (int k = 0; k < citizens.size(); k++) {
            Citizen citizen = citizens.get(k);
            for (int i = 0; i < diseases.size(); i++) {
                int counter = 0;
                for (int j = 0; j < diseases.get(i).getSymptoms().size(); j++) {
                    if(citizen.getSymptoms().contains(diseases.get(i).getSymptoms().get(j))){
                        counter++;
                    }
                }
                if(counter >= 2 && !citizen.hasDisease(diseases.get(i))){
                    citizen.addConfirmedDisease(diseases.get(i));
                }else if(counter < 2 && citizen.hasDisease(diseases.get(i))){
                    citizen.removeConfirmedDisease(diseases.get(i));
                }
            }
        }
    }

    public void writeDiseasesToFile(){
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Disease disease : diseases) {
                fw.write(disease.getDiseaseName() + ";" + disease.symptomsToString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<Disease> getDiseasesFromFile() throws IOException {
        List<Disease> diseasesAux = new ArrayList<>();

        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String userInfo;
        while ((userInfo = br.readLine()) != null) {
            String[] userParts = userInfo.split(";");
            ArrayList list = new ArrayList(Arrays.asList(userParts[1].split(",")));
            Disease disease = new Disease(userParts[0], list);
            diseasesAux.add(disease);
        }
        return diseasesAux;
    }


}
