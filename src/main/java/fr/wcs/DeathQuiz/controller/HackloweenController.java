package fr.wcs.DeathQuiz.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.wcs.DeathQuiz.model.Questions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Controller
public class HackloweenController {

    private static Questions questions = new Questions();

    private static final String HACKLOWEEN_URL = "https://hackathon-wild-hackoween.herokuapp.com/";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/question")
    public String question(Model out, @RequestParam(name = "answer", required = false, defaultValue = "") String answer) {

        String right = "none";
        String wrong = "none";
        Object rightAnswer = questions.getMovie1().getRightAnswers() + "";
        System.out.println(rightAnswer);
        System.out.println(answer);
        if (answer.length() != 0) {
            if (rightAnswer.equals(answer)) {
                right = "block";
            }
            else {
                wrong = "block";
            }
        }

        out.addAttribute("question", questions.getMovie1());
        out.addAttribute("right", right);
        out.addAttribute("wrong", wrong);
        return "question";
    }

}
