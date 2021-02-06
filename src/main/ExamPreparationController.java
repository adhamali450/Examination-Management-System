package main;

import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import models.Course;
import models.EndUser;
import models.Exam;
import models.Instructor;
import models.Question;

public class ExamPreparationController implements Initializable {

    //region controls

    //HEADER
    @FXML
    private Label lblCourseName;
    @FXML
    private Label lblInstructorName;

    //test timing and duration
    @FXML
    private TextField txtStartTimeHour;
    @FXML
    private TextField txtStartTimeMinute;
    @FXML
    private TextField txtDuration;

    //test submission date
    @FXML
    private RadioButton rdAfterSubmission;
    @FXML
    private ToggleGroup release_date;
    @FXML
    private RadioButton rdCustomDate;
    @FXML
    private DatePicker dtPicker;

    //QUESTIONS
    //Q-1
    @FXML
    private TextField txtQ1;
    @FXML
    private RadioButton rdQ1A1;
    @FXML
    private TextField txtQ1A1;
    @FXML
    private RadioButton rdQ1A2;
    @FXML
    private TextField txtQ1A2;
    @FXML
    private RadioButton rdQ1A3;
    @FXML
    private TextField txtQ1A3;
    @FXML
    private RadioButton rdQ1A4;
    @FXML
    private TextField txtQ1A4;
    @FXML
    private TextField txtG1;

    //Q-2
    @FXML
    private TextField txtQ2;
    @FXML
    private RadioButton rdQ2A1;
    @FXML
    private TextField txtQ2A1;
    @FXML
    private RadioButton rdQ2A2;
    @FXML
    private TextField txtQ2A2;
    @FXML
    private RadioButton rdQ2A3;
    @FXML
    private TextField txtQ2A3;
    @FXML
    private RadioButton rdQ2A4;
    @FXML
    private TextField txtQ2A4;
    @FXML
    private TextField txtG2;

    //Q-3
    @FXML
    private TextField txtQ3;
    @FXML
    private RadioButton rdQ3A1;
    @FXML
    private TextField txtQ3A1;
    @FXML
    private RadioButton rdQ3A2;
    @FXML
    private TextField txtQ3A2;
    @FXML
    private RadioButton rdQ3A3;
    @FXML
    private TextField txtQ3A3;
    @FXML
    private RadioButton rdQ3A4;
    @FXML
    private TextField txtQ3A4;
    @FXML
    private TextField txtG3;

    //Q-4
    @FXML
    private TextField txtQ4;
    @FXML
    private RadioButton rdQ4A1;
    @FXML
    private TextField txtQ4A1;
    @FXML
    private RadioButton rdQ4A2;
    @FXML
    private TextField txtQ4A2;
    @FXML
    private RadioButton rdQ4A3;
    @FXML
    private TextField txtQ4A3;
    @FXML
    private RadioButton rdQ4A4;
    @FXML
    private TextField txtQ4A4;
    @FXML
    private TextField txtG4;

    //Q-5
    @FXML
    private TextField txtQ5;
    @FXML
    private RadioButton rdQ5A1;
    @FXML
    private TextField txtQ5A1;
    @FXML
    private RadioButton rdQ5A2;
    @FXML
    private TextField txtQ5A2;
    @FXML
    private RadioButton rdQ5A3;
    @FXML
    private TextField txtQ5A3;
    @FXML
    private RadioButton rdQ5A4;
    @FXML
    private TextField txtQ5A4;
    @FXML
    private TextField txtG5;

    //Q-6
    @FXML
    private TextField txtQ6;
    @FXML
    private RadioButton rdQ6A1;
    @FXML
    private TextField txtQ6A1;
    @FXML
    private RadioButton rdQ6A2;
    @FXML
    private TextField txtQ6A2;
    @FXML
    private RadioButton rdQ6A3;
    @FXML
    private TextField txtQ6A3;
    @FXML
    private RadioButton rdQ6A4;
    @FXML
    private TextField txtQ6A4;
    @FXML
    private TextField txtG6;

    //Q-7
    @FXML
    private TextField txtQ7;
    @FXML
    private RadioButton rdQ7A1;
    @FXML
    private TextField txtQ7A1;
    @FXML
    private RadioButton rdQ7A2;
    @FXML
    private TextField txtQ7A2;
    @FXML
    private RadioButton rdQ7A3;
    @FXML
    private TextField txtQ7A3;
    @FXML
    private RadioButton rdQ7A4;
    @FXML
    private TextField txtQ7A4;
    @FXML
    private TextField txtG7;

    //SUBMITTING
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSubmit;

    //endregion

    ArrayList<String> questionsTexts, grades;
    ArrayList<ArrayList<String>> choices;
    ArrayList<Integer> correctChoiceIndexs;

    NavSystem navSystem;
    Instructor instructor;
    Course course;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblInstructorName.setText(instructor.getName());
        course.getName();

        rdCustomDate.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    dtPicker.setDisable(false);
                    dtPicker.setFocusTraversable(true);
                } else {
                    dtPicker.setDisable(true);
                }
            }
        });
    }

    public ExamPreparationController(){
        navSystem = new NavSystem();
        instructor = (Instructor)EndUser.loggingUser;
        course = instructor.getCourseOffered();
    }

    public void handleButtonAction(MouseEvent event) {
        if(event.getSource() == btnSubmit){
            submitExam();
        }

        navSystem.switchScene(event, NavSystem.INSTRUCTOR_DASHBOARD);
    }

    private void submitExam(){
        System.out.println("submitting an exam...");

        float startTime =  Float.parseFloat((txtStartTimeHour.getText())) + (Float.parseFloat(txtStartTimeMinute.getText()) / 60.0f);
        int duration = Integer.parseInt(txtDuration.getText());

        //getting release date
        LocalDate releaseDate = rdAfterSubmission.isSelected() ? LocalDateTime.now().toLocalDate() :
                dtPicker.getValue();

        Exam exam = new Exam(UUID.randomUUID(),
                course.getCode(),
                instructor,
                startTime,
                duration,
                releaseDate);

        exam.push();

        PushQuestions(exam);
    }

    private void PushQuestions(Exam exam){

        //region fetching data from input fields

        questionsTexts = new ArrayList<>(Arrays.asList(txtQ1.getText(), txtQ2.getText(), txtQ3.getText(),
                txtQ4.getText(), txtQ5.getText(), txtG6.getText(), txtG7.getText()));

        grades = new ArrayList<>(Arrays.asList(txtG1.getText(), txtG2.getText(), txtG3.getText(),
                txtG4.getText(), txtG5.getText(), txtG6.getText(), txtG7.getText()));

        choices = new ArrayList<>();
        choices.add(new ArrayList<>(Arrays.asList(txtQ1A1.getText(), txtQ1A2.getText(), txtQ1A3.getText(), txtQ1A4.getText())));
        choices.add(new ArrayList<>(Arrays.asList(txtQ2A1.getText(), txtQ2A2.getText(), txtQ2A3.getText(), txtQ2A4.getText())));
        choices.add(new ArrayList<>(Arrays.asList(txtQ3A1.getText(), txtQ3A2.getText(), txtQ3A3.getText(), txtQ3A4.getText())));
        choices.add(new ArrayList<>(Arrays.asList(txtQ4A1.getText(), txtQ4A2.getText(), txtQ4A3.getText(), txtQ4A4.getText())));
        choices.add(new ArrayList<>(Arrays.asList(txtQ5A1.getText(), txtQ5A2.getText(), txtQ5A3.getText(), txtQ5A4.getText())));
        choices.add(new ArrayList<>(Arrays.asList(txtQ6A1.getText(), txtQ6A2.getText(), txtQ6A3.getText(), txtQ6A4.getText())));
        choices.add(new ArrayList<>(Arrays.asList(txtQ7A1.getText(), txtQ7A2.getText(), txtQ7A3.getText(), txtQ7A4.getText())));

        ArrayList<ArrayList<Integer> > aList =
                new ArrayList<ArrayList<Integer> >(7);


        correctChoiceIndexs = new ArrayList<>(Arrays.asList(
            getEnabledRadioButtonIndex(rdQ1A1, rdQ1A2, rdQ1A3, rdQ1A4),
            getEnabledRadioButtonIndex(rdQ2A1, rdQ2A2, rdQ2A3, rdQ2A4),
            getEnabledRadioButtonIndex(rdQ3A1, rdQ3A2, rdQ3A3, rdQ3A4),
            getEnabledRadioButtonIndex(rdQ4A1, rdQ4A2, rdQ4A3, rdQ4A4),
            getEnabledRadioButtonIndex(rdQ5A1, rdQ5A2, rdQ5A3, rdQ5A4),
            getEnabledRadioButtonIndex(rdQ6A1, rdQ6A2, rdQ6A3, rdQ6A4),
            getEnabledRadioButtonIndex(rdQ7A1, rdQ7A2, rdQ7A3, rdQ7A4)
        ));
        //endregion


        //region ensuring questions uniqueness (in terms of text)
        String questionsCombined = "";
        for (int i = 0; i < 7; i++) {
            String text = questionsTexts.get(i);
            if(questionsCombined.contains(text)){
                //prompt an error
            }
            else{
                questionsCombined += text;
            }
        }
        //endregion

        for (int i = 0; i < 7; i++) {

            Question question = new Question(exam, questionsTexts.get(i), choices.get(i),
                    correctChoiceIndexs.get(i), Integer.parseInt(grades.get(i)));

            question.push();
        }
    }

    private int getEnabledRadioButtonIndex(RadioButton... buttons){
        int index = 0;
        for (RadioButton button : buttons) {
            if(button.isSelected())
                return index;
            index++;
        }
        return -1;
    }
}
