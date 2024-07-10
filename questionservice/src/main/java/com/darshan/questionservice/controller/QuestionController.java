package com.darshan.questionservice.controller;

import com.darshan.questionservice.model.Question;
import com.darshan.questionservice.model.QuestionWrapper;
import com.darshan.questionservice.model.Response;
import com.darshan.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController
{
    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> findByCategory(@PathVariable String category)
    {
        return questionService.findByCategory(category);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable int id)
    {
        return questionService.deleteById(id);
    }

    @PutMapping("updateQuestion")
    public ResponseEntity<String> updateQuestion(@RequestBody Question question)
    {
        return questionService.updateQuestion(question);
    }

    @GetMapping("createQuiz")
    public ResponseEntity<List<Integer>> createQuiz(@RequestParam String category,@RequestParam int numQ)
    {
        return questionService.createQuiz(category,numQ);
    }
    @PostMapping("getQuizQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@RequestBody List<Integer> questionIds)
    {
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuizQuestions(questionIds);
    }

    @PostMapping("score")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionService.quizScore(responses);
    }
}
