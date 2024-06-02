package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser() {
        User user = new User("John", "Doe", LocalDate.now(), "john_doe@email.com");
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.createUser(user));
    }

    @Test
    void getUser() {
        User user = new User("John", "Doe", LocalDate.now(), "john_doe@email.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertEquals(Optional.of(user), userService.getUser(1L));
    }

    @Test
    void getUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), userService.getUser(1L));
    }

    @Test
    void getUserByEmail() {
        User user = new User("John", "Doe", LocalDate.now(), "john_doe@email.com");
        when(userRepository.findByEmail("john_doe@email.com")).thenReturn(Optional.of(user));
        assertEquals(Optional.of(user), userService.getUserByEmail("john_doe@email.com"));
    }

    @Test
    void getUserByEmailNotFound() {
        when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), userService.getUserByEmail("test@test.com"));
    }

    @Test
    void findAllUsers() {
        User user1 = new User("John", "Doe", LocalDate.now(), "john_doe@email.com");
        User user2 = new User("John", "Doe2", LocalDate.now(), "john_doe2@email.com");
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);
        assertEquals(users, userService.findAllUsers());
    }

    @Test
    void deleteUser() {
        User user = new User("John", "Doe", LocalDate.now(), "john_doe@email.com");
        userRepository.save(user);
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void updateUser() {
        User user = new User("John", "Doe", LocalDate.now(), "john_doe@email.com");
        user.setBirthdate(LocalDate.now().minusYears(1));
        userService.updateUser(user);
        when(userRepository.save(user)).thenReturn(user);
        assertEquals(user, userService.updateUser(user));
    }

    @Test
    void updateUserNotFound() {
        User user = new User("John", "Doe", LocalDate.now(), "john_doe@email.com");
        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(user));
    }
}