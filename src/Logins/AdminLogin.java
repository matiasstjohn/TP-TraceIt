package Logins;

import Events.Disease;
import Events.DiseaseController;
import Controllers.AdminController;
import Exceptions.DiseaseAlreadyRegisteredException;
import Exceptions.UserAlreadyExistsException;
import Users.Administrator;
import Users.Citizen;
import Controllers.UserController;
import Util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class AdminLogin {

    /*
    Busca un administrador pasandole un usuario y su contraseña
     */
    public Administrator searchAdmin(AdminController adminController){
        String userName = Scanner.getString("Enter username: ");
        String password = Scanner.getString("Enter password: ");
        List<Administrator> administrators = adminController.getAdministrators();
        for (int i = 0; i < administrators.size(); i++) {
            if(administrators.get(i).getUserName().equals(userName) && administrators.get(i).getPassword().equals(password)){
                return administrators.get(i);
            }
        }
        //throw new UserNotFoundException();
        return null;
    }

    /*
    Menu de los administradores
     */
    public void adminInterface(AdminController adminController, UserController userController, DiseaseController diseaseController){

        //busca un administrador (es el metodo de arriba)

       Administrator administrator = searchAdmin(adminController);

       //condiciones para que el admin pueda entrar
        if(administrator == null){
            System.out.println("User was not found. Try again.");
            return;
        }

        // opciones que tiene el admin
        while (true) {
            adminMenu();
            int a = Scanner.getInt("Select the action you want to preform: ");
            switch (a) {
                case 1:
                    createAdmin(administrator, adminController);
                    break;
                case 2:
                    registerDisease(administrator, diseaseController);
                    break;
                case 3:
                    removeSymptomOfDisease(administrator,diseaseController);
                    break;
                case 4:
                    addSymptomOfDisease(administrator,diseaseController);
                    break;
                case 5:
                    auditUsers(administrator, userController);
                    break;
                case 6:
                    for (int i = 0; i < diseaseController.getDiseases().size(); i++) {
                        System.out.println(diseaseController.getDiseases().get(i).getDiseaseName());
                    }
                    break;
                case 7:
                    return;
                default :
                    break;
            }
        }
    }

    // permite al admin craer otro admin ingresando usuario y contraseñias del nuevo admin
    public void createAdmin(Administrator administrator, AdminController adminController){
        String userName = Scanner.getString("Enter user name: ");
        String password = Scanner.getString("Enter password: ");
        try{
            administrator.createAdministrator(userName, password, adminController);
        }catch (UserAlreadyExistsException e){
            System.out.println(e.getMessage());
            return;
        }
    }

    // perminte al admin dar de alta sintomas (no esta completamente implementado los sintomas y sus relaciones)
    public void registerDisease(Administrator administrator, DiseaseController diseaseController) {
        String diseaseName = Scanner.getString("Enter disease name: ");
        int amountOfSymptoms = Scanner.getInt("Enter the amount of symptoms: ");
        List<String> symptoms = new ArrayList<>();
        for (int i = 0; i < amountOfSymptoms; i++) {
            String aSymptom = Scanner.getString("Enter symptom " + (i+1) + ": ");
            symptoms.add(aSymptom);
        }
        try{
            administrator.registerDisease(diseaseName, symptoms, diseaseController);
        }catch (DiseaseAlreadyRegisteredException e){
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Symptom registered correctly");
    }

    public void removeSymptomOfDisease(Administrator administrator, DiseaseController diseaseController){
        showDiseases(diseaseController);
        String disease = Scanner.getString("Enter a disease: ");
        if(diseaseNotExists(disease,diseaseController)){
            return;
        }
        showDiseaseSymptoms(disease, diseaseController);
        String symptomToRemove = Scanner.getString("Enter the symptom you want to remove: ");
        administrator.removeDiseaseSymptom(disease, symptomToRemove, diseaseController);
        System.out.println("Symptom removed correctly");
    }

    public void addSymptomOfDisease(Administrator administrator, DiseaseController diseaseController){
        showDiseases(diseaseController);
        String disease = Scanner.getString("Enter a disease: ");
        if(diseaseNotExists(disease,diseaseController)){
            return;
        }
        String symptomToAdd = Scanner.getString("Enter the symptom you want to add: ");
        administrator.addDiseaseSymptom(disease, symptomToAdd, diseaseController);
        System.out.println("Symptom added correctly");
    }

    public void showDiseases(DiseaseController diseaseController){
        for (int i = 0; i < diseaseController.getDiseases().size(); i++) {
            System.out.println("" + (i+1) + ". "  + diseaseController.getDiseases().get(i).getDiseaseName() + "");
        }
    }

    public void showDiseaseSymptoms(String diseaseName, DiseaseController diseaseController){
        Disease disease = diseaseController.getDiseaseByName(diseaseName);
        for (int i = 0; i < disease.getSymptoms().size(); i++) {
            System.out.println("" + (i+1) + ". "  + disease.getSymptoms().get(i) + "");
        }
    }

    public boolean diseaseNotExists(String diseaseName, DiseaseController diseaseController){
        if(diseaseController.getDiseaseByName(diseaseName) == null){
            return true;
        }
        return false;
    }

    // busca los citizens bloqueados y los muestra para que el admin puede seleccionar si quiere desbloquear a alguno
    public void auditUsers(Administrator administrator, UserController userController){
        List<Citizen> citizens = userController.getCitizens();
        List<Citizen> blockedCitizens = userController.getBlockedCitizens();
        if(blockedCitizens.isEmpty()){
            return;
        }
        for (int i = 0; i < blockedCitizens.size(); i++) {
            System.out.println("" + (i+1) + ". " + blockedCitizens.get(i).getCuil());
        }
        int unBlock = Scanner.getInt("Select a user to unblock or press 0 to go back: ");
        if(unBlock == 0){
            return;
        }
        administrator.unblockCitizen(blockedCitizens.get(unBlock - 1));
    }

    //menu
    public void adminMenu(){
        System.out.println("************");
        System.out.println("1. Create Admin");
        System.out.println("2. Register disease");
        System.out.println("3. Remove symptom from disease");
        System.out.println("4. Add symptom to disease");
        System.out.println("5. Audit users");
        System.out.println("6. Display possible diseases");
        System.out.println("7. Save and exit");
        System.out.println("************");
    }

}
