package com.roshan.quizeapp.controller;


import com.roshan.quizeapp.model.QuestionWrapper;
import com.roshan.quizeapp.model.Questions;
import com.roshan.quizeapp.model.Responses;
import com.roshan.quizeapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<QuestionWrapper>> getQuizeQuestions(@PathVariable Integer id){
        return quizService.getQuizeQuestions(id);
    }

    @PostMapping("/submit/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Integer> submitResponse(@PathVariable int id, @RequestBody List<Responses> rs){
        return quizService.calculate(id,rs);
    }
}
