package com.kodilla.usermicro.domain;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "SURNAME")
    private String surname;

    @NotNull
    @Column(name = "LOGIN")
    private String username;

    @Nullable
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @NotNull
    @Column(name = "EMAIL")
    private String email;

    @Column(name = "FAV_LIST")
    @ElementCollection
    private List<String> favoriteList;

    @Column(name = "TO_WATCH_LIST")
    @ElementCollection
    private List<String> toWatchList;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<User> friends = new ArrayList<>();

    @ManyToMany(mappedBy = "friends")
    private List<User> befriended = new ArrayList<>();

    public static class UserBuilder{
        private Integer id;
        private String name;
        private String surname;
        private String username;
        private String phoneNumber;
        private String email;

        public UserBuilder id(Integer id){
            this.id = id;
            return this;
        }
        public UserBuilder name(String name){
            this.name = name;
            return this;
        }
        public UserBuilder surname(String surname){
            this.surname = surname;
            return this;
        }
        public UserBuilder username(String username){
            this.username = username;
            return this;
        }

        public UserBuilder phoneNumber(String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }
        public UserBuilder email(String email){
            this.email = email;
            return this;
        }
        public User build(){
            return new User(id, name,surname,username,phoneNumber,email);
        }

    }
}
