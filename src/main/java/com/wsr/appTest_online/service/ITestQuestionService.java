package com.wsr.appTest_online.service;

import com.wsr.appTest_online.model.TestQuestion;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface ITestQuestionService {

    TestQuestion createQuestion(TestQuestion question);
    List<TestQuestion> getAllQuestions();
    Optional<TestQuestion> getQuestionById(Long id);
    List<String> getAllSubjects();
    TestQuestion updateQuestion(Long id, TestQuestion question) throws ChangeSetPersister.NotFoundException;
    void deleteQuestion(Long id);
    List<TestQuestion> getQuestionsForUser(Integer numOfQuestions, String subject);

}
