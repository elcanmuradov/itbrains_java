package Exceptions;

public class AlreadyCancelledException extends RuntimeException {
    public AlreadyCancelledException(String message) {
        super("Ticket already cancelled!");
    }
}
