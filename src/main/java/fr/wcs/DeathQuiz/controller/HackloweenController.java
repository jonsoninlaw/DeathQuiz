package fr.wcs.DeathQuiz.controller;

import fr.wcs.DeathQuiz.model.Movie;
import fr.wcs.DeathQuiz.model.Questions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HackloweenController {

    private static Questions questions = new Questions();
    private static int currentQuestion = 0;
    private static final String HACKLOWEEN_URL = "https://hackathon-wild-hackoween.herokuapp.com/";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/question")
    public String question(Model out, @RequestParam(name = "answer", required = false, defaultValue = "") String answer) {

        String right = "none";
        String wrong = "none";
        Object rightAnswer;
        Movie movie = questions.getMoviesList()[currentQuestion];
        if (currentQuestion == 0) {
            rightAnswer = questions.getMoviesList()[0].getRightAnswers() + "";
        }
        else {
            rightAnswer = questions.getMoviesList()[currentQuestion].getRightAnswers() + "";
        }
        if (answer.length() != 0) {
            if (rightAnswer.equals(answer)) {
                right = "block";
            }
            else {
                wrong = "block";
            }
        }
        System.out.println(rightAnswer);
        System.out.println(answer);


        out.addAttribute("question", movie);
        out.addAttribute("right", right);
        out.addAttribute("wrong", wrong);
        return "question";

    }

    @GetMapping("/nextQuestion")
    public String nextQuestion(Model test) {

        String right = "none";
        String wrong = "none";
        currentQuestion ++;
        Movie movie = questions.getMoviesList()[currentQuestion];

        test.addAttribute("question", movie);
        test.addAttribute("right", right);
        test.addAttribute("wrong", wrong);
        return "question";

    }
}
