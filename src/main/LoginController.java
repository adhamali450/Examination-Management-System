package main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import models.EndUser;
import models.Instructor;
import models.Student;

public class LoginController implements Initializable {

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
                    navSystem.switchScene(event, NavSystem.INSTRUCTOR_DASHBOARD);
                }
                else if(rdStudent.isSelected()){
                    navSystem.switchScene(event, NavSystem.STUDENT_DASHBOARD);
                }
            }
            else
            {
                txtPassword.setText("");
                lblErrors.setText("Invalid username or password");
            }
        }
        if (event.getSource() == btnSignup) {
            navSystem.switchScene(event, NavSystem.SIGNUP);
        }
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

        if(username.equals("") || password.equals(""))
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
