import java.util.ArrayList;
import java.util.List;

public class UserController {

    private List<Citizen> citizens;
    private List<Administrator> administrators;

    public UserController(){  // se rellenan con la info de los txt
        citizens = new ArrayList<>();
        administrators = new ArrayList<>();
    }

    public void addCitizen(Citizen citizen){
        citizens.add(citizen);
    }

    public void addAdministrator(Administrator administrator){
        administrators.add(administrator);
    }

    public void removeCitizen(Citizen citizen){
        citizens.remove(citizen);
    }

    public void removeAdministrator(Administrator administrator){
        administrators.remove(administrator);
    }

    // getters

    public List<Citizen> getCitizens() {
        return citizens;
    }

    public List<Administrator> getAdministrators() {
        return administrators;
    }

    public boolean banUser(Citizen citizen){
        //seria return citizen.blocked = true
        return true;
    }

    public boolean unBanUser(Citizen citizen){
        //seria return.citizen = false
        return false;
    }


}
