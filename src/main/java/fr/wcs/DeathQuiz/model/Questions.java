package fr.wcs.DeathQuiz.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Random;

public class Questions {

    private static final String HACKLOWEEN_URL = "https://hackathon-wild-hackoween.herokuapp.com/";
    private Movie movie1;

    public Questions() {
        this.movie1 = generateMovie(this.movie1);
        this.movie1.chooseQuestion();
        this.movie1.generateYearAnswers();
        this.movie1.generateDirectorAnswers();
        this.movie1.generateCountryAnswers();
        this.movie1.setRightAnswers();
    }

    public Movie getMovie1() {
        return movie1;
    }

    private Movie generateMovie(Movie movie) {

        Random random = new Random();
        int id = random.nextInt(82) + 1;

        WebClient webClient = WebClient.create(HACKLOWEEN_URL);
        Mono<String> call = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movies/{id}")
                        .build(id))
                .retrieve()
                .bodyToMono(String.class);
        String response = call.block();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode root = objectMapper.readTree(response);
            movie = objectMapper.convertValue(root.get("movie"), Movie.class);

        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return movie;
    }

}

