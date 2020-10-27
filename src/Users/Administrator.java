package Users;
import Controllers.AdminController;
import Events.Disease;
import Events.DiseaseController;
import Exceptions.DiseaseAlreadyRegisteredException;
import Exceptions.UserAlreadyExistsException;

import java.util.List;

public class Administrator {

    private String userName;
    private String password;

    public Administrator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword(){
        return password;
    }

    //permite que el admin cree otros admins t y lo guarda en el ABM de ususarios administradores
    public Administrator createAdministrator(String userName, String password, AdminController adminController) throws UserAlreadyExistsException {
        Administrator administrator = new Administrator(userName, password);
        for (int i = 0; i < adminController.getAdministrators().size(); i++) {
            if(adminController.getAdministrators().get(i).getUserName().equals(userName)){
                throw new UserAlreadyExistsException();
            }
        }
        adminController.addAdministrator(administrator);
        return administrator;
    }

    //permite al admin dar de alta un sintoma y los guarda en la lista de sintomas
    /*public void createSymptoms(String symptoms, DiseaseController diseaseController){
        diseaseController.addSymptom(symptoms);
    }*/

    //permite al administrador desbloquear a un citizen
    public void unblockCitizen(Citizen citizen){
        citizen.unBlockUser();
    }

    public void registerDisease(String diseaseName, List<String> symptoms, DiseaseController diseaseController) throws DiseaseAlreadyRegisteredException {
        Disease disease = new Disease(diseaseName, symptoms);
        if(diseaseController.getDiseaseByName(diseaseName) != null){
            throw new DiseaseAlreadyRegisteredException();
        }
        diseaseController.addDisease(disease);
    }

    public void removeDiseaseSymptom(String diseaseName, String symptom, DiseaseController diseaseController){
        if(diseaseController.getDiseaseByName(diseaseName) != null){
            diseaseController.getDiseaseByName(diseaseName).removeSymptom(symptom);
        }else{
            //exception
        }
    }

    public void addDiseaseSymptom(String diseaseName, String symptom, DiseaseController diseaseController){
        if(diseaseController.getDiseaseByName(diseaseName) != null){
            diseaseController.getDiseaseByName(diseaseName).addSymptom(symptom);
        }else{
            //exception
        }
    }


}
