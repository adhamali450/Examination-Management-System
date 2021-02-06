/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.File;
import java.io.FileInputStream;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import models.EndUser;
import models.Instructor;
import utils.Avatars;

public class InstructorDashboardController implements Initializable{

    @FXML
    private Label lblInstructorName;

    @FXML
    private Button btnLogout;

    @FXML
    private ImageView imgCourse;

    @FXML
    private Label lblCourseName;

    @FXML
    private Button btnExam;

    @FXML
    private Button btnReport;

    @FXML
    private ImageView imgGender;

    @FXML
    private Label lblInfoInstructorName;

    @FXML
    private Label lblInstructorEmail;

    @FXML
    private Label lblInstructorNumber;

    
    NavSystem navSystem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        navSystem = new NavSystem();

        
        //Loading basic info
        Instructor instructor = (Instructor)EndUser.loggingUser;

        imgGender.setImage(Avatars.getGenderAvatar(instructor.getGender()));
        String instructorName = "Dr. " + instructor.getName();
        lblInstructorName.setText(instructorName);
        lblInfoInstructorName.setText(instructorName);
        lblInstructorEmail.setText(instructor.getEmailAddress());
        lblInstructorNumber.setText("(+20) " + instructor.getPhoneNumber());
        
        //2- Loading course offered basic info
        lblCourseName.setText(instructor.getCourseOffered().getName());

        imgCourse.setImage(Avatars.getCourseIcon(instructor.getCourseOffered()));
    }    

    @FXML
    private void handleButtonAction(MouseEvent event) {
        if (event.getSource() == btnLogout) {
            navSystem.switchScene(event, NavSystem.LOGIN);
            EndUser.loggingUser = null;
        }
        if (event.getSource() == btnExam) {
            navSystem.switchScene(event, NavSystem.EXAM_PREPARATION);
        }
        
    }
}