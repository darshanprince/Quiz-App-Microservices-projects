package com.darshan.questionservice.service;

import com.darshan.questionservice.dao.QuestionDao;
import com.darshan.questionservice.model.Question;
import com.darshan.questionservice.model.QuestionWrapper;
import com.darshan.questionservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService
{
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions()
    {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> findByCategory(String category)
    {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question)
    {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Success",HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
         return new ResponseEntity<>("Failed",HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<String> deleteById(int id)
    {
        try {
            questionDao.deleteById(id);
            return new ResponseEntity<>("Question Deleted",HttpStatus.OK);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Delete Failed",HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<String> updateQuestion(Question question)
    {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Update Success",HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Update Success",HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<List<Integer>> createQuiz(String category, int numQ)
    {
        List<Integer> questions =questionDao.findRandomQuestionsByCategory(category,numQ);

        return new ResponseEntity<>(questions,HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(List<Integer> questionIds)
    {
        List<QuestionWrapper> wrapper = new ArrayList<>();
        List<Question> questions = new ArrayList<>();

        for(int id : questionIds)
        {
           questions.add(questionDao.findById(id).get());

        }
        for(Question question: questions)
        {
            QuestionWrapper wrappers= new QuestionWrapper();
            wrappers.setId(question.getId());
            wrappers.setQuestionTitle(question.getQuestionTitle());
            wrappers.setOption1(question.getOption1());
            wrappers.setOption2(question.getOption2());
            wrappers.setOption3(question.getOption3());
            wrappers.setOption4(question.getOption4());
            wrapper.add(wrappers);
        }
    return new ResponseEntity<>(wrapper,HttpStatus.OK);
    }

    public ResponseEntity<Integer> quizScore(List<Response> responses)
    {
        int right =0;

        for(Response response: responses)
        {
            Question questions = questionDao.findById(response.getId()).get();
            if (response.getResponses().equals(questions.getRightAnswer()))
                right++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
    }

