package Main;

import Anses.Anses;
import Controllers.AdminController;
import Controllers.MeetingController;
import Controllers.UserController;
import Events.DiseaseController;
import Logins.AdminLogin;
import Logins.UserLogin;
import Anses.*;
import Users.*;
import Util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class TesterNuevo {
    public static void main(String[] args) {

        /*
        Crea los abm y un usuario administrador
         */

        UserController userController = new UserController();
        AdminController adminController = new AdminController();
        DiseaseController diseaseController = new DiseaseController();
        MeetingController meetingController = new MeetingController();


        Resident aaa = new Resident("aaa","aaa","pilar");
        Resident bbb = new Resident("bbb","bbb","pilar");
        Resident ccc = new Resident("ccc","ccc","pilar");
        Resident eee = new Resident("eee", "eee","bsas");

        List<Resident> residents = new ArrayList<>();
        residents.add(aaa);
        residents.add(bbb);
        residents.add(ccc);
        residents.add(eee);

        Anses anses = new Anses(residents);

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
        //blockedCitizen2.blockUser();

        Citizen blockedCitizen3 = new Citizen("ccc","ccc");
        userController.addCitizen(blockedCitizen3);
        //blockedCitizen3.blockUser();

        /*
        Menu para seleccionar si se quiere ingresar al userLogin, al adminLogin o salir del programa
         */
        while(true){
            int action = Scanner.getInt("Select 1 to enter as a citizen, 2 to enter as an administrator or 3 to exit: ");
            if(action == 1){
                userLogin.citizenInterfaze(userController, meetingController, anses);
            }else if(action == 2){
                adminLogin.adminInterface(adminController, userController, diseaseController);
            }else if(action == 3){
                return;
            }
        }

    }
}
