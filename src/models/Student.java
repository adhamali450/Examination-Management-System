package models;

import com.sun.tools.javac.Main;
import utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Student extends EndUser{
    private List<Course> coursesEnrolled;
    private final int iD;

    public Student(String username, String password, String name,String phoneNumber,
                      String emailAddress, int iD) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.iD = iD;
        this.phoneNumber = phoneNumber;
        this.emailAddress  = emailAddress;

        coursesEnrolled = Course.getCourses();
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

    public int getId() { return iD;}

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

    public static int nextId(){
        try{
            Connection con = DriverManager.getConnection(ConnectionUtil.dbURL, ConnectionUtil.username,
                    ConnectionUtil.password);

            String query =  "select id from examsystem.students";
            Statement statement = con.createStatement();
            ResultSet rText = statement.executeQuery(query);

            int lastId = 0;
            while(rText.next()) {

                lastId = rText.getInt("ID");
            }

            con.close();

            return ++lastId;
        }
        catch(SQLException ex){
            var lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return -1;
    }

    public void register(){
        try{
            Connection con = DriverManager.getConnection(ConnectionUtil.dbURL, ConnectionUtil.username,
                    ConnectionUtil.password);

            String query =  String.format("INSERT INTO STUDENTS(username, password, sname, phone_number, email_address, id) " +
                    "VALUES('%s', '%s', '%s', '%s', '%s', %s)",
                    username, password, name, phoneNumber, emailAddress, iD);

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
                            Integer.parseInt(rText.getString("id"))
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

