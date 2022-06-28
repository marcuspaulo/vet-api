package com.mp.survey.useranswer;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(database = "survey", collection="user-answers")
public class UserAnswer extends PanacheMongoEntity {
    public String userId;
    public String questionId;
    public String value;
}
