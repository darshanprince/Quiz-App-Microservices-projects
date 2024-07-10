package com.darshan.questionservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionWrapper
{
        int id;
        String questionTitle;
        String option1;
        String option2;
        String option3;
        String option4;
}
