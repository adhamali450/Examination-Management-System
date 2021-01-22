package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
    public final static String dbURL = "jdbc:oracle:thin:@localhost:1521:orcl";
    public final static String username = "examsystem";
    public final static String password = "manager";
}