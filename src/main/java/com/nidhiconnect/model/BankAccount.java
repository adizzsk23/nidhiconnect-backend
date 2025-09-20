package com.nidhiconnect.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String bankName;
    private String accountNumber; // Encrypt this data
    private String apiToken; // Encrypted token for third-party API access
    private BigDecimal balance;
}