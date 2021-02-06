package models;

import com.sun.tools.javac.Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnectionUtil;
import utils.DateConverter;

public class Course {
    private UUID iD;
    private String name;
    private final String code;
    private Instructor instructorOffering;
    private ArrayList<Exam> addedExams;

    public Course(UUID iD, String name, String code) {
        this.iD = iD;
        this.name = name;
        this.code = code;

        addedExams = new ArrayList<>();
    }

    //region getters and setters
    public UUID getId() {
        return iD;
    }
    public void setId(UUID iD) {
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

    public static ArrayList<Course> getAllCourses(){
        ArrayList<Course> courses = new ArrayList<>();
        try{
            Connection con = DriverManager.getConnection(ConnectionUtil.dbURL, ConnectionUtil.username,
                    ConnectionUtil.password);

            //sql query to get the all the courses and parse them into objects
            String query =  "SELECT * FROM COURSES";

            Statement statement = con.createStatement();
            ResultSet rText = statement.executeQuery(query);

            while(rText.next()){
                Course course = new Course(
                        UUID.fromString(rText.getString("course_id")),
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
                        UUID.fromString(rText.getString("course_id")),
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
                        UUID.fromString(rText.getString("course_id")),
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

    public Boolean hasExams(){
        return hasExams(this);
    }

    public List<Exam> getExams(){
        return getExams(this);
    }

    public static Boolean hasExams(Course course){
        try{
            Connection con = DriverManager.getConnection(ConnectionUtil.dbURL, ConnectionUtil.username,
                    ConnectionUtil.password);

            String query =  String.format("SELECT * " +
                            "FROM EXAMS " +
                            "where course_code = '%s'",
                    course.getCode());

            Statement statement = con.createStatement();
            ResultSet rText = statement.executeQuery(query);

            return rText.next();
        }
        catch(SQLException ex){
            var lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

            return false;
        }
    }

    public static ArrayList<Exam> getExams(Course course){
        try{
            Connection con = DriverManager.getConnection(ConnectionUtil.dbURL, ConnectionUtil.username,
                    ConnectionUtil.password);

            String query =  String.format("SELECT * " +
                            "FROM EXAMS " +
                            "where course_code = '%s'",
                    course.getCode());

            Statement statement = con.createStatement();
            ResultSet rText = statement.executeQuery(query);


            if(!rText.next()) {
                return null;
            }
            else{
                ArrayList<Exam> exams = new ArrayList<>();
                while(!rText.next()){
                    Exam exam = new Exam(
                            UUID.fromString(rText.getString("exam_id")),
                            course.getCode(),
                            Instructor.getInstructorByCourse(course.getCode()),
                            Float.parseFloat(rText.getString("start_time")),
                            Exam.calculateDuration(Integer.parseInt(rText.getString("start_time")),
                                    Integer.parseInt(rText.getString("end_time"))),
                            DateConverter.fromSql(rText.getString("release_date"))
                    );

                    exams.add(exam);
                }

                return exams;
            }


        }
        catch(SQLException ex){
            var lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

            return null;
        }
    }

    @Override
    public String toString() { 
        return String.format("""
                        course_code: %s
                        course_id: %s
                        course_name: %s
                        instructor_offering: %s
                        ===========================
                        """,
                getCode(), getId(), getName(), getInstructorOffering().name);
    }
}
