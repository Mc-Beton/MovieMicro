package com.kodilla.usermicro.service;

import com.kodilla.usermicro.domain.User;
import com.kodilla.usermicro.domain.UserDto;
import com.kodilla.usermicro.exceptions.UserNotFoundException;
import com.kodilla.usermicro.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceTestSuite {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    void addNewUserTest() {
        //Given
        User user = new User.UserBuilder()
                .id(1L)
                .name("name")
                .surname("surname1")
                .username("username")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        //When
        userService.saveNewUser(user);

        //Then
        assertTrue(userService.getUserByUsername("username").getName().equals("name"));

        //Clean up
        userService.deleteUserByUsername("username");
    }

    @Test
    void getAllUsersTest() {
        //Given
        User user = new User.UserBuilder()
                .id(1L)
                .name("name")
                .surname("surname1")
                .username("username")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        User user2 = new User.UserBuilder()
                .id(2L)
                .name("name")
                .surname("surname1")
                .username("username2")
                .phoneNumber("43522345")
                .email("asdfasdgf")
                .build();

        //When
        userService.saveNewUser(user);
        userService.saveNewUser(user2);

        //Then
        assertEquals(2, userService.getAllUsers().size());

        //Clean up
        userService.deleteUserByUsername(user.getUsername());
        userService.deleteUserByUsername(user2.getUsername());
    }

    @Test
    void updateUserTest() throws UserNotFoundException {
        //Given
        User user = new User.UserBuilder()
                .id(1L)
                .name("name")
                .surname("surname1")
                .username("username")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        userService.saveNewUser(user);
        UserDto userDto = userMapper
                .mapToUserDto(userService.
                        getUserByUsername("username"));
        System.out.println(userDto.getId());

        //When
        userDto.setEmail("sdabssfg");
        userService.updateUser(userDto);

        //Then
        assertTrue(userService.getUserByUsername(user.getUsername()).getEmail().equals(userDto.getEmail()));

        //Clean up
        userService.deleteUserByUsername(user.getUsername());
    }

    @Test
    void addAndGetFriendsTest() throws UserNotFoundException {
        //Given
        User user = new User.UserBuilder()
                .id(1L)
                .name("name")
                .surname("surname1")
                .username("username")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        User user2 = new User.UserBuilder()
                .id(2L)
                .name("name")
                .surname("surname1")
                .username("username2")
                .phoneNumber("43522345")
                .email("asdfasdgf")
                .build();

        userService.saveNewUser(user);
        userService.saveNewUser(user2);

        //When
        Long id1 = userService.getUserByUsername(user.getUsername()).getId();
        Long id2 = userService.getUserByUsername(user2.getUsername()).getId();
        userService.addFriend(id1, id2);

        //Then
        assertEquals(1, userService.findUserFriends(id1).size());

        //Clean up
        userService.deleteUser(id1);
        userService.deleteUser(id2);
    }

    @Test
    void addAndGetFavoriteListTest() throws UserNotFoundException {
        //Given
        User user = new User.UserBuilder()
                .id(1L)
                .name("name")
                .surname("surname1")
                .username("username")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        userService.saveNewUser(user);
        Long id = userService.getUserByUsername(user.getUsername()).getId();

        //When
        userService.addMovieToFavorite(id,"tt12321123");
        userService.addMovieToFavorite(id,"tt23521431");

        //Then
        assertEquals(2, userService.getFavMovieList(id).size());

        //Clean up
        userService.deleteUser(id);
    }

    @Test
    void addAndGetWatchListTest() throws UserNotFoundException {
        //Given
        User user = new User.UserBuilder()
                .id(1L)
                .name("name")
                .surname("surname1")
                .username("username")
                .phoneNumber("24321545")
                .email("asdfadsg")
                .build();

        userService.saveNewUser(user);
        Long id = userService.getUserByUsername(user.getUsername()).getId();

        //When
        userService.addMovieToFWatch(id,"tt12321123");
        userService.addMovieToFWatch(id,"tt23521431");

        //Then
        assertEquals(2, userService.getToWatchMovieList(id).size());

        //Clean up
        userService.deleteUser(id);
    }
}