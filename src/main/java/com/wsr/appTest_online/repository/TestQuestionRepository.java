package com.wsr.appTest_online.repository;

import com.wsr.appTest_online.model.TestQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface TestQuestionRepository extends JpaRepository<TestQuestion,Long> {

    @Query("SELECT DISTINCT q.subject FROM TestQuestion q")
    List<String> findDistinctSubject();
    Page<TestQuestion> findBySubject(String subject, Pageable pageable);
}
