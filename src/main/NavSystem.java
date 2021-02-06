package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class NavSystem {
    public static String EXAM_ATTEMPT = "EXAMATTEMPTING.FXML",
                         EXAM_PREPARATION = "EXAMPREPARATION.FXML",
                         INSTRUCTOR_DASHBOARD = "INSTRUCTORDASHBOARD.FXML",
                         LOGIN = "LOGIN.FXML",
                         NO_EXAMS_FOUND = "NOEXAMSFOUND.FXML",
                         SIGNUP = "SIGNUP.FXML",
                         STUDENT_DASHBOARD = "STUDENTDASHBOARD.FXML";

    public static Stage getStageFromNode(Node node){
        // Get the instance of the stage from the node and close it
        return (Stage) node.getScene().getWindow();
    }

    public static Stage getStageFromNode(MouseEvent event){
        // Grab the node representing the button from the event object
        Node node = (Node)event.getSource();
        // Get the instance of the stage from the node and close it
        return getStageFromNode(node);
    }
    
    public void switchScene(MouseEvent event, String fxmlFileDir, Boolean maximize)
    {
        Stage stage = getStageFromNode(event);

        stage.setMaximized(maximize);

        stage.close();
        try {
            //Create a scene through the FXMLLoader class
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlFileDir)));

            stage.setScene(scene);

            // Make the stage visible again
            stage.show();
        } catch ( IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void switchScene(MouseEvent event, String fxmlFileDir)
    {
        switchScene(event, fxmlFileDir, false);
    }
}
