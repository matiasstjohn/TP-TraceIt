package Users;

import java.util.ArrayList;
import java.util.List;

public class UserController {

    private List<Citizen> citizens;

    public UserController(){  // se rellenan con la info de los txt
        citizens = new ArrayList<>();
    }

    public List<Citizen> getCitizens() {
        return citizens;
    }

    public void addCitizen(Citizen citizen){
        citizens.add(citizen);
    }

    public List<Citizen> getBlockedCitizens(){
        List<Citizen> blockedCitizens = new ArrayList<>();
        for (int i = 0; i < citizens.size(); i++) {
            if(citizens.get(i).isBlocked()){
                blockedCitizens.add(citizens.get(i));
            }
        }
        return blockedCitizens;
    }

    public Citizen getCitizenByCuil(String cuil){
        for (int i = 0; i < citizens.size(); i++) {
            if(citizens.get(i).getCuil().equals(cuil)){
                return citizens.get(i);
            }
        }
        return null;
    }

















    public boolean banUser(Citizen citizen){
        //seria return citizen.blocked = true
        return true;
    }

    public boolean unBanUser(Citizen citizen){
        //seria return.citizen = false
        return false;
    }


}
