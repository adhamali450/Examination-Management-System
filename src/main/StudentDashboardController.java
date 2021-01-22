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
import javafx.scene.input.MouseEvent;
import models.EndUser;

/**
 * FXML Controller class
 *
 * @author adham
 */
public class StudentDashboardController implements Initializable {

    
    @FXML
    private Button btnLogout;
    
    
    @FXML
    private Label lblStudentName;
    
    NavSystem navSystem;
    public void handleButtonAction(MouseEvent event) {
        if (event.getSource() == btnLogout) {
            navSystem.SwitchScene(event, "Login.fxml");
            EndUser.loggingUser = null;
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        navSystem = new NavSystem();
        lblStudentName.setText(EndUser.loggingUser.getName());
    }    
    
}
