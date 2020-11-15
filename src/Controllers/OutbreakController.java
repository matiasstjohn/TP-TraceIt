package Controllers;

import Encounters.Date;
import Encounters.Meeting;
import Encounters.Outbreak;
import Events.Disease;
import Exceptions.InvalidDate;
import Main.TesterNuevo;
import Users.Citizen;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutbreakController {

    List<Outbreak> outbreaks;
    String filePath = "src/Persistencia/Outbreaks";

    public OutbreakController(DiseaseController diseaseController, UserController userController) throws IOException, InvalidDate {
        //this.outbreaks = new ArrayList<>();
        this.outbreaks = getOutbrakesFromFile(diseaseController, userController);
    }

    public void addOutbrake(Outbreak outbreak){
        outbreaks.add(outbreak);
    }

    public List<Outbreak> checkCitizenRelatedOutbreaks(Citizen citizen, Disease disease){
        List<Outbreak> possibleOutbreaks = new ArrayList<>();
        for (int i = 0; i < outbreaks.size(); i++) {
            if(outbreaks.get(i).getDisease().equals(disease) && outbreaks.get(i).containsCitizen(citizen)){
                possibleOutbreaks.add(outbreaks.get(i));
            }
        }
        return possibleOutbreaks;
    }

    public List<Outbreak> getOutbreaks() {
        return outbreaks;
    }

    public List<Outbreak> getSortedOutBreaks(){
            List<Outbreak> outbreaksAux = new ArrayList<>();
            outbreaksAux.addAll(outbreaks);
            List<Outbreak> outbrakesOrdenados = new ArrayList<>();
            while(outbreaksAux.size() > 0){
                Outbreak outbreak = outbreaksAux.get(0);
                for (int j = 0; j < outbreaksAux.size(); j++) {
                    if(outbreak.getAmountOfPeople() < outbreaksAux.get(j).getAmountOfPeople()){
                        outbreak = outbreaksAux.get(j);
                    }
                }
                outbrakesOrdenados.add(outbreak);
                outbreaksAux.remove(outbreak);
            }
            return outbrakesOrdenados;
    }

    private List<Outbreak> getOutbrakesFromFile(DiseaseController diseaseController, UserController userController) throws IOException, InvalidDate {
        List<Outbreak> outbreaksAux = new ArrayList<>();

        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String userInfo;
        while ((userInfo = br.readLine()) != null) {
            String[] userParts = userInfo.split(";");
            ArrayList participants = new ArrayList(Arrays.asList(userParts[1].split(",")));
            List<Citizen> participantsAux = new ArrayList();
            for (int i = 0; i < participants.size(); i++) {
                participantsAux.add(userController.getCitizenByCuil((String) participants.get(i)));
            }
            Outbreak outbreak = new Outbreak(diseaseController.getDiseaseByName(userParts[0]), participantsAux, Boolean.parseBoolean(userParts[2]), Boolean.parseBoolean(userParts[3]), MetodosPersistencia.toDate(userParts[4]), MetodosPersistencia.toDate(userParts[5]), userParts[6]);
            outbreaksAux.add(outbreak);
        }

        return outbreaksAux;
    }

    /*public static Date toDate(String date) throws InvalidDate {
        Integer month =Integer.parseInt(String.valueOf(date.substring(0,2)));
        Integer day = Integer.parseInt(String.valueOf(date.substring(3,5)));
        Integer hours = Integer.parseInt(String.valueOf(date.substring(6,8)));
        return new Date(month,day,hours);
    }*/

    public void writeOutbreaksToFile(){
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Outbreak outbreak : outbreaks) {
                List<String> citizensByCuil = new ArrayList<>();
                for (int i = 0; i < outbreak.getCitizens().size(); i++) {
                    citizensByCuil.add(outbreak.getCitizens().get(i).getCuil());
                }
                fw.write(outbreak.getDisease().getDiseaseName() + ";" + TesterNuevo.listToString(citizensByCuil) + ";" + Boolean.toString(outbreak.isActive()) + ";" + Boolean.toString(outbreak.isSecondGrade())+ ";" + outbreak.getStarts().toString() + ";" + outbreak.getLastDate().toString() + ";" + outbreak.getLocation() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
