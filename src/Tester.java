public class Tester {
        public static void main(String[] args) {
            UserController userController = new UserController();
            DiseaseController diseaseController = new DiseaseController();
            Citizen juan = new Citizen("1111","2222");
            Administrator admin = new Administrator("admin1", "contra", userController, diseaseController);

            userController.addAdministrator(admin);

            admin.createSymptoms("Fiebre");

            Administrator admin2 = admin.createAdministrator("admin2", "chau");

            admin2.createSymptoms("dolor de cabeza");

            for (int i = 0; i < userController.getAdministrators().size(); i++) {
                System.out.println(userController.getAdministrators().get(i).getName());
            }
            for (int i = 0; i < diseaseController.getSymptoms().size(); i++) {
                System.out.println(diseaseController.getSymptoms().get(i));
            }

        }
}

