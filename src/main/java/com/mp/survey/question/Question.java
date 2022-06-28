package com.mp.survey.question;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.List;

@MongoEntity(database = "survey", collection="questions")
public class Question extends PanacheMongoEntity {

    public String description;
    public boolean isPreScreening;
    public boolean isLast;
    public int order;
    public int nextQuestion;
    public String message;
    public List<Answer> answers;
}