package models;

import utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Question {
    private final Exam exam;
    private String text;
    private List<String> choices;
    private int correctChoiceIndex;
    private int grade, rank;

    public Question(Exam exam, String text, List<String> choices, int correctChoiceIndex, int grade) {
        this.exam = exam;

        this.text = text;
        
        if(choices == null)
            throw new NullPointerException("Choices for the question CANNOT be null");
        
        this.choices = choices;

        this.correctChoiceIndex = correctChoiceIndex;

        this.grade = grade;
    }


    public String getText(){
        return text;
    }
    
    public void setText(String text){
        this.text = text;
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

    public Exam getExam(){
        return this.exam;
    }

    public void push(){
        Question.push(this);
    }

    public static void push(Question question){
        try{
            Connection con = DriverManager.getConnection(ConnectionUtil.dbURL, ConnectionUtil.username,
                    ConnectionUtil.password);

            //pushing question and it's info
            String questionQuery =  String.format("INSERT INTO QUESTIONS(question_text, correct_choice, grade, rank, exam_id) " +
                            "VALUES('%s', '%s', '%s', '%s', '%s')",
                    question.getText(), question.getChoices().get(question.correctChoiceIndex), question.getGrade(),
                        question.getRank(), question.getExam().getId());

            System.out.println("text: " + question.getText());
            System.out.println("correct choice: " + question.getChoices().get(question.correctChoiceIndex));
            System.out.println("grade: " + question.getGrade());
            System.out.println("rank: " + question.getRank());
            System.out.println("Exam id: "+ question.getExam().getId());

            con.createStatement().execute(questionQuery);

            System.out.println("Pushing question info succeeded!");

            //pushing questions answers (choices)
            for (int i = 0; i < question.getChoices().size(); i++) {
                String choicesQuery =  String.format("INSERT INTO QUESTION_ANSWERS(question_text, answer) " +
                                "VALUES('%s', '%s')",
                        question.getText(), question.getChoices().get(i));

                con.createStatement().execute(choicesQuery);
            }

            System.out.println("Pushing question answers succeeded!");
            System.out.println("question pushed");
            System.out.println("=======================================================================");

            con.close();
        }
        catch(SQLException ex){
            System.out.println("Error pushing exam");
            System.out.println("=======================================================================");

            /*var lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);*/
        }
    }
}
