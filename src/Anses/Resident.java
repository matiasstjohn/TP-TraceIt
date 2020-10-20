package Anses;

public class Resident {

    String cuil;
    String phoneNumber;
    String location;

    public Resident(String cuil, String phoneNumber, String location) {
        this.cuil = cuil;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    public String getCuil() {
        return cuil;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLocation() {
        return location;
    }
}
