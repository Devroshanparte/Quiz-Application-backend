package com.roshan.quizeapp.service;


import com.roshan.quizeapp.model.QuestionWrapper;
import com.roshan.quizeapp.model.Questions;
import com.roshan.quizeapp.model.Quiz;
import com.roshan.quizeapp.model.Responses;
import com.roshan.quizeapp.repo.QuestionRepo;
import com.roshan.quizeapp.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;
    @Autowired
    QuestionRepo qRepo;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Questions> question=qRepo.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(question);
        quizRepo.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.OK);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizeQuestions(Integer id) {
         Optional<Quiz> quiz=quizRepo.findById(id);
         List<Questions> questionsFromDb=quiz.get().getQuestions();
         List<QuestionWrapper> questionForUser=new ArrayList<>();

         for (Questions q: questionsFromDb){
             QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
             questionForUser.add(qw);
         }


         return new ResponseEntity<>(questionForUser,HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculate(int id, List<Responses> responses) {
        Quiz quiz = quizRepo.findById(id).get();
        List<Questions> questions=quiz.getQuestions();
        int right=0;
        int i=0;
        for(Responses rs:responses) {
            if (rs.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }

}
