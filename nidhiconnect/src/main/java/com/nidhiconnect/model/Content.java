package com.nidhiconnect.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contentKey; // e.g., "learn.dbt_vs_aadhaar.title"
    private String languageCode; // e.g., "en", "hi", "ta"
    
    @Column(length = 2048) // Allow for longer text content
    private String text;
}