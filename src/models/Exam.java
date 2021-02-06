package models;

import com.sun.tools.javac.Main;
import utils.ConnectionUtil;
import utils.DateConverter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exam {
    private UUID iD;
    private String courseCode;
    private Instructor instructor;
    float startTime, endTime;
    int duration, mark;
    private LocalDate releaseDate;
    private boolean validationStatus;

    public Exam(UUID iD, String courseCode, Instructor instructor, float startTime, int duration, LocalDate releaseDate) {
        this.iD = iD;
        this.courseCode = courseCode;
        this.instructor = instructor;
        this.startTime= startTime;
        this.duration = duration;
        this.endTime = calculateEndTime();
        this.releaseDate = releaseDate;

        this.validationStatus = false;
        this.mark = 50;
    }

    public UUID getId() {
        return iD;
    }

    public void setId(UUID iD) {
        this.iD = iD;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructorName(Instructor instructor) {
        this.instructor = instructor;
    }
    
    public float getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public float getEndTime() {
        return endTime;
    }
    
    public int getDuration(){
        return duration;
    }
    
    public void setDuration(int duration){
        this.duration = duration;
        endTime = calculateEndTime();
    }

    public LocalDate getReleaseDate(){
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getValidationStatus() {
        return validationStatus ? 1 : 0;
    }

    public void setValidationStatus(boolean validationStatus) {
        this.validationStatus = validationStatus;
    }

    public void setValidationStatus(int validationStatus) {
        this.validationStatus = validationStatus == 1;
    }

    public float calculateEndTime(){
        return (startTime + duration > 24)? (startTime + duration) - 24 
                : startTime + duration;
    }

    public static int calculateDuration(Exam exam){
        float startTime = exam.startTime, endTime = exam.endTime;
        return (endTime - startTime > 0)? (int)(endTime - startTime)
                : (int)((endTime - startTime) + 24);
    }

    public static int calculateDuration(float sTime, float eTime){
        return (eTime - sTime > 0)? (int)(eTime - sTime)
                : (int)((eTime - sTime) + 24);
    }

    public void push(){
        Exam.push(this);
    }

    public static void push(Exam exam){
        try{
            Connection con = DriverManager.getConnection(ConnectionUtil.dbURL, ConnectionUtil.username,
                    ConnectionUtil.password);

            String query =  String.format("insert into EXAMS values('%s', %s, '%s', %s, %s, %s, '%s', '%s')",
                    exam.getId(),
                    exam.getValidationStatus(),
                    DateConverter.toSql(exam.getReleaseDate()),
                    exam.getStartTime(),
                    exam.getEndTime(),
                    exam.getMark(),
                    exam.getInstructor().getUsername(),
                    exam.getCourseCode());

            Statement st = con.createStatement();

            st.execute(query);

            con.close();
        }
        catch(SQLException ex){
            var lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        System.out.println("exam pushed");
    }
}

