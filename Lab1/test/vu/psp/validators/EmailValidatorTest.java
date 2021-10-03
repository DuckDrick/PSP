package vu.psp.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EmailValidatorTest {

    EmailValidator emailValidator;

    @BeforeEach
    void setup() {
        emailValidator = new EmailValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "email.email.com",
            "email"
    })
    void validateNoAtSignTest(String email){
        Boolean result = emailValidator.validate(email);
        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "@",
            "email@email@email@email.com"
    })
    void validateInvalidWithAtSignTest(String email){
        Boolean result = emailValidator.validate(email);
        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "@email.com",
            "this is\"not\\allowed@example.com",
            "1234567890123456789012345678901234567890123456789012345678901234+x@example.com",
            "andrius()@gmail.com",
            "\"(),:;<>@[\\]@mail.com",
            ""
    })
    void validateInvalidSymbolsTest(String email){
        Boolean result = emailValidator.validate(email);
        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "email@",
            "email@email_test.com",
            "email@email*test.com",
            "emial@abcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijaaaa.com",
            "email@-email.com",
            "email@email-.com",
            "email@em--ail.com"

    })
    void validateInvalidDomainTest(String email){
        Boolean result = emailValidator.validate(email);
        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "@email",
            "email@mail.a",
            "email@mail.1com",
            "email@mail.11",
            "email@mail.*",
            "email@mail.c*m",
            "email@mail.abcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijaaaa"
    })
    void validateInvalidTLDTest(String email){
        Boolean result = emailValidator.validate(email);
        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "andrius.bertasius@mif.stud.vu.lt",
            "user-@email.org",
            "user%email.com@email.org",
            "mailhost!username@email.org",
            "example@s.example",
            "email.email+email@email.com",
            "!#$%&'*+-/=?^_`{|}~@email.com",
            "email@email-email.com"
    })
    void validateValidEmailsTest(String email){
        Boolean result = emailValidator.validate(email);
        Assertions.assertTrue(result);
    }

}
