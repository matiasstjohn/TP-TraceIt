package Encounters;

public class Date {

    private Integer month;
    private Integer day;
    private Integer hours;

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

}
