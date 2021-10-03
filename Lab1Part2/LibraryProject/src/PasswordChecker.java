import java.util.List;
import java.util.Set;

public class PasswordChecker {
    private static final Set<Character> specialSymbols = Set.of('!', '@');
    public static boolean validatePassword(String password, Integer length) {
        if(password == null) return false;
        if(password.length() < length) return false;

        Boolean containsSpecial = false;
        Boolean containsUppercase = false;
        for (char letter : password.toCharArray()) {
            if(specialSymbols.contains(letter)) containsSpecial = true;
            if('A' <= letter && letter <= 'Z') containsUppercase = true;
        }

        return containsSpecial && containsUppercase;
    }
}
