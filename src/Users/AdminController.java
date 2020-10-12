package Users;

import java.util.ArrayList;
import java.util.List;

public class AdminController {

    private List<Administrator> administrators;

    public AdminController(){
        administrators = new ArrayList<>();
    }

    public  List<Administrator> getAdministrators(){
        return administrators;
    }

    public void addAdministrator(Administrator administrator){
        administrators.add(administrator);
    }

    public void removeAdministrator(Administrator administrator){
        administrators.remove(administrator);
    }


}
