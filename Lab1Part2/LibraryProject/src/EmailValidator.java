import java.util.Locale;
import java.util.Set;

public class EmailValidator {
    public static boolean validateEmail(String email) {
        if(email == null) return false;

        Integer atLocation = email.indexOf('@');
        Integer lastDot = email.lastIndexOf('.');

        if(lastDot < atLocation || lastDot == -1 || atLocation == -1) return false;
        if(email.lastIndexOf('@') != atLocation) return false;
        String local = email.substring(0, atLocation);
        String domain = email.substring(atLocation + 1, lastDot);
        String topLevelDomain = email.substring(lastDot + 1);

        return validLocal(local) && validDomain(domain) && validTopLevelDomain(topLevelDomain);
    }

    private static Boolean validTopLevelDomain(String topLevelDomain) {
        if(topLevelDomain.length() < 2 || topLevelDomain.length() > 6) return false;
        for (char character : topLevelDomain.toLowerCase(Locale.ROOT).toCharArray()) {
            if(!('a' <= character && character <= 'z')){
                return false;
            }
        }
        return true;
    }

    private static boolean validDomain(String domain) {
        if(domain.length() < 1 || domain.length() > 63) return false;
        if(domain.contains("..")) return false;
        for (char character : domain.toLowerCase(Locale.ROOT).toCharArray()) {
            if(!('a' <= character && character <= 'z' || character == '-' || character == '.')){
                return false;
            }
        }
        return true;
    }
    private static final Set<Character> allowedChars = Set.of('!','#','$','%','&','*','+','-','/','=','?','^','_','`','{','|','\'','}','~');

    private static boolean validLocal(String local) {
        if(local.length() == 0) return false;
        if(local.contains("..")) return false;
        if(local.charAt(0) == '.' || local.charAt(local.length() - 1) == '.') return false;

        for (int index = 0; index < local.length(); index++) {
            char character = local.charAt(index);
            if(!('A' <= character && character <= 'Z'
                    || 'a' <= character && character <= 'z'
                    || '0' <= character && character <= '9'
                    || allowedChars.contains(character)
                    || character == '.')){
                return false;
            }
        }
        return true;
    }
}
