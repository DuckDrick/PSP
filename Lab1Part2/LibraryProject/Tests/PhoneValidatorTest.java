import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneValidatorTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void phoneValidator_phoneNumberNull_returnFalse(){
        assertFalse(PhoneValidator.validatePhoneFormat(null, "lt"));
    }

    @Test
    void phoneValidator_phoneNumberEmpty_returnFalse(){
        assertFalse(PhoneValidator.validatePhoneFormat("", "lt"));
    }

    @Test
    void phoneValidator_phoneNumberFirstDigitInvalid_returnFalse(){
        assertFalse(PhoneValidator.validatePhoneFormat("661234567", "lt"));
    }

    @Test
    void phoneValidator_phoneNumberContainsSymbol_returnFalse(){
        assertFalse(PhoneValidator.validatePhoneFormat("861A34567", "lt"));
    }

    @Test
    void phoneValidator_phoneNumberTooShort_returnFalse(){
        assertFalse(PhoneValidator.validatePhoneFormat("86123456", "lt"));
    }

    @Test
    void phoneValidator_phoneNumberTooLong_returnFalse(){
        assertFalse(PhoneValidator.validatePhoneFormat("8612345678", "lt"));
    }

    @Test
    void phoneValidator_phoneNumberFormatCorrect_returnTrue(){
        assertTrue(PhoneValidator.validatePhoneFormat("861234567", "lt"));
    }

    @Test
    void phoneValidator_phoneNumberPrefixValid_returnTrue(){
        assertEquals("+37061234567", PhoneValidator.addPhoneNumberPrefix("861234567", "lt"));
    }

    @AfterEach
    void tearDown() {
    }
}