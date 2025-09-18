package com.roshan.quizeapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.repository.cdi.Eager;

@Data
@AllArgsConstructor
public class QuestionWrapper {

    private int id;

    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;


}
