package Encounters;

import Users.Citizen;

public class OutbreakAux {

    Outbreak outbreak;
    Citizen citizen;

    public OutbreakAux(Outbreak outbreak, Citizen citizen) {
        this.outbreak = outbreak;
        this.citizen = citizen;
    }

    public Outbreak getOutbreak() {
        return outbreak;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void checkSecondGrade(){
        if(!outbreak.getFirstCitizen().equals(citizen)){
            outbreak.activeSecondGrade();
        }
    }

}
