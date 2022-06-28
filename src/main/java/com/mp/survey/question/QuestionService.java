package com.mp.survey.question;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mp.survey.useranswer.UserAnswer;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class QuestionService {

    @Inject
    MongoClient mongoClient;

    @Inject
    QuestionService questionService;

    public List<Question> list(){

        return Question.listAll();
    }

    public void add(Question question){
        Document document = new Document()
                .append("description", question.description)
                .append("answers", question.answers)
                .append("isPreScreening", question.isPreScreening)
                .append("isLast", question.isLast)
                .append("nextQuestion", question.nextQuestion)
                .append("order", question.order);

        getCollection().insertOne(document);
    }

    private MongoCollection getCollection(){
        return mongoClient.getDatabase("survey").getCollection("questions");
    }

    public Question start(UserAnswer userAnswer) {
        if (userAnswer.questionId == null) {
            return Question.find("isPreScreening", true).firstResult();

        }

        Question question = new Question();

        question = Question.findById(userAnswer.questionId);



        Optional<Question> questionOptional = Question.findByIdOptional(userAnswer.questionId);
        question = questionOptional.orElseThrow(() -> new NotFoundException());

        if ("true".equals(userAnswer.value) && question.isPreScreening) {
            question.message = "You are a pre-screening participant. You will be asked to answer questions about " +
                    "your health and lifestyle.";
            return question;
        }

        if (question.isLast) {
            question.message = "You are a post-screening participant. You will be asked to answer questions about " +
                    "your health and lifestyle.";
            return question;
        }


        Question nextQuestion = new Question();

        Optional<Question> nextQuestionOptional = Question.findByIdOptional(question.nextQuestion);
        nextQuestion = nextQuestionOptional.orElseThrow(() -> new NotFoundException());

        return nextQuestion;

    }
}