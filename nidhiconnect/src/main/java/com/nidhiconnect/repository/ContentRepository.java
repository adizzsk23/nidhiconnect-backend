package com.nidhiconnect.repository;

import com.nidhiconnect.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByLanguageCode(String languageCode);
    Content findByContentKeyAndLanguageCode(String contentKey, String languageCode);
}