package com.psp.validator.service;

import com.psp.validator.model.User;
import com.psp.validator.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void getUsersTest() {
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<User> result = userRepository.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(List.of(user1, user2), result);
    }

    @Test
    void getUserTestPresent() {
        Long id = 1L;
        User user = new User();
        user.setId(id);

        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        Optional<User> result = userRepository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void getUserTestEmpty() {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        Optional<User> result = userRepository.findById(id);

        assertTrue(result.isEmpty());
    }

    @Test
    void saveUserTestValid() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setTelNr("863333333");
        user.setSlaptazodis("ABC-abcde");

        when(userRepository.save(user)).thenReturn(user);

        User result = userService.saveUser(user);

        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void saveUserTestBadEmail() {
        User user = new User();
        user.setEmail("testtest.com");
        user.setTelNr("863333333");
        user.setSlaptazodis("ABC-abcde");

        try {
            userService.saveUser(user);
            throw new RuntimeException("Should have failed");
        } catch (ResponseStatusException e) {
            assertNotNull(e);
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertTrue(e.getMessage().toLowerCase(Locale.ROOT).contains("email"));
        }
    }

    @Test
    void saveUserTestBadPhoneNumber() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setTelNr("86333333");
        user.setSlaptazodis("ABC-abcde");

        try {
            userService.saveUser(user);
            throw new RuntimeException("Should have failed");
        } catch (ResponseStatusException e) {
            assertNotNull(e);
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertTrue(e.getMessage().toLowerCase(Locale.ROOT).contains("phone"));
        }
    }

    @Test
    void saveUserTestBadPassword() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setTelNr("863333333");
        user.setSlaptazodis("ABC-abc");

        try {
            userService.saveUser(user);
            throw new RuntimeException("Should have failed");
        } catch (ResponseStatusException e) {
            assertNotNull(e);
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertTrue(e.getMessage().toLowerCase(Locale.ROOT).contains("password"));
        }
    }

    @Test
    void updateUserValid() {
        Long id = 1L;
        User user = new User();
        user.setEmail("test@test.com");
        user.setTelNr("863333333");
        user.setSlaptazodis("ABC-abcde");
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        userService.updateUser(id, user);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void updateUserNotFound() {
        Long id = 1L;
        User user = new User();
        when(userRepository.findById(id)).thenReturn(Optional.empty());
        try {
            userService.updateUser(id, user);
            throw new RuntimeException("Should have failed");
        } catch (ResponseStatusException e) {
            assertNotNull(e);
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
        }

        verify(userRepository, times(0)).save(user);
    }

    @Test
    void updateUserBadEmail() {
        Long id = 1L;
        User user = new User();
        user.setEmail("testtest.com");
        user.setTelNr("863333333");
        user.setSlaptazodis("ABC-abcde");
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        try {
            userService.updateUser(id, user);
            throw new RuntimeException("Should have failed");
        } catch (ResponseStatusException e) {
            assertNotNull(e);
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertTrue(e.getMessage().toLowerCase(Locale.ROOT).contains("email"));
        }

        verify(userRepository, times(0)).save(user);
    }

    @Test
    void updateUserBadPhoneNumber() {
        Long id = 1L;
        User user = new User();
        user.setEmail("test@test.com");
        user.setTelNr("86333333");
        user.setSlaptazodis("ABC-abcde");
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        try {
            userService.updateUser(id, user);
            throw new RuntimeException("Should have failed");
        } catch (ResponseStatusException e) {
            assertNotNull(e);
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertTrue(e.getMessage().toLowerCase(Locale.ROOT).contains("phone"));
        }

        verify(userRepository, times(0)).save(user);
    }

    @Test
    void updateUserBadPassword() {
        Long id = 1L;
        User user = new User();
        user.setEmail("test@test.com");
        user.setTelNr("863333333");
        user.setSlaptazodis("ABC-abc");
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        try {
            userService.updateUser(id, user);
            throw new RuntimeException("Should have failed");
        } catch (ResponseStatusException e) {
            assertNotNull(e);
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatus());
            assertTrue(e.getMessage().toLowerCase(Locale.ROOT).contains("password"));
        }

        verify(userRepository, times(0)).save(user);
    }

    @Test
    void deleteUser() {
        Long id = 1L;
        User user = new User();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        userService.deleteUser(id);

        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    void deleteUserNotFound() {
        Long id = 1L;
        User user = new User();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        try {
            userService.deleteUser(id);
            throw new RuntimeException("Should have failed");
        } catch (ResponseStatusException e) {
            assertNotNull(e);
            assertEquals(HttpStatus.NOT_FOUND, e.getStatus());
        }
        verify(userRepository, times(0)).deleteById(id);
    }
}
