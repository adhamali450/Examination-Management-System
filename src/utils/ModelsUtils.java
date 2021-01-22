/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.sun.tools.javac.Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Course;

/**
 *
 * @author adham
 */
public class ModelsUtils {
    public static Object getByPrimaryKey(String pkName, String pkValue){
        
        return null;
    }
    
    /*public static Object getByPrimaryKey(String tableName, String pkName, String pkValue){
        try{
            Connection con = DriverManager.getConnection(ConnectionUtil.dbURL, ConnectionUtil.username,
                    ConnectionUtil.password);

            String query =  String.format(
                    "SELECT *" +
                    "FROM %s" +
                    "Where %s = %s", tableName, pkName, pkValue);

            Statement statement = con.createStatement();
            ResultSet rText = statement.executeQuery(query);
            
            while(rText.next()){
                Course course = new Course(
                        Integer.parseInt(rText.getString("course_id")),
                        rText.getString("cname"),
                        rText.getString("course_code"),
                        "Dr. zby");
                
                //courses.add(course);
            }
            
            return null;
        }
        catch(SQLException ex){
            var lgr = Logger.getLogger(Main.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

            return null;
        }
    }*/
}
