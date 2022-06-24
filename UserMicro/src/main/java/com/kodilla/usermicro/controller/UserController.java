package com.kodilla.usermicro.controller;

import com.kodilla.usermicro.domain.User;
import com.kodilla.usermicro.domain.UserDto;
import com.kodilla.usermicro.exceptions.UserNotFoundException;
import com.kodilla.usermicro.mapper.UserMapper;
import com.kodilla.usermicro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/all-users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(users));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> makeNewUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userService.saveNewUser(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> editUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userService.saveNewUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userMapper.mapToUserDto(userService.getUserById(userId)));
    }

    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{userId}/addFriend/{friendId}")
    public ResponseEntity<Void> addFriend(@PathVariable Long userId, @PathVariable Long friendId) throws UserNotFoundException {
        userService.addFriend(userId, friendId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{userId}/addMovieToFav/{movieId}")
    public ResponseEntity<Void> addMovieToFavorite(@PathVariable Long userId, @PathVariable String movieId) throws UserNotFoundException {
        userService.addMovieToFavorite(userId, movieId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{userId}/addMovieToWatch/{movieId}")
    public ResponseEntity<Void> addMovieToWatch(@PathVariable Long userId, @PathVariable String movieId) throws UserNotFoundException {
        userService.addMovieToFWatch(userId, movieId);
        return ResponseEntity.ok().build();
    }
}
