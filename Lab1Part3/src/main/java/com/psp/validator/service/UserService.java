package com.psp.validator.service;

import Validations.EmailValidator;
import Validations.PasswordValidator;
import Validations.PhoneNumberValidator;
import com.psp.validator.model.User;
import com.psp.validator.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private static final EmailValidator emailValidator = new EmailValidator();
    private static final PasswordValidator passwordValidator = new PasswordValidator();
    private static final PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    public User saveUser(User user) {
        try {
            emailValidator.validate(user.getEmail());
            passwordValidator.validate(user.getSlaptazodis());
            phoneNumberValidator.validate(user.getTelNr());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Throwable e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        return userRepository.save(user);
    }

    public void updateUser(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id: " + id + " not found");
        }

        user.setId(id);
        saveUser(user);
    }

    public void deleteUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if(optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id: " + id + " not found");
        }

        userRepository.deleteById(id);
    }
}
