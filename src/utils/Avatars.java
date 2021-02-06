package utils;

import javafx.scene.image.Image;
import models.Course;

import java.io.File;
import java.io.FileInputStream;

public class Avatars {
    private static String assetsRelDir = "src/main/Assets";
    public static Image getGenderAvatar(int gender){
        String absolutePath = new File(assetsRelDir).getAbsolutePath();
        switch(gender){
            case 0:
                absolutePath += "/user-male.png";
                break;
            case 1:
                absolutePath += "/user-female.png";
                break;
        }
        try{
            return new Image(new FileInputStream(absolutePath));
        }
        catch(Exception ex){
            System.out.println("couldn't find img");
        }

        return null;
    }

    public static Image getCourseIcon(Course course){
        return getCourseIcon(course.getCode());
    }

    public static Image getCourseIcon(String courseCode){
        String imgDir = new File(assetsRelDir).getAbsolutePath();
        switch(courseCode){
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
            return new Image(new FileInputStream(imgDir));
        }
        catch(Exception ex){
            System.out.println("couldn't find img");
        }

        return null;
    }
}
