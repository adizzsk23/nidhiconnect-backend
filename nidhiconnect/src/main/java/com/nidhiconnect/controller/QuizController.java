package com.nidhiconnect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    // Using constants to avoid duplicated string warnings
    private static final String NAME = "name";
    private static final String OFFICIAL_URL = "officialUrl";
    private static final String QUESTIONS = "questions";
    private static final String QUESTION = "question";
    private static final String OPTIONS = "options";
    private static final String CORRECT_ANSWER_INDEX = "correctAnswerIndex";
    private static final String MANDATORY = "mandatory";
    private static final String TITLE = "title";
    private static final String GADGET = "gadget";
    private static final String BRIEFING = "briefing";
    private static final String POINTS = "points";

    private static final Map<String, Map<String, Object>> scholarshipData = Map.of(
        "nmms", Map.of(
            NAME, "National Means-cum-Merit Scholarship (NMMS)",
            OFFICIAL_URL, "https://scholarships.gov.in/",
            QUESTIONS, List.of(
                Map.of(QUESTION, "What is the minimum class requirement to apply?", OPTIONS, List.of("Class 6", "Class 7", "Class 8", "Class 9"), CORRECT_ANSWER_INDEX, 2, MANDATORY, true),
                Map.of(QUESTION, "What is the maximum family income allowed?", OPTIONS, List.of("₹2.5 lakh/yr", "₹3.5 lakh/yr", "₹4.5 lakh/yr", "No limit"), CORRECT_ANSWER_INDEX, 1, MANDATORY, true),
                Map.of(QUESTION, "Until which class is the scholarship awarded?", OPTIONS, List.of("Class 10", "Class 11", "Class 12", "Graduation"), CORRECT_ANSWER_INDEX, 2, MANDATORY, false),
                Map.of(QUESTION, "Does NMMS require students to study in government schools?", OPTIONS, List.of("Yes", "No"), CORRECT_ANSWER_INDEX, 0, MANDATORY, true)
            )
        ),
        "ntse", Map.of(
            NAME, "National Talent Search Examination (NTSE)",
            OFFICIAL_URL, "https://ncert.nic.in/ntse.php",
            QUESTIONS, List.of(
                Map.of(QUESTION, "At which class level can students apply?", OPTIONS, List.of("Class 8", "Class 9", "Class 10", "Class 12"), CORRECT_ANSWER_INDEX, 2, MANDATORY, true),
                Map.of(QUESTION, "Is NTSE based on income or merit?", OPTIONS, List.of("Income", "Merit", "Both", "None"), CORRECT_ANSWER_INDEX, 1, MANDATORY, true),
                Map.of(QUESTION, "What is the monthly scholarship amount at UG/PG level?", OPTIONS, List.of("₹1,000", "₹1,250", "₹2,000", "₹5,000"), CORRECT_ANSWER_INDEX, 2, MANDATORY, false)
            )
        ),
        "inspire", Map.of(
            NAME, "INSPIRE Scholarship",
            OFFICIAL_URL, "https://www.online-inspire.gov.in/",
            QUESTIONS, List.of(
                Map.of(QUESTION, "Who is eligible?", OPTIONS, List.of("Top 1% in Class 12 boards / JEE-NEET cleared", "Any Class 12 pass student", "Sports quota students"), CORRECT_ANSWER_INDEX, 0, MANDATORY, true),
                Map.of(QUESTION, "Which stream must students pursue?", OPTIONS, List.of("Arts", "Commerce", "Science", "Any"), CORRECT_ANSWER_INDEX, 2, MANDATORY, true),
                Map.of(QUESTION, "What is the annual scholarship amount?", OPTIONS, List.of("₹50,000", "₹60,000", "₹70,000", "₹80,000"), CORRECT_ANSWER_INDEX, 3, MANDATORY, false)
            )
        ),
         "csss", Map.of(
            NAME, "Central Sector Scheme of Scholarship (CSSS)",
            OFFICIAL_URL, "https://scholarships.gov.in/",
            QUESTIONS, List.of(
                Map.of(QUESTION, "Which students can apply?", OPTIONS, List.of("Class 10 toppers", "Class 12 toppers (top 20 percentile)", "Graduate students"), CORRECT_ANSWER_INDEX, 1, MANDATORY, true),
                Map.of(QUESTION, "Maximum family income allowed?", OPTIONS, List.of("₹2.5 lakh/yr", "₹3.5 lakh/yr", "₹4.5 lakh/yr"), CORRECT_ANSWER_INDEX, 2, MANDATORY, true),
                Map.of(QUESTION, "Does CSSS require regular degree programs?", OPTIONS, List.of("Yes", "No"), CORRECT_ANSWER_INDEX, 0, MANDATORY, true)
            )
        )
    );

    @GetMapping("/scholarships")
    public ResponseEntity<List<Map<String, String>>> getScholarshipsList() {
        List<Map<String, String>> scholarshipList = scholarshipData.entrySet().stream()
            .map(entry -> Map.of(
                "key", entry.getKey(),
                NAME, (String) entry.getValue().get(NAME)
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(scholarshipList);
    }

    @GetMapping("/scholarship/{key}")
    public ResponseEntity<Map<String, Object>> getScholarshipQuiz(@PathVariable String key) {
        Map<String, Object> quiz = scholarshipData.get(key);
        if (quiz == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(quiz);
    }

    @GetMapping("/dbt-knowledge")
    public ResponseEntity<List<Map<String, Object>>> getDbtQuizQuestions(@RequestHeader("Accept-Language") String language) {
        List<Map<String, Object>> quizMissions = List.of(
            Map.of(TITLE, "Operation: Deep Cover", GADGET, "Financial Scanner", BRIEFING, "Agent, to receive scholarship funds securely, you must choose the correct account type for direct government transfers. Select your cover.", OPTIONS, List.of("Standard Savings Account", "Aadhaar-Linked Account", "DBT-Enabled (Seeded) Account", "Student-Only Account"), CORRECT_ANSWER_INDEX, 2, POINTS, 500),
            Map.of(TITLE, "Operation: Bank Infiltration", GADGET, "Identity Verifier", BRIEFING, "You're inside the bank. To initiate Aadhaar seeding for DBT, what is the most critical set of documents you need to present?", OPTIONS, List.of("School ID and a photograph", "Aadhaar Card, PAN Card, and Bank Passbook", "Passport and international visa", "Just your mobile number"), CORRECT_ANSWER_INDEX, 1, POINTS, 500),
            Map.of(TITLE, "Operation: Biometric Lock", GADGET, "Bio-Scanner 007", BRIEFING, "The bank officer asks for verification to link your Aadhaar for DBT. Which action completes the 'seeding' process securely?", OPTIONS, List.of("Signing a form", "Providing a fingerprint or iris scan", "Submitting a photocopy of your Aadhaar", "Answering security questions"), CORRECT_ANSWER_INDEX, 1, POINTS, 1000),
            Map.of(TITLE, "Operation: Status Check", GADGET, "Intel Comms Link", BRIEFING, "Your seeding request is submitted. Where can you officially verify if your account is successfully enabled for DBT payments?", OPTIONS, List.of("Calling the bank's customer care", "Checking your email for a confirmation", "The official PFMS (Public Financial Management System) portal", "Asking a friend who works at another bank"), CORRECT_ANSWER_INDEX, 2, POINTS, 500),
            Map.of(TITLE, "Operation: Final Advantage", GADGET, "Intel Debrief", BRIEFING, "What is the ultimate advantage of having a DBT-seeded account over one that is merely Aadhaar-linked?", OPTIONS, List.of("It looks more official on your bank statement", "You can withdraw more cash daily", "It guarantees direct, faster, and failure-proof receipt of government benefits", "You get a higher interest rate"), CORRECT_ANSWER_INDEX, 2, POINTS, 1000)
        );
        return ResponseEntity.ok(quizMissions);
    }
}