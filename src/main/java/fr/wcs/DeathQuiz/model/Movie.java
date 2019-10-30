package fr.wcs.DeathQuiz.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    private int questionIndex;
    private String[] questions = {"What's this movie's release date ?", "Who directed this movie ?", "Do you know where this movie comes from ?"};
    private List<Integer> yearAnswers = new ArrayList<>();
    private String[] directorAnswers = new String[4];
    private String[] countryAnswers = new String[4];
    //private String[] funAnswers = {""};
    private int[] wrongYearAnswers = {1999, 55, 2222};
    private String[] wrongDirectorAnswers = {"Steve Williams", "Roger Rabbit", "Jackie et Michel"};
    private String[] wrongCountryAnswers = {"Transilvania", "Disneyland", "Laponie"};
    private Object[] answers = {yearAnswers, directorAnswers, countryAnswers};
    private Object[] wrongAnswers = {wrongYearAnswers, wrongDirectorAnswers, wrongCountryAnswers};

    public Movie() {
    }

    public String getQuestion() {
        return question;
    }

    public Object getAnswers() {
        return answers[questionIndex];
    }

    public void chooseQuestion() {
        Random random = new Random();
        //questionIndex = random.nextInt(3);
        this.questionIndex = 0;
        this.question = questions[questionIndex];
    }

    public void generateYearAnswers() {
        Random random = new Random();
        this.yearAnswers.add(this.year);
        System.out.println("year : " + this.year);
        System.out.println("id : " + this.id);
        for (int i = 0; i < 3; i++) {
            this.yearAnswers.add(this.wrongYearAnswers[i]);
        }
        Collections.shuffle(yearAnswers);
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
