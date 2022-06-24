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
}
