package com.projekat.svtprojekat.entity;

import com.projekat.svtprojekat.entity.enums.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private LocalDate lastLogin;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String profileImagePath;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles role;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Set<Post> posts;

    public User() {
    }

    public User(String userName, String password, String email, LocalDate lastLogin, String firstName, String lastName, String profileImagePath) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.lastLogin = lastLogin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImagePath = profileImagePath;
    }
}
