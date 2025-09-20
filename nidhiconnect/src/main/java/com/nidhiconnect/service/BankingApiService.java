package com.nidhiconnect.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BankingApiService {
    public boolean linkBankAccount(Long userId, String consentToken) {
        System.out.println("Linking bank account for user " + userId + " with consent.");
        return true;
    }

    public BigDecimal getAccountBalance(String apiToken) {
        System.out.println("Fetching mock balance...");
        return new BigDecimal("5432.10");
    }

    public List<String> getTransactions(String apiToken) {
        System.out.println("Fetching mock transactions...");
        return List.of("Transaction 1: -Rs.500 | Transaction 2: +Rs.2000");
    }
}