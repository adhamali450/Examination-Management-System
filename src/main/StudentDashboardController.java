/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import models.EndUser;
import models.Student;
import utils.Avatars;

/**
 * FXML Controller class
 *
 * @author adham
 */
public class StudentDashboardController implements Initializable {

    //header
    @FXML
    private Label lblStudentName;
    @FXML
    private Button btnLogout;
    
    //lhs content
    @FXML
    private Button btnOOP;
    @FXML
    private Button btnSA;
    @FXML
    private Button btnDBMS;
    @FXML
    private Button btnLD;
    @FXML
    private Button btnDM;
    @FXML
    private Button btnRW;
    
    //rhs content
    @FXML
    private ImageView imgGender;
    @FXML
    private Label lblInfoStudentName;
    @FXML
    private Label lblStudentId;
    @FXML
    private Label lblStudentEmail;
    @FXML
    private Label lblStudentNumber;

    
    //nav system
    NavSystem navSystem;
    
    /**
     * Initializes the controller class.
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        navSystem = new NavSystem();

        //loading basic info
        Student student = (Student)EndUser.loggingUser;

        imgGender.setImage(Avatars.getGenderAvatar(student.getGender()));
        lblStudentName.setText(student.getName());
        lblInfoStudentName.setText(student.getName());
        lblStudentId.setText(student.getId().toString());
        lblStudentEmail.setText(student.getEmailAddress());
        lblStudentNumber.setText(student.getPhoneNumber());
    }

    @FXML
    private void handleButtonAction(MouseEvent event) {
        if (event.getSource() == btnLogout) {
            System.out.println("logging out");

            navSystem.switchScene(event, NavSystem.LOGIN);
            EndUser.loggingUser = null;
        }
        if(event.getSource() == btnOOP){
            navSystem.switchScene(event, navSystem.NO_EXAMS_FOUND);
        }
        if(event.getSource() == btnSA){
            navSystem.switchScene(event, navSystem.EXAM_ATTEMPT);
        }
        if(event.getSource() == btnDBMS){
            navSystem.switchScene(event, navSystem.EXAM_ATTEMPT);
        }
        if(event.getSource() == btnLD){
            navSystem.switchScene(event, navSystem.EXAM_ATTEMPT);
        }
        if(event.getSource() == btnDM){
            navSystem.switchScene(event, navSystem.EXAM_ATTEMPT);
        }
        if(event.getSource() == btnRW){
            navSystem.switchScene(event, navSystem.EXAM_ATTEMPT);
        }
    }
}
