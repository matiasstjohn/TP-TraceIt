package Exceptions;

public class DiseaseAlreadyRegisteredException extends Throwable {
    public DiseaseAlreadyRegisteredException(){
        super("The disease is already registered.");
    }
}
