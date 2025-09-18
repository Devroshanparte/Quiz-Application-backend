package com.roshan.quizeapp.controller;


import com.roshan.quizeapp.model.Questions;
import com.roshan.quizeapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionsController {

    @Autowired
    QuestionService qService;

    //get all elements
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Questions>> getAllQuestion(){
        return qService.getAllQuestion();
    }

    //add element
    @PostMapping("/addQuestion")
    public void addQuestion(@RequestBody Questions questions){
        qService.addQuestion(questions);
    }

    //get based on category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Questions>> getBasedonCategory(@PathVariable String category){
        return qService.getBasedonCategory(category);
    }

    //update question
    @PutMapping("/{id}")
    public Questions updateQuestion(@PathVariable int id,@RequestBody Questions questions){
        return qService.updateQuestion(id,questions);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable int id){
        qService.deleteQuestion(id);
    }






}
