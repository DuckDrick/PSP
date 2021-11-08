import java.util.Locale;
import java.util.Map;

public class PhoneValidator {

    private static final Map<String, PhoneNumber> config = Map.of(
            "lt", new PhoneNumber("+370", 8),
            "pl", new PhoneNumber("+48", 6)
    );
    public static boolean validatePhoneFormat(String number, String country) {
        if(number == null) return false;
        country = country.toLowerCase(Locale.ROOT);
        number = addPhoneNumberPrefix(number, country);
        if(!config.containsKey(country)) return false;
        PhoneNumber phoneNumberConfig = config.get(country);
        if(!number.startsWith(phoneNumberConfig.prefix)) return false;
        if(number.length() - phoneNumberConfig.prefix.length() != phoneNumberConfig.length) return false;

        for (char num : number.substring(1).toCharArray()) {
            if('0' > num || num > '9') return false;
        }
        return true;
    }

    public static String addPhoneNumberPrefix(String number, String country) {
        if(number.length() == 0 || !country.toLowerCase(Locale.ROOT).equals("lt") || number.charAt(0) != '8') return number;
        return "+370" + number.substring(1);
    }
}
