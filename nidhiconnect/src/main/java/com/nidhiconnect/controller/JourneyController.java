package com.nidhiconnect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/journey")
public class JourneyController {

    // --- In-Memory Data Store ---
    // Using thread-safe collections for safety
    private final List<Map<String, Object>> userTransactions = new ArrayList<>();
    private final List<Map<String, Object>> userGoals = new ArrayList<>();
    private final List<Map<String, Object>> userReminders = new ArrayList<>();

    // This now acts as our live, changing balance, starting at 2350
    private double currentBalance = 2350.00;

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboard() {
        // The mock account's balance now reflects the true current balance
        Map<String, Object> mockAccount = Map.of(
            "bankName", "State Bank of India",
            "accountNumber", "****2350",
            "balance", this.currentBalance 
        );

        Map<String, Object> journeyData = Map.of(
            "account", mockAccount,
            "goals", userGoals,
            "transactions", userTransactions,
            "reminders", userReminders
        );
        return ResponseEntity.ok(journeyData);
    }

    @PostMapping("/transactions")
    public ResponseEntity<Map<String, Object>> addTransaction(@RequestBody Map<String, Object> transaction) {
        // Extract the amount from the incoming transaction
        Object amountObj = transaction.get("amount");
        if (amountObj instanceof Number) {
            double amount = ((Number) amountObj).doubleValue();

            // Update the central balance
            this.currentBalance += amount;

            // Add the transaction to our list and send a success response
            userTransactions.add(transaction);
            return ResponseEntity.status(201).body(transaction);
        }
        // Return an error if the amount is not a valid number
        return ResponseEntity.badRequest().body(Map.of("error", "Invalid amount"));
    }

    @PostMapping("/goals")
    public ResponseEntity<Map<String, Object>> addGoal(@RequestBody Map<String, Object> goal) {
        userGoals.add(goal);
        return ResponseEntity.status(201).body(goal);
    }

    @PostMapping("/reminders")
    public ResponseEntity<Map<String, Object>> addReminder(@RequestBody Map<String, Object> reminder) {
        userReminders.add(reminder);
        return ResponseEntity.status(201).body(reminder);
    }
}