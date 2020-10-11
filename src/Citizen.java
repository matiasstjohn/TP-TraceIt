import java.util.ArrayList;
import java.util.List;

public class Citizen {

    private String phoneNumber;
    private String cuil;
    boolean blocked;
    List<String> symptoms;

    //como guardar las relaciones y lo symptoms?

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCuil() {
        return cuil;
    }

    public Citizen(String phoneNumber, String cuil){
        this.phoneNumber = phoneNumber;
        this.cuil = cuil;
        blocked = false;
        symptoms = new ArrayList<>();
    }

    public void addSymptom(String symptom){
        symptoms.add(symptom);
    }

    public void removeSymptom(String symptom){
        symptoms.remove(symptom);
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    // indicar relacion y aceptar relacion

    /*
    cuando un citizen rechaza se le suma uno al int del que envio la solicitud y se chequea si llego a 5,
     si llego a 5 se bloquea y se agrega a una lista para ser auditado por un admin
     */


}
