package Users;

import java.util.List;

public class MeetingRequest {

    /*
    La meeting request contiene a las personas a las cual se envia, a quien la envia y la
    meeting
     */

    List<Citizen> participants;
    Meeting meeting;
    Citizen sender;

    public MeetingRequest(Citizen sender, List<Citizen> citizens, int date){
        this.sender = sender;
        participants = citizens;
        meeting = new Meeting(date);
        meeting.confirmParticipant(sender);
    }

    //devuelve a los participantes que se les envio la request
    public List<Citizen> getParticipants(){
        return participants;
    }

    //cuando un participante acepta la meeting request es agregado a la meeting
    public void addParticipantToMeeting(Citizen citizen){
        meeting.confirmParticipant(citizen);
    }

    //devuelve quien envio la meeting request
    public Citizen getSender(){
        return sender;
    }

    //devuelve la meeting que tiene esta meeting request
    public Meeting getMeeting(){
        return meeting;
    }

}
