package fr.wcs.DeathQuiz.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.wcs.DeathQuiz.model.Questions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Controller
public class HackloweenController {

    //private static Questions questions = new Questions();

    private static final String HACKLOWEEN_URL = "https://hackathon-wild-hackoween.herokuapp.com/";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/question1")
    public String question1(Model out) {
        Questions questions = new Questions();
        out.addAttribute("question", questions.getMovie1());
        return "question";
        //return questions.getMovie1().getTitle() + " ------- " + questions.getMovie1().getQuestion() + " ------- " + questions.getMovie1().getAnswers() + " --- The right answer is : " + questions.getMovie1().getRightAnswers();

    }

}
