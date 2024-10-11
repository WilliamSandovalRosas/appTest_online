package com.wsr.appTest_online.model;



import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.*;


import java.util.List;




@Getter
@Setter
@Entity
public class TestQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String question;
    @NotBlank
    private String subject;
    @NotBlank
    private String questionType;
    @NotBlank
    @ElementCollection
    private List<String> choices;
    @NotBlank
    @ElementCollection
    private List<String> correctAnswer;



}
