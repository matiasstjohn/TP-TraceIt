package Main;

import Events.DiseaseController;
import Logins.AdminLogin;
import Logins.UserLogin;
import Users.AdminController;
import Users.Administrator;
import Users.Citizen;
import Users.UserController;
import Util.Scanner;

import java.util.Date;

public class TesterNuevo {
    public static void main(String[] args) {

        //PRUEBA DE LA CLASE DATE
        Date date = new Date();
        System.out.println("Fecha: " + date.getDate() + "/" + date.getMonth() + ". Hora: " + date.getHours() + ":" + date.getMinutes());
        //PRUEBA DE LA CLASE DATE


        UserController userController = new UserController();
        AdminController adminController = new AdminController();
        DiseaseController diseaseController = new DiseaseController();

        Administrator admin = new Administrator("admin", "admin");
        adminController.addAdministrator(admin);

        UserLogin userLogin = new UserLogin();
        AdminLogin adminLogin = new AdminLogin();

        //usuarios creados para testear (se llaman blocked pero no necesariamente estan bloqueados)

        Citizen blockedCitizen = new Citizen("aaa","aaa");
        userController.addCitizen(blockedCitizen);
        //blockedCitizen.blockUser();

        Citizen blockedCitizen2 = new Citizen("bbb","bbb");
        userController.addCitizen(blockedCitizen2);
        blockedCitizen2.blockUser();

        Citizen blockedCitizen3 = new Citizen("ccc","ccc");
        userController.addCitizen(blockedCitizen3);
        //blockedCitizen3.blockUser();

        while(true){
            int action = Scanner.getInt("Select 1 to enter as a citizen, 2 to enter as an administrator or 3 to exit: ");
            if(action == 1){
                userLogin.citizenInterfaze(userController);
            }else if(action == 2){
                adminLogin.adminInterfaze(adminController, userController, diseaseController);
            }else if(action == 3){
                return;
            }
        }

    }
}
