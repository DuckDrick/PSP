package vu.psp.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

public class PasswordCheckerTest {

    PasswordChecker passwordChecker;

    @BeforeEach
    void setup() {
        Set<Character> specialCharacters = Set.of('.', ',', '/', '*');
        Integer minLength = 8;
        passwordChecker = new PasswordChecker(specialCharacters, minLength);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "PsW.",
            "Pas*ord",
            ""
    })
    void validateLengthIsLessThanXTest(String password) {
        Boolean result = passwordChecker.validate(password);

        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "nouppercase/",
            ".,*/............"
    })
    void validateDoestContainUppercaseTest(String password) {
        Boolean result = passwordChecker.validate(password);

        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "noSpecialCharacters",
            "AAAAAAAAAAAA"
    })
    void validateDoesntContainSpecialCharacterTest(String password) {
        Boolean result = passwordChecker.validate(password);

        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "pasSwrd0:\"123",
            "1111(AbcD)@@@@@"
    })
    void validateContainsUnknownSpecialCharactersTest(String password) {
        Boolean result = passwordChecker.validate(password);

        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "thisIsGood.PSW",
            "....A...",
            "ABCDEFG*"
    })
    void validatePassesTest(String password) {
        Boolean result = passwordChecker.validate(password);

        Assertions.assertTrue(result);
    }
}
