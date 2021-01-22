package models;

import java.util.List;

public class Question {
    private List<String> choices;
    private int correctChoiceIndex;
    private int grade, rank;

    public Question(List<String> choices, int correctChoiceIndex, int grade, int rank) {
        if(choices == null)
            throw new NullPointerException("Choices for the quesiton CANNOT be null");
        
        this.choices = choices;

        this.correctChoiceIndex = correctChoiceIndex;

        this.grade = grade;
        this.rank = rank;
    }


    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public String getCorrectChoice() {
        return choices.get(correctChoiceIndex);
    }

    public void setCorrectChoiceIndex(int correctChoiceIndex) {
        this.correctChoiceIndex = correctChoiceIndex;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
