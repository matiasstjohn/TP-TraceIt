package Controllers;

import Encounters.Date;
import Exceptions.InvalidDate;

import java.util.List;

public class MetodosPersistencia {


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

    public static Date toDate(String date) throws InvalidDate {
        Integer month =Integer.parseInt(String.valueOf(date.substring(0,2)));
        Integer day = Integer.parseInt(String.valueOf(date.substring(3,5)));
        Integer hours = Integer.parseInt(String.valueOf(date.substring(6,8)));
        return new Date(month,day,hours);
    }
}
