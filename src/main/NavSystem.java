package main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class NavSystem {
    public void SwitchScene(MouseEvent event, String fxmlFileDir, Boolean maximize)
    {
        // Grab the node representing the button from the event object
        Node node = (Node)event.getSource();
        // Get the instance of the stage from the node and close it
        Stage stage = (Stage) node.getScene().getWindow();
       
        stage.setMaximized(maximize);

        stage.close();
        try {
            //Create a scene through the FXMLLoader class
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource(fxmlFileDir)));

            stage.setScene(scene);

            // Make the stage visible again
            stage.show();
        } catch ( IOException e) {
            System.err.println(String.format("Error: %s", e.getMessage()));
        }
    }

    public void SwitchScene(MouseEvent event, String fxmlFileDir)
    {
        SwitchScene(event, fxmlFileDir, false);
    }
}
