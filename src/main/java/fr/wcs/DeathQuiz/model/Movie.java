package fr.wcs.DeathQuiz.model;

import java.util.Random;

public class Movie {
    private int id;
    private String title;
    private String director;
    private int year;
    private String country;
    private String posterUrl;
    private String createdAt;
    private String updatedAt;

    private String question;
    private String[] questions = {"What's this movie's release date ?", "Who directed this movie ?", "Do you know where this movie comes from ?"};
    private int[] yearAnswers = new int[4];
    private String[] directorAnswers = new String[4];
    private String[] countryAnswers = new String[4];
    private Object[] answers = {yearAnswers, directorAnswers, countryAnswers};

    public Movie() {
    }

    private void chooseQuestion() {
        Random random = new Random();
        this.question = questions[random.nextInt(3)];
    }

    private void generateAnswers() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
