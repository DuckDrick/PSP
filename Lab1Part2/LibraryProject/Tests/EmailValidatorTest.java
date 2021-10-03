import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidatorTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void emailValidator_localPartMissing_returnFalse(){
        assertFalse(EmailValidator.validateEmail("@gmail.com"));
    }

    @Test
    void emailValidator_atSymbolMissing_returnFalse(){
        assertFalse(EmailValidator.validateEmail("vardas!gmail.com"));
    }

    @Test
    void emailValidator_incorrectDomain_returnFalse(){
        assertFalse(EmailValidator.validateEmail("vardas.pavarde@gmail.1"));
    }

    @Test
    void emailValidator_missingDomain_returnFalse(){
        assertFalse(EmailValidator.validateEmail("vardas.pavarde@gmail"));
    }

    @Test
    void emailValidator_incorrectTLD_returnFalse(){
        assertFalse(EmailValidator.validateEmail("vardas.pavarde@1.com"));
    }

    @Test
    void emailValidator_missingTLD_returnFalse(){
        assertFalse(EmailValidator.validateEmail("vardas.pavarde@.com"));
    }

    @Test
    void emailValidator_emailFormatCorrect_returnTrue(){
        assertTrue(EmailValidator.validateEmail("vardas.pavarde@gmail.com"));
    }

    @AfterEach
    void tearDown() {
    }
}