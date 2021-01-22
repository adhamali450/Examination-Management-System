package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Course;
import models.Instructor;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        System.setProperty("prism.lcdtext", "false");
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);        
    }

}
