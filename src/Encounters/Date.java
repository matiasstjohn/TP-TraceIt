package Encounters;

public class Date {

    private Integer month;
    private Integer day;
    private Integer hours;

    private String monthInString;
    private String dayInString;
    private String hoursInString;

    public Date(Integer month, Integer day, Integer hours) {
        this.month = month;
        this.day = day;
        this.hours = hours;
    }

    public Integer getMonth() {
        return month;
    }

    public Integer getDay() {
        return day;
    }

    public Integer getHours() {
        return hours;
    }

    public String getTime(){
        return month + " " + day + " " + hours;
    }

    public void passDateToString(){
        monthInString = month.toString();
        dayInString = day.toString();
        hoursInString = hours.toString();
    }

}
