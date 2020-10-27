package Controllers;

import Users.Citizen;

import java.util.ArrayList;
import java.util.List;

/*
Se almacenan los usuarios. Falta implementar persistencia.
La idea es que al  iniciar el programa la lista de citizens se lleme con los citizens guardados enlos archivos txt
y al cerrarlo se vuelvan a guardar en esos archivos
Los metodos para hacer esto estarian en esta misma clase
 */

public class UserController {

    private List<Citizen> citizens;

    //constructor. El array list se rellenaria con la info de los txt
    public UserController(){
        citizens = new ArrayList<>();
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

    public boolean banUser(Citizen citizen){
        //seria return citizen.blocked = true
        return true;
    }

    public boolean unBanUser(Citizen citizen){
        //seria return.citizen = false
        return false;
    }



}
