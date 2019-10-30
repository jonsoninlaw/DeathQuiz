package fr.wcs.DeathQuiz.controller;

import fr.wcs.DeathQuiz.model.Questions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HackloweenController {

    private static Questions questions = new Questions();
    private static final String HACKLOWEEN_URL = "https://hackathon-wild-hackoween.herokuapp.com/";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/question1")
    @ResponseBody
    public String question1(Model out) {

        out.addAttribute(questions.getMovie1());

        return questions.getMovie1().getTitle() + " ------- " + questions.getMovie1().getQuestion() + " ------- " + questions.getMovie1().getAnswers();
    }

}
