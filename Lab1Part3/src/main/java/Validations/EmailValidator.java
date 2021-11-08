package Validations;

import Exceptions.EmailMissingEtaSignException;
import Exceptions.InvalidEmailCharsException;
import Exceptions.InvalidEmailDomainException;

public class EmailValidator{

    public boolean validate(String email) throws EmailMissingEtaSignException, InvalidEmailCharsException, InvalidEmailDomainException{
        if (!email.contains("@")){
            throw new EmailMissingEtaSignException("No @ sign detected, email is invalid");

        }
        if(hasInvalidSymbols(email)){
            throw new InvalidEmailCharsException("Invalid characters detected");
        }
        if(hasInvalidTLD(email)){
            throw new InvalidEmailDomainException("Invalid top level domain detected");
        }
        if(hasInvalidDomain(email)){
            throw new InvalidEmailCharsException("Invalid domain detected");
        }
        return true;
    }
    public boolean hasInvalidSymbols(String email){
        int validSymbols = 0;
        for(int i = 0; i < email.length(); i++){
            if(
                    (email.charAt(i) >= 'a' && email.charAt(i) <= 'z') ||
                    (email.charAt(i) >= 'A' && email.charAt(i) <= 'Z') ||
                    (email.charAt(i) >= '0' && email.charAt(i) <= '9') ||
                    (email.charAt(i) == '@') ||
                    (email.charAt(i) == '.') ||
                    (email.charAt(i) == '-') ||
                    (email.charAt(i) == '_'))
                validSymbols++;
        }
            if(validSymbols == email.length())
                return false;
            else return true;
    }
    public boolean hasInvalidTLD(String email){
        int invalidSymbols = 0;
        int lastDotPos = email.lastIndexOf('.');
        for (int i = lastDotPos + 1; i < email.length(); i++){
            if(email.charAt(i) <= 'a' || email.charAt(i) >= 'z')
                invalidSymbols++;
        }
        return invalidSymbols > 0;
    }
    public boolean hasInvalidDomain(String email){
        int lastDotPos = email.lastIndexOf('.');
        int EtaPos = email.lastIndexOf('@');
        int validSymbols = 0;
        int domainLength = lastDotPos - EtaPos - 1;
        for(int i = EtaPos + 1; i < lastDotPos; i++){
            if(
                    (email.charAt(i) >= 'a' && email.charAt(i) <= 'z') ||
                    (email.charAt(i) >= '0' && email.charAt(i) <= '9') ||
                    (email.charAt(i) == '.'))
                validSymbols++;
        }
        if(validSymbols == domainLength)
            return false;
        else return true;
    }
}
