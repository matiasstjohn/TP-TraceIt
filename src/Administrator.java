public class Administrator {

    private String userName;
    private String password;
    private UserController userController;
    private DiseaseController diseaseController;

    public Administrator(String userName, String password, UserController userController, DiseaseController diseaseController) {
        this.userName = userName;
        this.password = password;
        this.userController = userController;
        this.diseaseController = diseaseController;
    }

    public Administrator createAdministrator(String userName, String password){
        Administrator administrator = new Administrator(userName, password, this.userController, this.diseaseController);
        userController.addAdministrator(administrator);
        return administrator;
    }

    public void createSymptoms(String symptoms){
        diseaseController.addSymptom(symptoms);
    }

    public String getName(){
        return userName;
    }




}
