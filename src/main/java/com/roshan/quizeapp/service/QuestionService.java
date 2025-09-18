package com.roshan.quizeapp.service;


import com.roshan.quizeapp.model.Questions;
import com.roshan.quizeapp.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo qRepo;

    public ResponseEntity<List<Questions>> getAllQuestion() {
        try {
            return new ResponseEntity<>(qRepo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }


    public void addQuestion(Questions questions) {
        qRepo.save(questions);
    }

    public ResponseEntity<List<Questions>> getBasedonCategory(String category) {
        return new ResponseEntity<>(qRepo.findByCategory(category),HttpStatus.OK);
    }


    public Questions updateQuestion(int id,Questions questions) {


            Questions exsistingQeus=qRepo.findById(id).orElseThrow(()-> new RuntimeException("Question not found"));
            exsistingQeus.setQuestionTitle(questions.getQuestionTitle());
            exsistingQeus.setCategory(questions.getCategory());
            exsistingQeus.setOption1(questions.getOption1());
            exsistingQeus.setOption2(questions.getOption2());
            exsistingQeus.setOption3(questions.getOption3());
            exsistingQeus.setOption4(questions.getOption4());
            exsistingQeus.setRightAnswer(questions.getRightAnswer());
            exsistingQeus.setDifficultyLevel(questions.getDifficultyLevel());

            return qRepo.save(exsistingQeus);
    }


    public void deleteQuestion(int id){
        qRepo.deleteById(id);
    }
}
