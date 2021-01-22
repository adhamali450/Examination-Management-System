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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import models.EndUser;
import models.Instructor;

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
        
        //1- Loading instructor basic info
        //instructor glyph based on gender
                        System.out.println();        

        String imgDir = new File("src/main/Assets").getAbsolutePath();
        switch(instructor.getGender()){
            case 0:
                imgDir += "/user-female.png";
                break;
            case 1:
                imgDir += "/user-male.png";
                break;
        }
        try{
            imgGender.setImage(new Image(new FileInputStream(imgDir)));
        }
        catch(Exception ex){
            System.out.println("couln't find img");
        }
        String instructorName = "Dr. " + instructor.getName();
        lblInstructorName.setText(instructorName);
        lblInfoInstructorName.setText(instructorName);
        lblInstructorEmail.setText(instructor.getEmailAddress());
        lblInstructorNumber.setText("(+20) " + instructor.getPhoneNumber());
        
        //2- Loading course offered basic info
        lblCourseName.setText(instructor.getCourseOffered().getName());
        
        imgDir = new File("src/main/Assets").getAbsolutePath();
        switch(instructor.getCourseOffered().getCode()){
            case "CIS280":
                imgDir += "/dbms.png";
                break;
            case "BSC221":
                imgDir += "/dm.png";
                break;
            case "CIS240":
                imgDir += "/sa.png";
                break;
            case "CIS260":
                imgDir += "/ld.png";
                break;
            case "CIS250":
                imgDir += "/oop.png";
                break;
            case "HUM113":
                imgDir += "/rw.png";
                break;
        }
        
        try{
            imgCourse.setImage(new Image(new FileInputStream(imgDir)));
        }
        catch(Exception ex){
            System.out.println("couln't find img");
        }
    }    

    @FXML
    private void handleButtonAction(MouseEvent event) {
        if (event.getSource() == btnLogout) {
            navSystem.SwitchScene(event, "Login.fxml");
            EndUser.loggingUser = null;
        }
    }
}