package main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import models.Student;
import utils.ConnectionUtil;


public class SignupController implements Initializable {

    @FXML
    private Button btnGoback;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPhoneNum;
    @FXML
    private TextField txtEmail;

    @FXML
    private Label lblErrors;

    @FXML
    private Button btnSignup;

    private final NavSystem navSystem;

    @FXML
    public void handleButtonAction(MouseEvent event) {
        //sign up
        if (event.getSource() == btnSignup) {
            if (signup()) {
                navSystem.SwitchScene(event, "Login.fxml");
                System.out.println("data passed");
            }
        }
        else if (event.getSource() == btnGoback){
            navSystem.SwitchScene(event, "Login.fxml");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public SignupController() {
        navSystem = new NavSystem();
    }

    private void triggerRegisterError(String errorMessage){
        lblErrors.setTextFill(Color.TOMATO);
        lblErrors.setText(errorMessage);
    }

    private void setButtonStatus(){

    }

    private boolean signup() {
        //generate id
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String name = txtName.getText();
        String phone_num = txtPhoneNum.getText();
        String email = txtEmail.getText();

        //If username isn't unique, show error message
        if(!Student.isUniqueUsername(username)){
            triggerRegisterError("Username already exists");

            return false;
        }
        //continue with the process of registration
        else
        {
            //generate a unique id for the student
            int iD = Student.nextId();

            //insert the new student into the database
            Student student = new Student(username, password,
                    name, phone_num, email, iD);

            student.register();
            return true;
        }
    }
}