package models;

import com.sun.tools.javac.Main;
import utils.ConnectionUtil;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Instructor extends EndUser {
    private int age, gender;
    private Course courseOffered;

    public Instructor(String username, String password, String name, String phone_number,
                      String emailAddress, int age, int gender) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phone_number;
        this.emailAddress  = emailAddress;
        this.age = age;
        this.gender = gender;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender(){
        return gender;
    }
    
    public void setGender(int gender){
        this.gender = gender;
    }
    
    public Course getCourseOffered() {
        return courseOffered;
    }

    public void setCourseOffered(Course courseOffered) {
        this.courseOffered = courseOffered;
    }
    //endregion

    public static Instructor Login(String username, String password) {
        try{
            Connection con = DriverManager.getConnection(ConnectionUtil.dbURL, ConnectionUtil.username,
                    ConnectionUtil.password);

            //sql query to get the instructor with the specified username and password
            String instructorQuery =  String.format("SELECT * " +
                            "FROM INSTRUCTORS " +
                            "where USERNAME = '%s' and PASSWORD = '%s'",
                    username, password);


            Statement instructorStatement = con.createStatement();
            ResultSet rText = instructorStatement.executeQuery(instructorQuery);

            if(!rText.next()) {
                System.out.println("invalid login info");
                return null;
            }
            else
            {
                if(rText.getString("username").equals(username) &&
                        rText.getString("password").equals(password)){
                    System.out.println("successful login");

                    Instructor instructor = new Instructor(
                            rText.getString("username"),
                            rText.getString("password"),
                            rText.getString("iname"),
                            rText.getString("phone_number"),
                            rText.getString("email_address"),
                            Integer.parseInt(rText.getString("age")),
                            Integer.parseInt(rText.getString("gender")));

                    //get course offered by instructor
                   instructor.setCourseOffered(Course.getCourseByInstructor(con, instructor));
                   
                    con.close();

                    return instructor;
                }
                else
                {
                    System.out.println("invalid login info");
                    con.close();
                    return null;
                }
            }
        }
        catch(SQLException ex){
            var lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

            return null;
        }
    }
    
    static Instructor getInstructorByCourse(Connection con, Course course){
        try {
            //sql query to get the instructor with the specified course offered
            String query =  String.format("select * " +
                                        "from instructors " +
                                        "where username = (select instructor_username " +
                                        "                  from instructor_offering " +
                                        "                  where course_code = '%s')",
                    course.getCode());

            Statement statement = con.createStatement();
            ResultSet rText = statement.executeQuery(query);
            
            
            if(!rText.next()) {
                System.out.println("No instructor associated with this course");
                return null;
            }
            else
            {
                    Instructor instructor = new Instructor(
                            rText.getString("username"),
                            rText.getString("password"),
                            rText.getString("iname"),
                            rText.getString("phone_number"),
                            rText.getString("email_address"),
                            Integer.parseInt(rText.getString("age")),
                            Integer.parseInt(rText.getString("gender")));
                    

                    return instructor;
            }
        } catch (SQLException ex) {
            var lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return null;
    }
    
    public static Instructor getInstructorByCourse(Course course){
        try{
            Connection con = DriverManager.getConnection(ConnectionUtil.dbURL, ConnectionUtil.username,
                    ConnectionUtil.password);
            return getInstructorByCourse(con, course);
        } catch(SQLException ex){
            var lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return null;
    }
    
    public static Instructor getInstructorByCourse(String courseCode){
        return getInstructorByCourse(Course.fromCourseCode(courseCode));
    }        
}
