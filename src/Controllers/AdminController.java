package Controllers;

import Exceptions.UserAlreadyExistsException;
import Users.Administrator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
Se almacenan los administradores. Falta implementar persistencia.
La idea es que al  iniciar el programa la lista de admins se lleme con los admins guardados en los archivos txt
y al cerrarlo se vuelvan a guardar en esos archivos
Los metodos para hacer esto estarian en esta misma clase
 */

public class AdminController {

    private List<Administrator> administrators;
    private String filePath = "src/Persistencia/Administrators";

    //constructor, la lista de admins se rellenaria con la info de los txt
    public AdminController() throws IOException {
        administrators = getUsersFromFile();
    }

    //devuelve a todos los admins
    public  List<Administrator> getAdministrators(){
        return administrators;
    }

    //permite agreagar un admin a la lista de admins
    public void addAdministrator(Administrator administrator){
        administrators.add(administrator);
        writeUsersToFile();
    }

    //permite remover un admin
    public void removeAdministrator(Administrator administrator){
        administrators.remove(administrator);
    }

    public void writeUsersToFile(){
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Administrator admin : administrators) {
                fw.write(admin.getUserName() + "," + admin.getPassword() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private List<Administrator> getUsersFromFile() throws IOException {
        List<Administrator> admins = new ArrayList<>();

        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String userInfo;
        while ((userInfo = br.readLine()) != null) {
            String[] userParts = userInfo.split(",");
            Administrator administrator = new Administrator(userParts[0], userParts[1]);
            admins.add(administrator);
        }
        return admins;
    }



}
