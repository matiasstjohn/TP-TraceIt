import java.util.ArrayList;
import java.util.List;

public class Meeting {

    // guarda solo cuando te viste por ultima ves porque de ahi se calculan las 48 horas
    int date; // se puede cambiar a objetod date
    List<Citizen> participants;

    public Meeting(int date) {
        this.date = date;
        participants = new ArrayList<>();
    }

    public void confirmParticipant(Citizen citizen){
        participants.add(citizen);
    }

    public int getDate(){
        return date;
    }

    public String getParticipantsCuil(){
        String cuils = "";
        for (int i = 0; i < participants.size(); i++) {
            cuils = cuils + participants.get(i).getCuil() + ", ";
        }
        return cuils;
    }




}
