package com.kodilla.usermicro.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private String username;
    private String phoneNumber;
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDto(String name, String surname, String username, String phoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
