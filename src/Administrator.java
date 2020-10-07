public class Administrator {

    private String userName;
    private String password;
    private UserController userController;
    private diseaseController diseaseController;

    public Administrator(String userName, String password, UserController userController) {
        this.userName = userName;
        this.password = password;
        this.userController = userController;
    }

    public void createAdministrator(String userName, String password, UserController aUserController){
        Administrator administrator = new Administrator(userName, password, aUserController);
        userController.addAdministrator(administrator);
    }

    public void createSymptoms(String symptoms){
        diseaseController.addSymptom(symptoms);
    }




}
