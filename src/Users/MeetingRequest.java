package Users;

import java.util.List;

public class MeetingRequest {

    List<Citizen> participants;
    Meeting meeting;
    Citizen sender;

    public MeetingRequest(Citizen sender, List<Citizen> citizens, int date){
        this.sender = sender;
        participants = citizens;
        meeting = new Meeting(date);
        meeting.confirmParticipant(sender);
    }

    public List<Citizen> getParticipants(){
        return participants;
    }

    public void addParticipantToMeeting(Citizen citizen){
        meeting.confirmParticipant(citizen);
    }

    public Citizen getSender(){
        return sender;
    }

    public Meeting getMeeting(){
        return meeting;
    }

}
