package Exceptions;

public class InvalidEmailCharsException extends Throwable {
    public InvalidEmailCharsException(String errorMessage) {super(errorMessage);}
}
