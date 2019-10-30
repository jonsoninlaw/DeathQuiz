package fr.wcs.DeathQuiz.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ui.Model;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Questions {

    private static final String HACKLOWEEN_URL = "https://hackathon-wild-hackoween.herokuapp.com/";
    private Movie movie1;
    private Movies movies;

    public Questions() {
        this.movie1 = generateMovie(this.movie1);
        movies = generateAllMovies(movies);
        this.movie1.chooseQuestion();
        this.movie1.generateYearAnswers(movies.getYear());
        this.movie1.generateDirectorAnswers(movies.getDirector());
        this.movie1.generateCountryAnswers(movies.getCountry());
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

    private Movies generateAllMovies(Movies movies) {

        WebClient webClient = WebClient.create(HACKLOWEEN_URL);
        Mono<String> call = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movies")
                        .build())
                .retrieve()
                .bodyToMono(String.class);
        String response = call.block();

        ObjectMapper objectMapper = new ObjectMapper();

        movies = new Movies();


        try {
            JsonNode root = objectMapper.readTree(response);
            int count = 0;

            for (JsonNode movie : root.get("movies") ) {
                System.out.println("movie: " + movie.get("director").asText());
                movies.setDirector(count, movie.get("director").asText());
                count++;
            }
            count = 0;
            for (JsonNode movie : root.get("movies") ) {
                System.out.println("year: " + movie.get("year").asInt());
                movies.setYear(count, movie.get("year").asInt());
                count++;
            }
            count = 0;
            for (JsonNode movie : root.get("movies") ) {
                System.out.println("country: " + movie.get("country").asText());
                movies.setCountry(count, movie.get("country").asText());
                count++;
            }
            count = 0;
            for (JsonNode movie : root.get("movies") ) {
                System.out.println("title: " + movie.get("title").asText());
                movies.setTitle(count, movie.get("title").asText());
                count++;
            }
            count = 0;
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return movies;
    }
}

