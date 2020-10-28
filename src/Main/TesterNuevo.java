package Main;

import Anses.Anses;
import Controllers.AdminController;
import Controllers.MeetingController;
import Controllers.UserController;
import Controllers.ZoneController;
import Controllers.DiseaseController;
import Logins.AdminLogin;
import Logins.UserLogin;
import Users.*;
import Util.Scanner;

import java.io.IOException;
import java.util.List;

public class TesterNuevo {
    public static void main(String[] args) throws IOException {

        /*
        Crea los abm y un usuario administrador
         */

        UserController userController = new UserController();
        AdminController adminController = new AdminController();
        DiseaseController diseaseController = new DiseaseController();
        MeetingController meetingController = new MeetingController();
        Anses anses = new Anses();

        ZoneController zoneController = new ZoneController();
        zoneController.createZones(anses);
        zoneController.updateCitizensInZones(anses, userController);

        UserLogin userLogin = new UserLogin();
        AdminLogin adminLogin = new AdminLogin();

        //usuarios creados para testear (se llaman blocked pero no necesariamente estan bloqueados)

        Citizen blockedCitizen = new Citizen("111","111");
        userController.addCitizen(blockedCitizen);
        //blockedCitizen.blockUser();

        Citizen blockedCitizen2 = new Citizen("222","222");
        userController.addCitizen(blockedCitizen2);
        //blockedCitizen2.blockUser();

        Citizen blockedCitizen3 = new Citizen("333","333");
        userController.addCitizen(blockedCitizen3);
        //blockedCitizen3.blockUser();


        zoneController.updateCitizensInZones(anses, userController);

        /*
        Menu para seleccionar si se quiere ingresar al userLogin, al adminLogin o salir del programa
         */
        while(true){
            System.out.println("");
            int action = Scanner.getInt("Select 1 to enter as a citizen, 2 to enter as an administrator, 3 to display common events by location or 4 to exit: ");
            if(action == 1){
                userLogin.citizenInterfaze(userController, meetingController, anses, diseaseController);
            }else if(action == 2){
                adminLogin.adminInterface(adminController, userController, diseaseController);
            }else if(action == 3){
                //muestra los 3 symptoms mas comunse por zona
                zoneController.updateCitizensInZones(anses,userController);
                List<List<String>> commonSymoptomsByZone = zoneController.showThreeMostCommon();
                    for (int i = 0; i < commonSymoptomsByZone.size(); i++) {
                        System.out.println("\n" + zoneController.getZones().get(i).getLocationName() + ": ");
                        if(commonSymoptomsByZone.get(i).size() > 0){
                            for (int j = 0; j < commonSymoptomsByZone.get(i).size(); j++) {
                                System.out.print( "" + commonSymoptomsByZone.get(i).get(j) + ", ");
                            }
                      }
                    }
            }else if(action == 4){
                return;
            }
        }
    }
}

