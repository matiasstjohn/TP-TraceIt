package Controllers;

import Encounters.Date;
import Events.DeclaredSymptom;
import Events.Disease;
import Exceptions.InvalidDate;
import Users.Administrator;
import Users.Citizen;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Se almacenan los usuarios. Falta implementar persistencia.
La idea es que al  iniciar el programa la lista de citizens se lleme con los citizens guardados enlos archivos txt
y al cerrarlo se vuelvan a guardar en esos archivos
Los metodos para hacer esto estarian en esta misma clase
 */

public class UserController {

    private List<Citizen> citizens;
    private String filePath = "src/Persistencia/Citizens";

    //constructor. El array list se rellenaria con la info de los txt
    public UserController(DiseaseController diseaseController) throws IOException, InvalidDate {
        //citizens = new ArrayList<>();
        citizens = getCitizensFromFile(diseaseController);
    }

    //devuelve los citizens
    public List<Citizen> getCitizens() {
        return citizens;
    }

    //permite agregar un citizen a la lista
    public void addCitizen(Citizen citizen){
        citizens.add(citizen);
    }

    //devuelve una lista de los ciudadanos bloqueados
    public List<Citizen> getBlockedCitizens(){
        List<Citizen> blockedCitizens = new ArrayList<>();
        for (int i = 0; i < citizens.size(); i++) {
            if(citizens.get(i).isBlocked()){
                blockedCitizens.add(citizens.get(i));
            }
        }
        return blockedCitizens;
    }

    //devuelve un citizen buscandolo segun su cuil (numero unico de cada citizen)
    public Citizen getCitizenByCuil(String cuil){
        for (int i = 0; i < citizens.size(); i++) {
            if(citizens.get(i).getCuil().equals(cuil)){
                return citizens.get(i);
            }
        }
        return null;
    }

    public boolean citizenExists(String cuil){
        for (int i = 0; i < citizens.size(); i++) {
            if(citizens.get(i).getCuil().equals(cuil)){
                return true;
            }
        }
        return false;
    }

    public boolean banUser(Citizen citizen){
        //seria return citizen.blocked = true
        return true;
    }

    public boolean unBanUser(Citizen citizen){
        //seria return.citizen = false
        return false;
    }

    private List<Citizen> getCitizensFromFile(DiseaseController diseaseController) throws IOException, InvalidDate {
        List<Citizen> citizensAux = new ArrayList<>();

        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String userInfo;
        while ((userInfo = br.readLine()) != null) {
            String[] userParts = userInfo.split(";");
            List<DeclaredSymptom> declaredSymptoms = new ArrayList();
            if(userParts.length >  4){
            ArrayList symptomsAux = new ArrayList(Arrays.asList(userParts[4].split(",")));
            for (int i = 0; i < symptomsAux.size(); i++) {
                String symptomInfo = (String) symptomsAux.get(i);
                String[] symptomParts = symptomInfo.split(":");
                DeclaredSymptom declaredSymptom = new DeclaredSymptom(symptomParts[0], MetodosPersistencia.toDate(symptomParts[1]));
                declaredSymptoms.add(declaredSymptom);
            }
            }
            List<Disease> diseases = new ArrayList<>();
            if(userParts.length > 5 ){
            ArrayList diseasesAux = new ArrayList(Arrays.asList(userParts[5].split(",")));
            for (int i = 0; i < diseasesAux.size(); i++) {
                diseases.add(diseaseController.getDiseaseByName((String) diseasesAux.get(i)));
            }
            }
            Citizen citizen = new Citizen(userParts[0],userParts[1], Boolean.parseBoolean(userParts[2]), Integer.parseInt(userParts[3]), declaredSymptoms, diseases);
            citizensAux.add(citizen);
            }

        return citizensAux;
    }

    /*public static Date toDate(String date) throws InvalidDate {
        Integer month =Integer.parseInt(String.valueOf(date.substring(0,2)));
        Integer day = Integer.parseInt(String.valueOf(date.substring(3,5)));
        Integer hours = Integer.parseInt(String.valueOf(date.substring(6,8)));
        return new Date(month,day,hours);
    }*/

    public void writeCitizensToFile(){
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Citizen citizen : citizens) {
                fw.write(citizen.getCuil() + ";" + citizen.getPhoneNumber() + ";" + Boolean.toString(citizen.isBlocked()) + ";" + citizen.getRejectedRequests() + ";" + citizen.declaredSymptomsToString() + ";" + citizen.diseasesToString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
