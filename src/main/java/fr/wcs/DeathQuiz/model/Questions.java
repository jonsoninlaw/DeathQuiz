package fr.wcs.DeathQuiz.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.ui.Model;
import org.springframework.web.reactive.function.client.WebClient;
import org.w3c.dom.ls.LSOutput;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Questions {

    private static final String HACKLOWEEN_URL = "https://hackathon-wild-hackoween.herokuapp.com/";
    private Movie movie1 = generateMovie(this.movie1);
    private Movie movie2 = generateMovie(this.movie2);
    private Movie movie3 = generateMovie(this.movie3);
    private Movie movie4 = generateMovie(this.movie4);
    private Movie movie5 = generateMovie(this.movie5);
    private Movie movie6 = generateMovie(this.movie6);
    private Movie movie7 = generateMovie(this.movie7);
    private Movie movie8 = generateMovie(this.movie8);
    private Movie movie9 = generateMovie(this.movie9);
    private Movie movie10 = generateMovie(this.movie10);
    private Movies movies;
    private Movie[] moviesList = {movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8, movie9, movie10};
    private List<EndGame> endGame = new ArrayList<>();
    private List<Integer> win = new ArrayList<>();

    public Questions() {

        movies = generateAllMovies(movies);
        for (Movie movie : moviesList) {
            movie.chooseQuestion();
            movie.generateYearAnswers(movies.getYear());
            movie.generateDirectorAnswers(movies.getDirector());
            movie.generateCountryAnswers(movies.getCountry());
            movie.setRightAnswers();

        }
        for (int i = 0; i < 40; i++) {
            for (Movie movie : moviesList) {
                endGame.add(new EndGame(movie.getAnswers().get(0), i));
                i++;
                endGame.add(new EndGame(movie.getAnswers().get(1), i));
                i++;
                endGame.add(new EndGame(movie.getAnswers().get(2), i));
                i++;
                endGame.add(new EndGame(movie.getAnswers().get(3), i));
                i++;
            }
        }
        int id = 0;
        for (Movie movie : moviesList) {
            for (int j = 0; j < 4; j++) {
                if (endGame.get(id).getAnswer().equals(movie.getRightAnswers())) {
                    win.add(id);
                }
                id++;
            }
        }
    }

    public List<Integer> getWin() {
        return win;
    }

    public List<EndGame> getEndGame() {
        return endGame;
    }

    public Movie[] getMoviesList() {
        return moviesList;
    }

    public Movie getMovie1() {
        return movie1;
    }
    public Movie getMovie2() {
        return movie2;
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
                movies.setDirector(count, movie.get("director").asText().replace("_", " "));
                count++;
            }
            count = 0;
            for (JsonNode movie : root.get("movies") ) {
                movies.setYear(count, movie.get("year").asInt());
                count++;
            }
            count = 0;
            for (JsonNode movie : root.get("movies") ) {
                movies.setCountry(count, movie.get("country").asText().replace("_", " "));
                count++;
            }
            count = 0;
            for (JsonNode movie : root.get("movies") ) {
                movies.setTitle(count, movie.get("title").asText().replace("_", " "));
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

