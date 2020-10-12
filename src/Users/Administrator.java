package Users;
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

    public Administrator createAdministrator(String userName, String password, AdminController adminController){
        Administrator administrator = new Administrator(userName, password);
        adminController.addAdministrator(administrator);
        return administrator;
    }

    public void createSymptoms(String symptoms, DiseaseController diseaseController){
        diseaseController.addSymptom(symptoms);
    }

    public void unblockCitizen(Citizen citizen){
        citizen.unBlockUser();
    }


}
