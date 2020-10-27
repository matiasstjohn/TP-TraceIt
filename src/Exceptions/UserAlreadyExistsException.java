package Exceptions;

public class UserAlreadyExistsException extends Throwable {
    public UserAlreadyExistsException() {
        super("User already exists. Try again.");
    }
}
