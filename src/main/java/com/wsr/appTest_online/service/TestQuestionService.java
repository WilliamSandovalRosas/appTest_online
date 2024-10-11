package com.wsr.appTest_online.service;

import com.wsr.appTest_online.model.TestQuestion;
import com.wsr.appTest_online.repository.TestQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestQuestionService implements ITestQuestionService {

    private final TestQuestionRepository testQuestionRepository;

    @Override
    public TestQuestion createQuestion(TestQuestion question) {

        return testQuestionRepository.save(question);
    }

    @Override
    public List<TestQuestion> getAllQuestions() {

        return testQuestionRepository.findAll();
    }

    @Override
    public Optional<TestQuestion> getQuestionById(Long id) {

        return testQuestionRepository.findById(id);
    }

    @Override
    public List<String> getAllSubjects() {

        return testQuestionRepository.findDistinctSubject();
    }

    @Override
    public TestQuestion updateQuestion(Long id, TestQuestion question) throws ChangeSetPersister.NotFoundException {
        Optional<TestQuestion> theQuestion = this.getQuestionById(id);
        if(theQuestion.isPresent()){
            TestQuestion updatedQuestion = theQuestion.get();
            updatedQuestion.setQuestion(question.getQuestion());
            updatedQuestion.setChoices(question.getChoices());
            updatedQuestion.setCorrectAnswer(question.getCorrectAnswer());
            return  testQuestionRepository.save(updatedQuestion);

        }
        else{
            throw new ChangeSetPersister.NotFoundException();
        }

    }

    @Override
    public void deleteQuestion(Long id) {

        testQuestionRepository.deleteById(id);
    }


    @Override
    public List<TestQuestion> getQuestionsForUser(Integer numOfQuestions, String subject) {

        Pageable pageable = PageRequest.of(0, numOfQuestions);
        return testQuestionRepository.findBySubject(subject,pageable).getContent();
              
    }
}
