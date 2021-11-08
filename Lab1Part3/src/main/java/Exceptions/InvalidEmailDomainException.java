package Exceptions;

public class InvalidEmailDomainException extends Throwable {
    public InvalidEmailDomainException(String errorMessage) {super(errorMessage);
    }
}
