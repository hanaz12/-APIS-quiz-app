package com.global.hr.service;

import com.global.hr.Repository.QuestionRepository;
import com.global.hr.Repository.QuizRepository;
import com.global.hr.model.Question;
import com.global.hr.model.QuestionWrapper;
import com.global.hr.model.Quiz;
import com.global.hr.model.Response;
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
    private QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }
    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions=questionRepository.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Quiz created", HttpStatus.CREATED);



    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        try {
            Optional<Quiz> quiz = quizRepository.findById(id);
            List<Question> questionsFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionWrappersForUsers = new ArrayList<>();
            for (Question q : questionsFromDB) {
                QuestionWrapper temp = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                questionWrappersForUsers.add(temp);
            }
            return new ResponseEntity<>(questionWrappersForUsers,HttpStatus.OK);
        } catch (Exception e) {
           e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
   Quiz quiz = quizRepository.findById(id).get();
   List<Question> questions=quiz.getQuestions();
   int trueAnswers=0 ,i=0;
   for (Response r : responses) {
       if (r.getResponse().equals(questions.get(i).getRightAnswer())) {
           trueAnswers++;
       }
       i++;
   }
   return new ResponseEntity<>(trueAnswers, HttpStatus.OK);
    }
}
