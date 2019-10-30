package fr.wcs.DeathQuiz.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.wcs.DeathQuiz.model.Movies;
import fr.wcs.DeathQuiz.model.Questions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/question2")
    public String otherMovies(Model out) {

        WebClient webClient = WebClient.create(HACKLOWEEN_URL);
        Mono<String> call = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movies")
                        .build())
                .retrieve()
                .bodyToMono(String.class);
        String response = call.block();

        ObjectMapper objectMapper = new ObjectMapper();

        Movies movies = new Movies();


        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode information = root.get("movies");

            for (int i = 0; i < 82; i++) {
                movies.setDirector(i, information.get("director").asText());
                String[] nameDirector = movies.getDirector();
            }

            for (int i = 0; i < 82; i++) {
                movies.setDirector(i, information.get("year").asText());
                int[] yearMovie = movies.getYear();
            }

            for (int i = 0; i < 82; i++) {
                movies.setDirector(i, information.get("country").asText());
                String[] countryMovie = movies.getCountry();
            }

            for (int i = 0; i < 82; i++) {
                movies.setDirector(i, information.get("title").asText());
                String[] titleMovie = movies.getTitle();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        out.addAttribute("movies", movies.getCountry());
        out.addAttribute("movies", movies.getDirector());
        out.addAttribute("movies", movies.getYear());
        out.addAttribute("movies", movies.getTitle());

        return "question";
    }
}
