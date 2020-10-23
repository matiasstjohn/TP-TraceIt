package Encounters;

public class Date {

    private String month;
    private String day;
    private String hours;

    public Date(String month, String day, String hours) {
        this.month = month;
        this.day = day;
        this.hours = hours;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public String getHours() {
        return hours;
    }

    public String getTime(){
        return month + " " + day + " " + hours;
    }

}
