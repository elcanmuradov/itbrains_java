package Exceptions;

public class DuplicateMovieException extends RuntimeException {
    public DuplicateMovieException(String message) {
        super("There cannot be two films with the same name!");
    }
}
