package models;

import java.util.Date;
import java.util.List;

public class Exam {
    private String iD, courseCode;
    private String instructorName;
    private int startTime, endTime, duration, mark;
    private Date releaseDate;
    private boolean validationStatus;
    private List<Question> questions;

    public Exam(String iD, String courseCode, String instructorName,
                int startTime, int duration, Date releaseDate, List<Question> questions) {
        this.iD = iD;
        this.courseCode = courseCode;
        this.instructorName = instructorName;
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

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartDate(int startTime) {
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

    public void setQuestions(List<Question> questions) {
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

