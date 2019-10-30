package fr.wcs.DeathQuiz.model;

public class Movies {
    private String[] directors;
    private String[] title;
    private String[] country;
    private int[] year;
    private Movies movies;

    public Movies() {}
    public String[] getDirector() {
        return directors;
    }

    public String[] getTitle() {
        return title;
    }

    public String[] getCountry() {
        return country;
    }

    public int[] getYear() {
        return year;
    }

    public void setDirector(int index, String director) {
        this.directors[index] = director;
    }

    public void setTitle(int index, String title) {
        this.title[index] = title;
    }

    public void setCountry(int index, String country) {
        this.country[index] = country;
    }

    public void setYear(int index, int year) {
        this.year[index] = year;
    }
}
