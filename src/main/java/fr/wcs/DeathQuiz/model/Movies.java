package fr.wcs.DeathQuiz.model;

public class Movies {
    private String[] director = new String[82];
    private String[] title = new String[82];
    private String[] country = new String[82];
    private int[] year = new int[82];

    public Movies() {}

    public String[] getDirector() {
        return director;
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
        this.director[index] = director;
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
