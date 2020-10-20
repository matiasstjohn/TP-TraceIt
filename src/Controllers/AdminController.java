package Controllers;

import Users.Administrator;

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

    //constructor, la lista de admins se rellenaria con la info de los txt
    public AdminController(){
        administrators = new ArrayList<>();
    }

    //devuelve a todos los admins
    public  List<Administrator> getAdministrators(){
        return administrators;
    }

    //permite agreagar un admin a la lista de admins
    public void addAdministrator(Administrator administrator){
        administrators.add(administrator);
    }

    //permite remover un admin
    public void removeAdministrator(Administrator administrator){
        administrators.remove(administrator);
    }


}
