package fr.wcs.DeathQuiz.model;

import java.util.ArrayList;
import java.util.List;

public class EndGame {

    private String answer;
    private int id;

    public EndGame(String answer, int id) {
        this.answer = answer;
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
