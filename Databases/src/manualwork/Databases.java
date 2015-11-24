/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manualwork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.out;
import java.sql.PreparedStatement;


/**
 *
 * @author dev64
 */
public class Databases {

    private static Connection con;
    
    public static void initDB() throws SQLException {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("mysql driver was found");
            
            String url = "jdbc:mysql://localhost/university";
            con = DriverManager.getConnection(url, "root", "passphrase");
            System.out.println("connection to database was obtained");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Databases.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void selectStudents() throws SQLException {
        
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from student");
        
        ResultSetMetaData metaData = rs.getMetaData();
        int columns = rs.getMetaData().getColumnCount();
        for (int i = 1; i <= columns; i++) {
            out.print(metaData.getColumnName(i) + " ");
        }
        out.println();
        
        while (rs.next()) {
            for (int i = 1; i <= columns; i++) {
                out.print(rs.getString(i) + " ");
            }
            out.println();
        }
    }
    
    public static String getStudent(int id) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select name from student where id = "+ id);
        
        rs.next();
        String result = rs.getString(1);
        return result;
    }
    
    public static void insertStudent(String name, int stip) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("insert into student (name, stip) values (?, ?)");
        stmt.setString(1, name);
        stmt.setInt(2, stip);
        stmt.execute();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException  {
        
        initDB();
        selectStudents();
        
        insertStudent("Michael", 1200);
        insertStudent("Sonya", 0);
        
        selectStudents();
        
        System.out.println(getStudent(4));
        
    }
    
}
