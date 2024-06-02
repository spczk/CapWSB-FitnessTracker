package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import com.capgemini.wsb.fitnesstracker.user.api.UserSimpleDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserServiceImpl userService;

    private final UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers()
                          .stream()
                          .map(userMapper::toDto)
                          .toList();
    }

    @PostMapping("/add")
    public User addUser(@RequestBody UserDto userDto) {

        User user = userMapper.toEntity(userDto);

        return userService.createUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/list")
    public List<UserSimpleDto> listUsers() {
        return userService.findAllUsers().stream()
                          .map(userMapper::toSimpleDto)
                          .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUser(id)
                          .map(userMapper::toDto)
                          .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                          .map(userMapper::toDto)
                          .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @GetMapping("/older/{age}")
    public List<UserDto> getUsersOlderThan(@PathVariable int age) {
        return userService.findAllUsers().stream()
                          .filter(user -> user.getBirthdate().getYear() < (LocalDate.now().getYear() - age))
                          .map(userMapper::toDto)
                          .toList();
    }

    @PutMapping("/update_birth_date/{id}")
    public UserDto updateBirthDate(@PathVariable Long id, @RequestBody LocalDate birthdate) {
        User user = userService.getUser(id)
                              .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setBirthdate(birthdate);
        return userMapper.toDto(userService.createUser(user));
    }

}