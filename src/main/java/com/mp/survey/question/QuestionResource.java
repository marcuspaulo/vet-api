package com.mp.survey.question;

import com.mp.survey.useranswer.UserAnswer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/questions")
public class QuestionResource {

    @Inject
    QuestionService questionService;

    @GET
    public List<Question> list() {
        return questionService.list();
    }

    @POST
    public List<Question> add(Question question) {
        questionService.add(question);
        return list();
    }

    @POST
    @Path("/start")
    public Question start(UserAnswer userAnswer) {
        return questionService.start(userAnswer);
    }
}