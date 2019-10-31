package fr.wcs.DeathQuiz.controller;

import fr.wcs.DeathQuiz.model.Movie;
import fr.wcs.DeathQuiz.model.Player;
import fr.wcs.DeathQuiz.model.Questions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.PlainDocument;
import java.util.List;


@Controller
public class HackloweenController {

    private static Questions questions = new Questions();
    private static int currentQuestion = 0;
    Player player = new Player();

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
    public String nextQuestion(Model out) {
        if (currentQuestion == 9) {
            out.addAttribute("question", questions);
            return "redirect:/endGame";
        }
        String right = "none";
        String wrong = "none";
        currentQuestion ++;
        Movie movie = questions.getMoviesList()[currentQuestion];

        out.addAttribute("question", movie);
        out.addAttribute("right", right);
        out.addAttribute("wrong", wrong);
        return "question";
    }

    @GetMapping("/endGame")
    public String endGame(Model out, @RequestParam(name = "endgame", defaultValue = "99") List<Integer> answers) {
        out.addAttribute("question", questions);
        for (int elt : questions.getWin()) {
            System.out.println(elt);
        }
        if (answers.get(0) == 99) {

            return "endgame";
        }


        boolean win = false;

        if (answers.size() == 10) {
            for (int i = 0; i < 10; i++) {
                if (!questions.getWin().get(i).equals(answers.get(i))) {
                    win = false;
                }
                else {
                    win = true;
                }
            }
        }
        return win ? "win" : "loose";

    }
}
