import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorAdditionalTest {
    @Test
    void emailValidator_domainContainsDot_returnTrue(){
        assertTrue(EmailValidator.validateEmail("vardas.pavarde@el.pastas.lt"));
    }

    @Test
    void emailValidator_domainContainsDash_returnTrue(){
        assertTrue(EmailValidator.validateEmail("vardas.pavarde@el-pastas.lt"));
    }
    @Test
    void emailValidator_localContainsSpecialChars_returnTrue(){
        assertTrue(EmailValidator.validateEmail("vardas!pavarde@el.pastas.lt"));
    }

    @Test
    void emailValidator_localStartsWithDot_returnFalse(){
        assertFalse(EmailValidator.validateEmail(".vardaspavarde@vu.mif"));
    }

    @Test
    void emailValidator_localContainsDoubleDot_returnFalse(){
        assertFalse(EmailValidator.validateEmail("a..vardaspavarde@vu.mif"));
    }

    @Test
    void emailValidator_localContainsIllegalChar_returnFalse(){
        assertFalse(EmailValidator.validateEmail("varda\\spavarde@vu.mif"));
    }

    @Test
    void emailValidator_tldShort_returnFalse(){
        assertFalse(EmailValidator.validateEmail("vardas.pavarde@el-pastas.l"));
    }

    @Test
    void emailValidator_domainContainsIllegalChar_returnFalse(){
        assertFalse(EmailValidator.validateEmail("vardas.pavarde@el_pastas.lt"));
    }
}
