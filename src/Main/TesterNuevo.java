package Main;

import Anses.Anses;
import Controllers.*;
import Encounters.Outbreak;
import Exceptions.InvalidDate;
import Logins.AdminLogin;
import Logins.UserLogin;
import Users.*;
import Util.Scanner;

import java.io.IOException;
import java.util.List;

public class TesterNuevo {
    public static void main(String[] args) throws IOException, InvalidDate {

        /*
        Crea los abm y un usuario administrador
         */


        AdminController adminController = new AdminController();
        DiseaseController diseaseController = new DiseaseController();
        UserController userController = new UserController(diseaseController);
        MeetingController meetingController = new MeetingController();
        Anses anses = new Anses();

        OutbreakController outbreakController = new OutbreakController(diseaseController, userController);

        ZoneController zoneController = new ZoneController();
        zoneController.createZones(anses);
        zoneController.updateCitizensInZones(anses, userController);

        UserLogin userLogin = new UserLogin();
        AdminLogin adminLogin = new AdminLogin();

        //usuarios creados para testear (se llaman blocked pero no necesariamente estan bloqueados)

        /*
        Citizen blockedCitizen = new Citizen("111","111");
        userController.addCitizen(blockedCitizen);
        //blockedCitizen.blockUser();

        Citizen blockedCitizen2 = new Citizen("222","222");
        userController.addCitizen(blockedCitizen2);
        //blockedCitizen2.blockUser();

        Citizen blockedCitizen3 = new Citizen("333","333");
        userController.addCitizen(blockedCitizen3);
        //blockedCitizen3.blockUser();

        Citizen blockedCitizen4 = new Citizen("444","444");
        userController.addCitizen(blockedCitizen4);
        //blockedCitizen3.blockUser();
      */

        zoneController.updateCitizensInZones(anses, userController);

        /*
        Menu para seleccionar si se quiere ingresar al userLogin, al adminLogin o salir del programa
         */
        while(true){
            System.out.println("");
            int action = Scanner.getInt("Select 1 to enter as a citizen, 2 to enter as an administrator, 3 to display common events by location, 4 to display outbreaks or 5 to exit: ");
            if(action == 1){
                userLogin.citizenInterfaze(userController, meetingController, anses, diseaseController, outbreakController);
            }else if(action == 2){
                adminLogin.adminInterface(adminController, userController, diseaseController);
            }else if(action == 3){
                //muestra los 3 symptoms mas comunse por zona
                zoneController.updateCitizensInZones(anses,userController);
                List<List<String>> commonSymptomsByZone = zoneController.showThreeMostCommon();
                    for (int i = 0; i < commonSymptomsByZone.size(); i++) {
                        System.out.println("\n" + zoneController.getZones().get(i).getLocationName() + ": ");
                        if(commonSymptomsByZone.get(i).size() > 0){
                            for (int j = 0; j < commonSymptomsByZone.get(i).size(); j++) {
                                System.out.print( "" + commonSymptomsByZone.get(i).get(j) + ", ");
                            }
                      }
                    }
            }else if(action == 4){
                List<Outbreak> outbreaks = outbreakController.getSortedOutBreaks();
                for (int i = 0; i < outbreaks.size(); i++) {
                    Outbreak outbreak = outbreaks.get(i);
                    if(outbreak.isActive()){
                        System.out.println("Location: " + outbreak.getLocation() + ". Related disease: " + outbreak.getDisease().getDiseaseName() + ". People Involved: " + outbreak.getCitizens().size() + ". Start date: " + outbreak.getStarts() + ". Last activity: " + outbreak.getLastDate() + ".");
                    }
                }
            }else if(action == 5){
                userController.writeCitizensToFile();
                meetingController.writeMeetingsToFile();
                outbreakController.writeOutbreaksToFile();
                return;
            }
        }
    }

    public static String listToString(List<String> list){
            String string = "";
            for (int i = 0; i < list.size(); i++) {
                if(string.equals("")){
                    string = string + list.get(i).toString();
                }else{
                    string = string + "," + list.get(i).toString();
                }

            }
            return string;
        }


}

