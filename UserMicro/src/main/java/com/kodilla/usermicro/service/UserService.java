package com.kodilla.usermicro.service;

import com.kodilla.usermicro.domain.User;
import com.kodilla.usermicro.domain.UserDto;
import com.kodilla.usermicro.exceptions.UserNotFoundException;
import com.kodilla.usermicro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveNewUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id).get();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).get();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteUserByUsername(String name) {
        userRepository.deleteByUsername(name);
    }

    public void updateUser(UserDto userDto) throws UserNotFoundException {
        Optional<User> user = Optional.ofNullable(getUserById(userDto.getId()));
        if(user.isPresent()) {
            User updatedUser = new User.UserBuilder()
                    .id(user.get().getId())
                    .name(userDto.getName())
                    .surname(userDto.getSurname())
                    .username(user.get().getUsername())
                    .phoneNumber(userDto.getPhoneNumber())
                    .email(userDto.getEmail())
                    .build();
            userRepository.save(updatedUser);
        } else {
            throw new UserNotFoundException();
        }
    }

    public void addMovieToFavorite(Long userId, String movieId) throws UserNotFoundException {
        Optional<User> user = Optional.ofNullable(getUserById(userId));
        if (user.isPresent()) {
            user.get().getFavoriteList().add(movieId);
            userRepository.save(user.get());
        } else {
            throw new UserNotFoundException();
        }
    }

    public void addMovieToFWatch(Long userId, String movieId) throws UserNotFoundException {
        Optional<User> user = Optional.ofNullable(getUserById(userId));
        if (user.isPresent()) {
            user.get().getToWatchList().add(movieId);
            userRepository.save(user.get());
        } else {
            throw new UserNotFoundException();
        }
    }

    public List<User> findUserFriends(Long id) throws UserNotFoundException {
        Optional<User> user = Optional.ofNullable(getUserById(id));
        if (user.isPresent()) {
            return user.get().getFriends();
        } else {
            throw new UserNotFoundException();
        }
    }

    public void addFriend(Long userId, Long friendId) throws UserNotFoundException{
        Optional<User> user = Optional.ofNullable(getUserById(userId));
        Optional<User> friend = Optional.ofNullable(getUserById(friendId));
        if (user.isPresent() && friend.isPresent()) {
            user.get().getFriends().add(friend.get());
            friend.get().getFriends().add(user.get());
            userRepository.save(friend.get());
        } else {
            throw new UserNotFoundException();
        }
    }

    public Set<String> getFavMovieList(Long userId) throws UserNotFoundException {
        Optional<User> user = Optional.ofNullable(getUserById(userId));
        if (user.isPresent()) {
            if (user.get().getFavoriteList().isEmpty()) {
                return new HashSet<>();
            } else {
                return user.get().getFavoriteList();
            }
        } else {
            throw new UserNotFoundException();
        }
    }

    public Set<String> getToWatchMovieList(Long userId) throws UserNotFoundException {
        Optional<User> user = Optional.ofNullable(getUserById(userId));
        if (user.isPresent()) {
            if (user.get().getToWatchList().isEmpty()) {
                return new HashSet<>();
            } else {
                return user.get().getToWatchList();
            }
        } else {
            throw new UserNotFoundException();
        }
    }
}