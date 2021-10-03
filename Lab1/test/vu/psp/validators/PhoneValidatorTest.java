package vu.psp.validators;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

public class PhoneValidatorTest {

    PhoneValidator phoneValidator;

    @BeforeEach
    void setup() {
        Map<String, Integer> validationRules = Map.of(
                "+371", 8,
                "+48", 9

        );
        phoneValidator = new PhoneValidator(validationRules);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "abcd123",
            "86333333+",
            "++37063333333",
            "(92)218/*+1AZz",
            "+3711234s678"
    })
    void validateNoOtherCharactersTest(String number) {
        Boolean result = phoneValidator.validate(number);

        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+37063333333",
            "+37112345678",
            "+44123456789",
            "863333333"
    })
    void validateCorrectNumberTest(String number) {
        Boolean result = phoneValidator.validate(number);

        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+37063333",
            "+3711",
            "+481234",
            "8633"
    })
    void validateShortNumberTest(String number) {
        Boolean result = phoneValidator.validate(number);

        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+37063333333333",
            "+3711333333333345",
            "+4812341234567891",
            "8633111111111111"
    })
    void validateLongNumberTest(String number) {
        Boolean result = phoneValidator.validate(number);

        Assertions.assertFalse(result);
    }
}
