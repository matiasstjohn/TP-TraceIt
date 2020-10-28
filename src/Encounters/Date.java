package Encounters;

import Exceptions.InvalidDate;

public class Date {

    private Integer month;
    private Integer day;
    private Integer hours;

    public Date(Integer month, Integer day, Integer hours) throws InvalidDate {
        if (!conditions(month, day, hours)){
            throw new InvalidDate();
        }
        this.month = month;
        this.day = day;
        this.hours = hours;
    }

    public Integer convertToHours(){
        return ((this.month * 31 * 24) + (this.day * 24) + (this.hours));
    }

    public Integer compareDates(Date date){
        return (this.convertToHours() - date.convertToHours());
    }

    public boolean conditions(Integer month, Integer day, Integer hours){
        if ((month > 12) || (day > 31) || (hours > 24)){
            return false;
        }
        return true;
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

    public String toString(){
        return "" + month + "/" + day + " " + hours;
    }
}
