package Users;
import Events.DiseaseController;

public class Administrator {

    private String userName;
    private String password;

    public Administrator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Administrator createAdministrator(String userName, String password, UserController userController){
        Administrator administrator = new Administrator(userName, password);
        userController.addAdministrator(administrator);
        return administrator;
    }

    public void createSymptoms(String symptoms, DiseaseController diseaseController){
        diseaseController.addSymptom(symptoms);
    }

    public void unblockCitizen(Citizen citizen){
        citizen.unBlockUser();
    }

    public String getName(){
        return userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword(){
        return password;
    }
}
