package Validations;

import Exceptions.InvalidPhoneNumberCharsException;
import Exceptions.InvalidPhoneNumberLengthException;
import Exceptions.InvalidPhoneNumberPrefixException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PhoneNumberValidator {
    public boolean validate(String phoneNumber) throws InvalidPhoneNumberCharsException, InvalidPhoneNumberLengthException, InvalidPhoneNumberPrefixException, IOException {
        if(phoneNumber.charAt(0) == '8')
            phoneNumber = replace8With370(phoneNumber);
        if(!checkForInvalidSymbols(phoneNumber)) {
            throw new InvalidPhoneNumberCharsException("Number is invalid, not all characters are numbers");
        }
        if(!differentCountryNumbers(phoneNumber)){
            return false;
        }
        return true;
    }
    public boolean checkForInvalidSymbols(String phoneNumber){
        char[] phoneNr = phoneNumber.toCharArray();
        for(int i = 1; i < phoneNumber.length(); i++)
            if(!Character.isDigit(phoneNr[i]) && (phoneNr[0] == '+' || phoneNr[0] == '8')){
                return false;
            }
            return true;
    }
    public String replace8With370(String phoneNumber){
        String phoneNumberNew;
        phoneNumberNew = phoneNumber.replace("8", "+370");
        return phoneNumberNew;
    }
    public boolean differentCountryNumbers(String phoneNumber) throws IOException, InvalidPhoneNumberPrefixException, InvalidPhoneNumberLengthException {
        File validNumbers = new File("src/Validations/differentCountryData.txt");
        FileReader fr = new FileReader(validNumbers);
        BufferedReader br = new BufferedReader(fr);
        List<Integer> lengths = new ArrayList<Integer>();
        List<String> prefixes = new ArrayList<String>();
        String prefix;
        int length;
        String line;
        while ((line = br.readLine()) != null) {
            String[] tmp = line.split(" ");
            prefix = tmp[0];
            length = Integer.parseInt(tmp[1]);
            prefixes.add(prefix);
            lengths.add(length);
        }
        int matchingPrefix = 0;
        int matchingLength = 0;
        for(int i = 0; i < prefixes.size(); i++){
            if(phoneNumber.contains(prefixes.get(i)))
                matchingPrefix++;
            if(phoneNumber.length() == lengths.get(i))
                matchingLength++;
        }
        if(matchingPrefix == 0) {
            throw new InvalidPhoneNumberPrefixException("Invalid phone number prefix");
        }
        else if(matchingLength == 0) {
            throw new InvalidPhoneNumberLengthException("Invalid phone number length");
        }
        return matchingPrefix > 0 && matchingLength > 0;
    }

}
