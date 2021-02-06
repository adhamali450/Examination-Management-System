package models;

import com.sun.tools.javac.Main;
import utils.ConnectionUtil;

import java.sql.*;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Student extends EndUser{
    private List<Course> coursesEnrolled;
    private int gender;
    private final UUID iD;

    public Student(String username, String password, String name,String phoneNumber,
                      String emailAddress, UUID iD, int gender) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.iD = iD;
        this.phoneNumber = phoneNumber;
        this.emailAddress  = emailAddress;
        this.gender = gender;

        coursesEnrolled = Course.getAllCourses();
    }


    //region getters and setters
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() { return iD;}

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public int getGender(){
        return this.gender;
    }

    @Override
    public void setGender(int gender){
        this.gender = gender;
    }

    //endregion

    public List<Course> getCoursesEnrolled() {return coursesEnrolled; }

    public static boolean isUniqueUsername(String username){
        try{
            Connection con = DriverManager.getConnection(ConnectionUtil.dbURL, ConnectionUtil.username,
                    ConnectionUtil.password);

            String query =  "select username from examsystem.students";
            Statement statement = con.createStatement();
            ResultSet rText = statement.executeQuery(query);

            while(rText.next()) {
                if(rText.getString("USERNAME").equals(username))
                    return false;
            }

            con.close();
        }
        catch(SQLException ex){
            var lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return true;
    }

    public void push(){
        try{
            Connection con = DriverManager.getConnection(ConnectionUtil.dbURL, ConnectionUtil.username,
                    ConnectionUtil.password);

            String query =  String.format("INSERT INTO STUDENTS(username, password, id, sname, phone_number, email_address, gender) " +
                    "VALUES('%s', '%s', '%s', '%s', '%s', '%s', %s)",
                    username.toLowerCase(), password, iD, name, phoneNumber, emailAddress.toLowerCase(), gender);

            Statement st = con.createStatement();

            st.execute(query);

            con.close();
        }
        catch(SQLException ex){
            var lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public static Student Login(String username, String password) {
        try{
            Connection con = DriverManager.getConnection(ConnectionUtil.dbURL, ConnectionUtil.username,
                    ConnectionUtil.password);

            //sql query to get the student with the specified username and password
            String query =  String.format("SELECT * " +
                            "FROM STUDENTS " +
                            "where USERNAME = '%s' and PASSWORD = '%s'",
                    username, password);

            Statement statement = con.createStatement();
            ResultSet rText = statement.executeQuery(query);

            if(!rText.next()) {
                return null;
            }
            else
            {
                    Student student = new Student(
                            rText.getString("username"),
                            rText.getString("password"),
                            rText.getString("sname"),
                            rText.getString("phone_number"),
                            rText.getString("email_address"),
                            UUID.fromString(rText.getString("id")),
                            Integer.parseInt(rText.getString("gender"))
                    );

                    con.close();

                    return student;
            }
        }
        catch(SQLException ex){
            var lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

            return null;
        }
    }
}

