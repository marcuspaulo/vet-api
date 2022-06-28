package com.mp.survey.useranswer;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mp.survey.question.QuestionService;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserAnswerService {

    @Inject
    MongoClient mongoClient;

    @Inject
    QuestionService questionService;

    public List<UserAnswer> list(){

        return UserAnswer.listAll();
    }

    public void add(UserAnswer userAnswer){
        Document document = new Document()
                .append("userId", userAnswer.userId)
                .append("questionId", userAnswer.questionId)
                .append("value", userAnswer.value);
        getCollection().insertOne(document);
    }

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("survey").getCollection("user-answers");
    }
}