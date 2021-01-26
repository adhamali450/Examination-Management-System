package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Exam {
    private String iD, courseCode;
    private Instructor instructor;
    private int startTime, endTime, duration, mark;
    private Date releaseDate;
    private boolean validationStatus;
    private ArrayList<Question> questions;

    public Exam(String iD, String courseCode, Instructor instructor, int startTime, int duration, Date releaseDate, ArrayList<Question> questions) {
        this.iD = iD;
        this.courseCode = courseCode;
        this.instructor = instructor;
        this.startTime= startTime;
        this.duration = duration;
        this.endTime = calculateEndTime();
        this.releaseDate = releaseDate;
        this.questions = questions;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
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
    
    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }
    
    public int getDuration(){
        return duration;
    }
    
    public void setDuration(int duration){
        this.duration = duration;
        endTime = calculateEndTime();
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public boolean getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(boolean validationStatus) {
        this.validationStatus = validationStatus;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
    
    public void addQuestion(Question question){
        this.questions.add(question);
    }
    
    private int calculateEndTime(){
        return (startTime + duration > 24)? (startTime + duration) - 24 
                : startTime + duration;
    }
}

