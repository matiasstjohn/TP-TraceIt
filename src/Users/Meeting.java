package Users;

import Users.Citizen;

import java.util.ArrayList;
import java.util.List;

public class Meeting {

    /*
     guarda solo cuando te viste por ultima ves porque de ahi se calculan las 48 horas
     se puede cambiar el int date por el objeto date.
     */
    int date;
    List<Citizen> participants;

    public Meeting(int date) {
        this.date = date;
        participants = new ArrayList<>();
    }

    //agreaga un participante a la meeting
    public void confirmParticipant(Citizen citizen){
        participants.add(citizen);
    }

    //devuelve la fecha
    public int getDate(){
        return date;
    }

    //devuelve los numeros de cuil de todos los participantes
    public String getParticipantsCuil(){
        String cuils = "";
        for (int i = 0; i < participants.size(); i++) {
            cuils = cuils + participants.get(i).getCuil() + ", ";
        }
        return cuils;
    }




}
