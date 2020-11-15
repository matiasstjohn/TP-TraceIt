package Encounters;

public class EventsAux {

    String symptomName;
    int counter;

    public EventsAux(String symptomName, int counter) {
        this.symptomName = symptomName;
        this.counter = counter;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public int getCounter() {
        return counter;
    }

    public void addCounter(){
        counter++;
    }
}
