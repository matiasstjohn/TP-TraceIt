package Encounters;

import Events.Disease;
import Users.Citizen;

import java.util.ArrayList;
import java.util.List;

public class Outbreak {

    Disease disease;
    List<Citizen> citizens;
    boolean active;
    boolean secondGrade;
    Date starts;
    Date lastDate;
    String location;

    public Outbreak(Disease disease, String location, Date starts) {
        this.disease = disease;
        this.citizens = new ArrayList<>();
        this.active = false;
        this.secondGrade = false;
        this.starts = starts;
        this.lastDate = starts;
        this.location = location;
    }

    public Outbreak(Disease disease, List<Citizen> citizens, boolean active, boolean secondGrade, Date starts, Date lastDate, String location) {
        this.disease = disease;
        this.citizens = citizens;
        this.active = active;
        this.secondGrade = secondGrade;
        this.starts = starts;
        this.lastDate = lastDate;
        this.location = location;
    }

    public Boolean isSecondGrade(){
        return secondGrade;
    }

    public Disease getDisease() {
        return disease;
    }

    public boolean containsCitizen(Citizen citizen){
        return citizens.contains(citizen);
    }

    public void addCitizen(Citizen citizen){
        if(!citizens.contains(citizen)){
            citizens.add(citizen);
            if(citizens.size() == 5){
                this.active = true;
            }
        }
    }

    public Citizen getFirstCitizen(){
        return citizens.get(0);
    }

    public void activeSecondGrade(){
        secondGrade = true;
        //active = true;
    }

    public void changeLastDate(Date date){
        this.lastDate = date;
    }

    public boolean isActive() {
        return active;
    }

    public List<Citizen> getCitizens() {
        return citizens;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public String getLocation() {
        return location;
    }

    public Date getStarts() {
        return starts;
    }

    public int getAmountOfPeople(){
        return citizens.size();
    }
}
