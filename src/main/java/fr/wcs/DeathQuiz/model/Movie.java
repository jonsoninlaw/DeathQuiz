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
    private List<String> yearAnswers = new ArrayList<>();
    private List<String> directorAnswers = new ArrayList<>();
    private List<String> countryAnswers = new ArrayList<>();
    private String[] fakeYearAnswers = {"It was never released", "2023", "Some day", "Who knows", "19999", "23401375", "1999 before J.C", "In the year 2525"};
    private String[] fakeDirectorAnswers = {"John Smith", "John Doe", "Jonathan Gendre", "Jacky and Michel", "Michael Jackson", "You", "Some talented person", "Not you", "Someone"};
    private String[] fakeCountryAnswers = {"Neverland", "Nevermindland", "Lala Land", "Mushroom Kingdom", "Hyrule", "Babar's Kingdom", "Groland", "Disneyland"};
    private Object[] rightAnswers = new Object[3];
    private Object[] answers = {yearAnswers, directorAnswers, countryAnswers};

    public Movie() {
    }

    public String getQuestion() {
        return question;
    }

    public Object getAnswers() {
        for (int i = 0; i < 3; i++) {
        }
        return answers[questionIndex];
    }

    public void setRightAnswers() {
        this.rightAnswers[0] = this.year;
        this.rightAnswers[1] = this.director.replace("_", " ");
        this.rightAnswers[2] = this.country.replace("_", " ");
    }

    public void chooseQuestion() {
        Random random = new Random();
        this.questionIndex = random.nextInt(3);
        this.question = questions[questionIndex];
    }

    public void generateYearAnswers(int[] allYears) {
        Random random = new Random();
        this.yearAnswers.add(this.year + "");
        this.yearAnswers.add(this.fakeYearAnswers[random.nextInt(fakeYearAnswers.length)]);
        for (int i = 0; i < 2; i++) {
            int randYear = allYears[random.nextInt(82)];
            while (yearAnswers.contains(randYear)) {
                randYear = allYears[random.nextInt(82)];
            }
            this.yearAnswers.add(randYear + "");
        }
        Collections.shuffle(yearAnswers);
    }

    public void generateDirectorAnswers(String[] allDirectors) {
        Random random = new Random();
        this.directorAnswers.add(this.director.replace("_", " "));
        this.directorAnswers.add(this.fakeDirectorAnswers[random.nextInt(fakeDirectorAnswers.length)]);
        for (int i = 0; i < 2; i++) {
            String randDirector = allDirectors[random.nextInt(82)].replace("_", " ");
            while (directorAnswers.contains(randDirector)) {
                randDirector = allDirectors[random.nextInt(82)].replace("_", " ");
            }
            this.directorAnswers.add(randDirector);
        }
        Collections.shuffle(directorAnswers);
    }

    public void generateCountryAnswers(String[] allCountries) {
        Random random = new Random();
        this.countryAnswers.add(this.country.replace("_", " "));
        this.countryAnswers.add(this.fakeCountryAnswers[random.nextInt(fakeCountryAnswers.length)]);
        for (int i = 0; i < 2; i++) {
            String randCountry = allCountries[random.nextInt(82)].replace("_", " ");
            while (countryAnswers.contains(randCountry)) {
                randCountry = allCountries[random.nextInt(82)].replace("_", " ");
            }
            this.countryAnswers.add(randCountry);
        }
        Collections.shuffle(countryAnswers);
    }

    public Object getRightAnswers() {
        return rightAnswers[questionIndex];
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
