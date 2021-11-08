package Exceptions;

public class InvalidPasswordCharsException extends Throwable {
    public InvalidPasswordCharsException(String errorMessage) { super(errorMessage);
    }
}
