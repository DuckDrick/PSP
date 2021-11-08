import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void passwordChecker_passwordNull_returnFalse(){
        assertFalse(PasswordChecker.validatePassword(null, 8));
    }
    @Test
    void passwordChecker_passwordTooShort_returnFalse(){
        assertFalse(PasswordChecker.validatePassword("Abcdef!", 8));
    }

    @Test
    void passwordChecker_noUppercaseSymbol_returnFalse(){
        assertFalse(PasswordChecker.validatePassword("abcdefg!", 8));
    }

    @Test
    void passwordChecker_noSpecialSymbol_returnFalse(){
        assertFalse(PasswordChecker.validatePassword("Abcdefgh", 8));
    }

    @Test
    void passwordChecker_passwordValid_returnTrue(){
        assertTrue(PasswordChecker.validatePassword("Abcdefg!", 8));
    }

    @AfterEach
    void tearDown() {
    }
}