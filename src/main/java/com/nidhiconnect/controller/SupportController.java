package com.nidhiconnect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/support")
public class SupportController {

    @GetMapping("/faqs")
    public ResponseEntity<List<Map<String, String>>> getFaqs() {
        List<Map<String, String>> faqs = List.of(
            Map.of("question", "What is the difference between Aadhaar-linked and DBT-enabled accounts?", "answer", "An Aadhaar-linked account simply has your Aadhaar number connected. A DBT-enabled (seeded) account is specially configured and biometrically verified, allowing government systems to transfer funds like scholarships directly and instantly."),
            Map.of("question", "Why is my scholarship delayed even though my account is Aadhaar-linked?", "answer", "Because linking is not enough. Your account must be explicitly 'seeded' for DBT. Without seeding, the payment cannot be processed automatically and gets stuck in manual verification, causing delays."),
            Map.of("question", "How do I check if my account is DBT-enabled?", "answer", "You can check your status on the official PFMS (Public Financial Management System) portal or by visiting your bank and specifically asking them to confirm your 'Aadhaar seeding status for DBT'."),
            Map.of("question", "What documents do I need to enable DBT on my account?", "answer", "You will need your original Aadhaar card, your PAN card (if available), and your bank passbook."),
            Map.of("question", "Can I convert my existing account to DBT-enabled?", "answer", "Yes. You do not need to open a new account. Visit your existing bank branch and request the Aadhaar seeding form to enable DBT."),
            Map.of("question", "How long does it take to enable DBT on my account?", "answer", "Typically, the process takes 3-5 business days after you complete the biometric verification at the bank."),
            Map.of("question", "Will I get notified when my scholarship is transferred?", "answer", "Yes, you should receive an SMS from your bank and from the government portal (like NSP) once the amount is credited to your DBT-enabled account."),
            Map.of("question", "What if my biometric verification fails at the bank?", "answer", "If your fingerprint scan fails, banks have a provision for an iris (eye) scan. Ensure your demographic details (name, date of birth) match exactly on both your Aadhaar and bank records.")
        );
        return ResponseEntity.ok(faqs);
    }
}
