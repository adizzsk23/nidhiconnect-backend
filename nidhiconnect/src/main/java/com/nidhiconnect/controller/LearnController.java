package com.nidhiconnect.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/learn")
public class LearnController {

    @GetMapping("/content")
    public ResponseEntity<Map<String, String>> getLearnContent(@RequestHeader("Accept-Language") String language) {
        // TODO: Fetch content from the ContentRepository based on the language
        Map<String, String> learnContent = Map.of(
            "title", "DBT Enabled vs. Aadhaar Linked (" + language + ")",
            "explanation", "This is a detailed explanation...",
            "steps_title", "Steps to Enable DBT",
            "steps_body", "Step 1: Visit bank. Step 2: Fill form...",
            "video_url_1", "https://example.com/video1_" + language,
            "video_url_2", "https://example.com/video2_" + language
        );
        return ResponseEntity.ok(learnContent);
    }
}