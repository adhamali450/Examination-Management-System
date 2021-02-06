package main;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import models.Student;


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
    private RadioButton rdMale;

    @FXML
    private RadioButton rdFemale;

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
                navSystem.switchScene(event, NavSystem.LOGIN);
                System.out.println("registration success");
            }
        }
        else if (event.getSource() == btnGoback){
            navSystem.switchScene(event, NavSystem.LOGIN);
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
        int gender = rdMale.isSelected() ? 0 : 1;

        //If username isn't unique, show error message
        if(!Student.isUniqueUsername(username)){
            triggerRegisterError("Username already exists");

            return false;
        }
        //continue with the process of registration
        else
        {
            //generate a unique id for the student
            UUID iD = UUID.randomUUID();;

            //insert the new student into the database
            Student student = new Student(username, password,
                    name, phone_num, email, iD, gender);
            student.setGender(gender);

            student.push();
            return true;
        }
    }
}