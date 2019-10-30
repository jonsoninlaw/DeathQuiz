package fr.wcs.DeathQuiz.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private static final String HACKLOWEEN_URL = "https://hackathon-wild-hackoween.herokuapp.com/";

    /*@GetMapping("/")
    public String index() {
        return "index";
    }*/

    @GetMapping("/question")
    @ResponseBody
    public String question() {

        WebClient webClient = WebClient.create(HACKLOWEEN_URL);
        Mono<String> call = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movies/{id}")
                        // TODO : Créer une valeur id aléatoire
                        .build(1))
                .retrieve()
                .bodyToMono(String.class);
        String response = call.block();

        ObjectMapper objectMapper = new ObjectMapper();
        //Planet planetObject = null;
        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode movies= root.get("movie");
            String movieId = movies.get("id").asText();
            String movieTitle = movies.get("title").asText();
            System.out.println(movieId + " : " + movieTitle);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Hello";
    }

}
