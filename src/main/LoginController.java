package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.EndUser;
import models.Instructor;
import models.Student;

public class LoginController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Label lbl_close;

    @FXML
    private Pane successBanner;

    @FXML
    private Label lblErrors;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private RadioButton rdStudent;

    @FXML
    private RadioButton rdInstructor;


    @FXML
    private Button btnSignin;

    @FXML
    private Button btnSignup;

    private final NavSystem navSystem;
    
    //handling all clicking events for the scene based on the source
    public void handleButtonAction(MouseEvent event) {
        if (event.getSource() == btnSignin) {
            if (login()) {
                if(rdInstructor.isSelected()) {
                    navSystem.SwitchScene(event, "InstructorDashboard.fxml");
                }
                else if(rdStudent.isSelected()){
                    navSystem.SwitchScene(event, "StudentDashboard.fxml");
                }
            }
            else
            {
                txtPassword.setText("");
                lblErrors.setText("Invalid username or password");
            }
        }
        if (event.getSource() == btnSignup) {
            navSystem.SwitchScene(event, "Signup.fxml");
        }
    }

    public void setBannerVisibility(Boolean visibility){
        successBanner.setVisible(visibility);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
    
    }

    public LoginController() {
        navSystem = new NavSystem();
    }


    private Boolean login() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if(username == "" || password == "")
            return false;
        
        if(rdInstructor.isSelected()) {
            Instructor instructor = Instructor.Login(username, password);
            if(instructor != null){
                EndUser.loggingUser = instructor;
                return true;
            }
            else
            {
                return false;
            }
        }
        else if(rdStudent.isSelected()){
            Student student = Student.Login(username, password);
            
            if(student != null){
                EndUser.loggingUser = student;
                return true;
            }
            else
            {
                return false;
            }
        }
        
        
        return false;
    }
}
