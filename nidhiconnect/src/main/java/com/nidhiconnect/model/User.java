package com.nidhiconnect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "app_users")
@Data // Lombok annotation for getters, setters, etc.
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password; // Should be stored hashed
    private String preferredLanguage;

    @OneToMany(mappedBy = "user")
    private List<BankAccount> bankAccounts;
}