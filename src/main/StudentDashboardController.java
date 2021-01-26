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
        
        lblStudentName.setText(EndUser.loggingUser.getName());
    }    

    @FXML
    private void handleButtonAction(MouseEvent event) {
        if (event.getSource() == btnLogout) {
            navSystem.SwitchScene(event, "Login.FXML");
            EndUser.loggingUser = null;
            
            System.out.println("logout");
        }
        if(event.getSource() == btnOOP){
            System.out.println("OOP");
        }
        if(event.getSource() == btnSA){
            System.out.println("SA");
        }
        if(event.getSource() == btnDBMS){
            System.out.println("DBMS");
        }
        if(event.getSource() == btnLD){
            System.out.println("LD");
        }
        if(event.getSource() == btnDM){
            System.out.println("DM");
        }
        if(event.getSource() == btnRW){
            System.out.println("RW");
        }
    }
    
}
