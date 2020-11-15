package Events;

import Encounters.Date;

public class DeclaredSymptom {

    private String symptomName;
    private Date date;

    public DeclaredSymptom(String symptomName, Date date) {
        this.symptomName = symptomName;
        this.date = date;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public Date getDate() {
        return date;
    }

    public String toString(){
        String symptom = "" + symptomName + ":" + date.toString() + "";
        return symptom;
    }
}
