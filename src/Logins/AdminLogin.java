package Logins;

import Events.DiseaseController;
import Users.AdminController;
import Users.Administrator;
import Users.Citizen;
import Users.UserController;
import Util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class AdminLogin {

    public Administrator searchAdmin(AdminController adminController){
        String userName = Scanner.getString("Ingrese nombre de usuario: ");
        String password = Scanner.getString("Ingrese contrasenia: ");
        List<Administrator> administrators = adminController.getAdministrators();
        for (int i = 0; i < administrators.size(); i++) {
            if(administrators.get(i).getUserName().equals(userName) && administrators.get(i).getPassword().equals(password)){
                return administrators.get(i);
            }
        }
        return null;
    }

    public void adminInterfaze(AdminController adminController, UserController userController, DiseaseController diseaseController){

       Administrator administrator = searchAdmin(adminController);

        if(administrator == null){
            System.out.println("No se encontro el usuario. Intente Nuevamente");
            return;
        }

        while (true) {
            adminMenu();
            int a = Scanner.getInt("Seleccione la accion desea realizar: ");
            switch (a) {
                case 1:
                    createAdmin(administrator, adminController);
                    break;
                case 2:
                    createSymptom(administrator,adminController,diseaseController);
                    break;
                case 3:
                    symptomList(diseaseController);
                    break;
                case 4:
                    auditUsers(administrator, userController);
                    break;
                case 5:
                    return;
                default :
                    break;

            }
        }
    }

    public void createAdmin(Administrator administrator, AdminController adminController){
        String userName = Scanner.getString("Ingrese nombre de usuario: ");
        String password = Scanner.getString("Ingrese contrasenia: ");
        administrator.createAdministrator(userName, password, adminController);
    }

    public void createSymptom(Administrator administrator, AdminController adminController, DiseaseController diseaseController){
        String symptom = Scanner.getString("Ingrese sintoma: ");
        administrator.createSymptoms(symptom, diseaseController);
    }

    public void symptomList(DiseaseController diseaseController){
        for (int i = 0; i < diseaseController.getSymptoms().size(); i++) {
            System.out.println(diseaseController.getSymptoms().get(i));
        }
    }

    public void auditUsers(Administrator administrator, UserController userController){
        List<Citizen> citizens = userController.getCitizens();
        List<Citizen> blockedCitizens = userController.getBlockedCitizens();
        if(blockedCitizens.isEmpty()){
            return;
        }
        for (int i = 0; i < blockedCitizens.size(); i++) {
            System.out.println("" + (i+1) + ". " + blockedCitizens.get(i).getCuil());
        }
        int unBlock = Scanner.getInt("Seleccione cual desea desbloquear: ");
        administrator.unblockCitizen(blockedCitizens.get(unBlock - 1));
    }

    public void adminMenu(){
        System.out.println("************");
        System.out.println("1. Create Admin");
        System.out.println("2. Create Symptom");
        System.out.println("3. Symptom list");
        System.out.println("4. Audit users");
        System.out.println("5. Save and exit");
        System.out.println("************");
    }

}
