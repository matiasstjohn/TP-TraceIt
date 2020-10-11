import java.util.List;

public class UserLogin {

    public Citizen createCitizen(UserController userController){
        String phoneNumber = Scanner.getString("Numero de telefono: ");
        String cuil = Scanner.getString("Cuil: ");
        Citizen citizen = new Citizen(phoneNumber, cuil);
        userController.addCitizen(citizen);
        return citizen;
    }

    public Citizen searchCitizen(UserController userController){
        String phoneNumber = Scanner.getString("Ingrese numero de telefono: ");
        String cuil = Scanner.getString("Ingrese cuil: ");
        List<Citizen> citizens = userController.getCitizens();
        for (int i = 0; i < citizens.size(); i++) {
            if(citizens.get(i).getPhoneNumber().equals(phoneNumber) && citizens.get(i).getCuil().equals(cuil)){
                return citizens.get(i);
            }
        }
        return null;
    }

    public void manageCitizen(UserController userController){

        Citizen citizen = searchCitizen(userController);

        if(citizen == null){
            System.out.println("No se encontro el usuario. Intente Nuevamente");
            return;
        }else if(citizen.isBlocked()){
            System.out.println("El usuario se encuentra bloqueado.");
            return;
        }

        while (true) {
            citizenMenu();
            int a = Scanner.getInt("Seleccione la accion desea realizar: ");
            switch (a) {
                case 1:
                    String symptom = Scanner.getString("Sintoma presenciado: ");
                    citizen.addSymptom(symptom);
                    break;
                case 2:
                    String removeSymptom = Scanner.getString("Sintoma que desea remover: ");
                    citizen.removeSymptom(removeSymptom);
                    break;
                case 3:
                    for (int i = 0; i < citizen.getSymptoms().size(); i++) {
                        System.out.println(citizen.getSymptoms().get(i));
                    }
                    break;
                case 4:
                    System.out.println("Opcion no disponible todavia.");
                    break;
                case 5:
                    return;
                 default :
                     break;

            }
        }
    }

    public void citizenInterfaze(UserController userController){
        while (true) {
            loginMenu();
            int a = Scanner.getInt("Seleccione la accion desea realizar: ");
            switch (a) {
                case 1:
                    createCitizen(userController);
                    break;
                case 2:
                    manageCitizen(userController);
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }

    }

    public void loginMenu(){
        System.out.println("************");
        System.out.println("1. create account");
        System.out.println("2. login");
        System.out.println("3. Save and exit");
        System.out.println("************");
    }

    public void citizenMenu(){
        System.out.println("************");
        System.out.println("1. Add Symptom");
        System.out.println("2. Remove symptom");
        System.out.println("3. My symptoms");
        System.out.println("4. Notify Relation");
        System.out.println("5. Log out");
        System.out.println("************");
    }

}
