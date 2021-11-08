import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class PasswordCheckerAdditionalTest {
    @Test
    void passwordChecker_containsSpecialCharacterNotMarkedAsSpecial_returnFalse() {
        assertFalse(PasswordChecker.validatePassword("ACD-852defg)", 8));
    }
}
