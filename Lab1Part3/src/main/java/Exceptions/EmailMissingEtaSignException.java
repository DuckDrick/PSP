package Exceptions;

public class EmailMissingEtaSignException extends Throwable {
    public EmailMissingEtaSignException(String errorMessage) {
        super(errorMessage);
    }
}
