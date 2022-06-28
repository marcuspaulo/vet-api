package com.mp.survey.useranswer;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/user/answer")
public class UserAnswerResource {

    @Inject
    UserAnswerService userAnswerService;

    @GET
    public List<UserAnswer> list() {
        return userAnswerService.list();
    }

    @POST
    public List<UserAnswer> add(UserAnswer userAnswer) {
        userAnswerService.add(userAnswer);
        return list();
    }
}