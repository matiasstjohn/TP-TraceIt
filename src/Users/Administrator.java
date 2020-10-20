package Users;
import Controllers.AdminController;
import Events.DiseaseController;

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
    public Administrator createAdministrator(String userName, String password, AdminController adminController){
        Administrator administrator = new Administrator(userName, password);
        adminController.addAdministrator(administrator);
        return administrator;
    }

    //permite al admin dar de alta un sintoma y los guarda en la lista de sintomas
    public void createSymptoms(String symptoms, DiseaseController diseaseController){
        diseaseController.addSymptom(symptoms);
    }

    //permite al administrador desbloquear a un citizen
    public void unblockCitizen(Citizen citizen){
        citizen.unBlockUser();
    }


}
