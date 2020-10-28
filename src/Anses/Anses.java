package Anses;

import Users.Administrator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Anses {

    private List<Resident> residents;
    private String filePath = "src/Persistencia/RegistroAnses";

    public Anses() throws IOException {
        this.residents = getResidentsFromFile();
    }

    public boolean residentExists(String cuil, String phoneNumber){
        for (int i = 0; i < residents.size(); i++) {
            if(residents.get(i).getCuil().equals(cuil) || residents.get(i).getPhoneNumber().equals(phoneNumber)){
                return true;
            }
        }
        return false;
    }

    public List<Resident> getResidents(){
        return residents;
    }

    //metodos q levanten la info de los archivos txt
    private List<Resident> getResidentsFromFile() throws IOException {
        List<Resident> residentsAux = new ArrayList<>();

        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String userInfo;
        while ((userInfo = br.readLine()) != null) {
            String[] userParts = userInfo.split(",");
            Resident resident = new Resident(userParts[0], userParts[1], userParts[2]);
            residentsAux.add(resident);
        }
        return residentsAux;
    }

    //devuelve todas las locations que existen
    public List<String> getAllLocations(){
        List<String> allLocations = new ArrayList<>();
        for (int i = 0; i < residents.size(); i++) {
            if(!allLocations.contains(residents.get(i).getLocation())){
                allLocations.add(residents.get(i).getLocation());
            }
        }
        return allLocations;
    }

    //busca la location de un citizen pasandole cuil
    public String getResidentLocation(String cuil){
        for (int i = 0; i < residents.size(); i++) {
            if(residents.get(i).getCuil().equals(cuil)){
                return residents.get(i).getLocation();
            }
        }
        return null;
    }


}
