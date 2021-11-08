import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneValidatorAdditionalTest {
    @Test
    void phoneValidator_phoneNumberStartsWith8_returnsTrue(){
        assertTrue(PhoneValidator.validatePhoneFormat("863333333", "lt"));
    }

    @Test
    void phoneValidator_phoneNumberPL_returnsTrue(){
        assertTrue(PhoneValidator.validatePhoneFormat("+48123456", "pl"));
    }

    @Test
    void phoneValidator_phoneNumberPLBadLength_returnsFalse(){
        assertFalse(PhoneValidator.validatePhoneFormat("+481234568", "pl"));
    }

    @Test
    void phoneValidator_prefixShouldNotBeAddedd() {
        assertEquals("6333333", PhoneValidator.addPhoneNumberPrefix("6333333", "lt"));
    }
}
