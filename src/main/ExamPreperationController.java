/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import models.Course;
import models.EndUser;
import models.Exam;
import models.Instructor;
import models.Question;

/**
 * FXML Controller class
 *
 * @author adham
 */
public class ExamPreperationController implements Initializable {

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
    private TextField txtCustomDate;
    
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
    
    NavSystem navSystem;
    Instructor instructor;
    Course course = instructor.getCourseOffered();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        navSystem = new NavSystem();
        
        instructor = (Instructor)EndUser.loggingUser;
        
        lblInstructorName.setText(instructor.getName());
        course.getName();
        
        
        
        rdCustomDate.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) { 
                    txtCustomDate.setDisable(false);
                    txtCustomDate.setFocusTraversable(true);
                } else {
                    txtCustomDate.setDisable(true);
                }
            }
        });

    }

    public void handleButtonAction(MouseEvent event) {
        if(event.getSource() == btnSubmit){
            submitExam();
        }
        if(event.getSource() == btnCancel){
            navSystem.SwitchScene(event, "InstructorDashboard.FXML");
        }
    }
    
    private void submitExam(){
        System.out.println("submit exam");
        
        
        int startTime =  Integer.parseInt(txtStartTimeHour.getText()) + (60 / Integer.parseInt(txtStartTimeMinute.getText()));
        int duration = Integer.parseInt(txtDuration.getText());
        
        
        //fetching release data
        Date releaseDate;
        try{
            releaseDate = rdAfterSubmission.isSelected() ? new Date(): 
                new SimpleDateFormat("dd/MM/yyyy").parse(txtCustomDate.getText());
        }
        catch(Exception e){
            releaseDate = new Date();
        }
        
        
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        Exam exam = new Exam("", course.getCode(), instructor,startTime, duration, releaseDate, getQuestions());
    }
    
    private ArrayList<Question> getQuestions(){
        ArrayList<Question> questions = new ArrayList<Question>();
        questions.add(new Question("", null, getEnabledRadioButtonIndex(rdQ1A1, rdQ1A2, rdQ1A3, rdQ1A4), 0, 0));
        questions.add(new Question("", null, getEnabledRadioButtonIndex(rdQ2A1, rdQ2A2, rdQ2A3, rdQ2A4), 0, 0));
        questions.add(new Question("", null, getEnabledRadioButtonIndex(rdQ3A1, rdQ3A2, rdQ3A3, rdQ3A4), 0, 0));
        questions.add(new Question("", null, getEnabledRadioButtonIndex(rdQ4A1, rdQ4A2, rdQ4A3, rdQ4A4), 0, 0));
        questions.add(new Question("", null, getEnabledRadioButtonIndex(rdQ5A1, rdQ5A2, rdQ5A3, rdQ5A4), 0, 0));
        questions.add(new Question("", null, getEnabledRadioButtonIndex(rdQ6A1, rdQ6A2, rdQ6A3, rdQ6A4), 0, 0));
        questions.add(new Question("", null, getEnabledRadioButtonIndex(rdQ7A1, rdQ7A2, rdQ7A3, rdQ7A4), 0, 0));

        return null;
    }
    
    private int getEnabledRadioButtonIndex(RadioButton... buttons){
        int index = 0;
        for (RadioButton button : buttons) {
            if(button.isSelected())
                return index;
            index++;
        }
        return 0;
    }
}
