package com.wsr.appTest_online.controller;

import com.wsr.appTest_online.model.TestQuestion;
import com.wsr.appTest_online.service.ITestQuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/apiTest")// Tengo dudas
@RequiredArgsConstructor
public class TestQuestionController {

    private final ITestQuestionService testQuestionService;

    @PostMapping("/create-new-question")
    public ResponseEntity<TestQuestion> createQuestion(@Valid @RequestBody TestQuestion question){
        TestQuestion createdQuestion = testQuestionService.createQuestion(question);
        return ResponseEntity.status(CREATED).body(createdQuestion);

    }

    @GetMapping("/all-questions")
    public ResponseEntity<List<TestQuestion>> getAllQuestions(){
        List<TestQuestion> questions = testQuestionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<TestQuestion> getQuestionById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {

        Optional<TestQuestion> theQuestion = testQuestionService.getQuestionById(id);
        if(theQuestion.isPresent()){
            return ResponseEntity.ok(theQuestion.get());
        }
        else{
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<TestQuestion> updateTestQuestion(@PathVariable Long id,@RequestBody TestQuestion question ) throws ChangeSetPersister.NotFoundException{
        TestQuestion updatedQuestion = testQuestionService.updateQuestion(id, question);
        return ResponseEntity.ok(updatedQuestion);

    }

    @DeleteMapping("/{id}/delete")

    public ResponseEntity<Void> deleteTestQuestion(@PathVariable Long id){
        testQuestionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/subjects")

    public ResponseEntity<List<String>> getAllSubject(){

        List<String> subjects = testQuestionService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/fetch-questions-for-user")

    public ResponseEntity<List<TestQuestion>> getQuestionsForUser(
            @RequestParam Integer numOfQuestions, @RequestParam String subject){

        List<TestQuestion> allQuestions = testQuestionService.getQuestionsForUser(numOfQuestions, subject);
        List<TestQuestion> mutableQuestions = new ArrayList<>(allQuestions);
        Collections.shuffle(mutableQuestions);
        int availableQuestion = Math.min(numOfQuestions,mutableQuestions.size());
        List<TestQuestion> randomTestQuestions = mutableQuestions.subList(0,availableQuestion);
        return ResponseEntity.ok(randomTestQuestions);
    }

}
