package Exceptions;

public class NoSeatsAvailableException extends RuntimeException {
    public NoSeatsAvailableException() {
        super("There are no seats available for this film.");
    }
}
