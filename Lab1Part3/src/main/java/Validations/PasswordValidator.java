package Validations;

import Exceptions.InvalidPasswordCharsException;
import Exceptions.InvalidPasswordLengthException;
import Exceptions.PasswordMissingUppercaseLetterException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PasswordValidator {
    public boolean validate(String password) throws InvalidPasswordLengthException, PasswordMissingUppercaseLetterException, IOException, InvalidPasswordCharsException {
        if (password.length() < 8) {
            throw new InvalidPasswordLengthException("Password is too short, it must be at least 8 characters long");
        }
        if(uppercaseCount(password) == 0) {
            throw new PasswordMissingUppercaseLetterException("Password must contain uppercase letters");
        }
        if(!hasSpecialChars(password)){
            throw new InvalidPasswordCharsException("Password does not contain a special character");
        }
        return true;
    }
    public boolean hasSpecialChars(String password) throws IOException {
        File specialCharsList = new File("src/Validations/specialChars.txt");
        FileReader fr = new FileReader(specialCharsList);
        BufferedReader br = new BufferedReader(fr);
        String validSymbols = "";
        int specialChars = 0;
        int c = 0;
        while((c = br.read()) != -1){
            char character = (char) c;
            validSymbols = validSymbols + character;
        }
        for(int i = 0;  i < password.length(); i++){
            for(int j = 0; j < validSymbols.length(); j++){
                if(password.charAt(i) == validSymbols.charAt(j))
                    specialChars++;
            }
        }
        if (specialChars > 0)
            return true;
        else return false;
    }

    public int uppercaseCount(String password){
        int uppercase = 0;
        for(int i = 0; i < password.length(); i++){
            if(Character.isUpperCase(password.charAt(i)))
                uppercase++;
        }
        return uppercase;
    }
}

