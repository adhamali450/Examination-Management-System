package models;

import com.sun.tools.javac.Main;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnectionUtil;

public class Course {
    private int iD;
    private String name;
    private final String code;
    private Instructor instructorOffering;
    private ArrayList<Exam> addedExams;

    public Course(int iD, String name, String code) {
        this.iD = iD;
        this.name = name;
        this.code = code;

        addedExams = new ArrayList<>();
    }

    //region getters and setters
    public int getiD() {
        return iD;
    }
    public void setiD(int iD) {
        this.iD = iD;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instructor getInstructorOffering() {
        return instructorOffering;
    }

    public void setInstructorOffering(Instructor instructorOffering) {
        this.instructorOffering = instructorOffering;
    }

    public ArrayList<Exam> getAddedExams() {
        return addedExams;
    }

    public void setAddedExams(ArrayList<Exam> addedExams) {
        this.addedExams = addedExams;
    }
    //endregion

    public void addExam(Exam exam){
        addedExams.add(exam);
    }

    public static ArrayList<Course> getCourses(){
        ArrayList<Course> courses = new ArrayList<Course>();
        try{
            Connection con = DriverManager.getConnection(ConnectionUtil.dbURL, ConnectionUtil.username,
                    ConnectionUtil.password);

            //sql query to get the all the courses and parse them into objects
            String query =  "SELECT * " +
                            "FROM COURSES";

            Statement statement = con.createStatement();
            ResultSet rText = statement.executeQuery(query);

            while(rText.next()){
                Course course = new Course(
                        Integer.parseInt(rText.getString("course_id")),
                        rText.getString("cname"),
                        rText.getString("course_code"));
                
                course.setInstructorOffering(
                        Instructor.getInstructorByCourse(con, course));
                
                courses.add(course);
            }
            
            return courses;
        }
        catch(SQLException ex){
            var lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

            return null;
        }
    }
    
    
    public static Course fromCourseCode(String courseCode){
        try{
            Connection con = DriverManager.getConnection(ConnectionUtil.dbURL, ConnectionUtil.username,
                    ConnectionUtil.password);

            String query =  String.format("select * from courses "
                    + "where course_code = '%s'", courseCode);
            
            Statement statement = con.createStatement();
            
            ResultSet rText = statement.executeQuery(query);

            if(rText.next()){
                Course course = new Course(
                        Integer.parseInt(rText.getString("course_id")),
                        rText.getString("cname"),
                        courseCode);
                
                course.setInstructorOffering(Instructor.getInstructorByCourse(con ,course));
                
                con.close();

                return course;
            }
        }
        catch(SQLException ex){
            var lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return null;
    }
    
    static Course getCourseByInstructor(Connection con, Instructor instructor){
        try{

            String query =  String.format("select * from courses "
                    + "where course_code = (select course_code "
                    + "from instructor_offering "
                    + "where instructor_username = '%s')", instructor.getUsername());
            
            Statement statement = con.createStatement();
            
            ResultSet rText = statement.executeQuery(query);

            if(rText.next()){
                Course course = new Course(
                        Integer.parseInt(rText.getString("course_id")),
                        rText.getString("cname"),
                        rText.getString("course_code"));
                
                course.setInstructorOffering(instructor);               

                return course;
            }
        }
        catch(SQLException ex){
            var lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return null;
    }
    
    @Override
    public String toString() { 
        return String.format("course_code: %s\n"
                + "course_id: %s\n"
                + "course_name: %s\n"
                + "instructor_offering: %s\n"
                + "===========================\n",
                getCode(), getiD(), getName(), getInstructorOffering().name);
    } 
}
