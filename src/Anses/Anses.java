package Anses;

import java.util.ArrayList;
import java.util.List;

public class Anses {

    List<Resident> residents;

    public Anses(List<Resident> residents) {
        this.residents = residents;
    }

    public boolean residentExists(String cuil, String phoneNumber){
        for (int i = 0; i < residents.size(); i++) {
            if(residents.get(i).getCuil().equals(cuil) || residents.get(i).getPhoneNumber().equals(phoneNumber)){
                return true;
            }
        }
        return false;
    }

    //metodos q levanten la info de los archivos txt
}
