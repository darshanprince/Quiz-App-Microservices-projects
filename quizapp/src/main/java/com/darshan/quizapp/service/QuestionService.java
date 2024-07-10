package com.darshan.quizapp.service;

import com.darshan.quizapp.dao.QuestionDao;
import com.darshan.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
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
        catch(Exception e){
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
}
