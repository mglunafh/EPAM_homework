/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myfamily;

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dev64
 */
public class MyFamily {

    private static Connection con;
    private static Scanner sc = new Scanner(System.in);
        
    public static void initDB(String name, String pswd) throws SQLException {
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("mysql driver was found");
            
            String url = "jdbc:mysql://localhost/test";
            con = DriverManager.getConnection(url, name, pswd);
            System.out.println("connection to database was obtained");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MyFamily.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    
    /**
     * 
     * @return true, if user entered EXIT in order to exit filling-data stage,
     * and false otherwise.
     * 
     */
    public static boolean processInput() throws SQLException {
        
        String info = sc.nextLine();
        String[] data = info.split(" ");
        if (data.length < 4) {
            return (data[0].equals("EXIT"));
        }
        String name = data[0];
        int yearOfBirth = Integer.parseInt(data[1]);
        int height = Integer.parseInt(data[2]);
        int weight = Integer.parseInt(data[3]);
        String insertion = "insert into family values (?, ?, ?, ?)";
        PreparedStatement stmt = con.prepareStatement(insertion);
        stmt.setString(1, name);
        stmt.setInt(2,  yearOfBirth);
        stmt.setInt(3, height);
        stmt.setInt(4, weight);
        stmt.executeUpdate();
        
        return false;
    }
    
    public static void showMembersOfFamily() throws SQLException {
        
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select * from family");
        
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
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        System.out.print("Lets connect to a database.\nEnter name: ");
        String name = sc.nextLine();
        System.out.print("\nEnter password: ");
        String pswd = sc.nextLine();
        
        initDB(name, pswd);
        
        System.out.println("If you want to exit, enter EXIT.");
        System.out.println("Enter: Name; year of birth; height; weight");
        
        while(true) {
            boolean res = processInput();
            if (res)
                break;
        }
    
        showMembersOfFamily();
    }
    
}
