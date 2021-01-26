/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.mysql.jdbc.MySQLConnection;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author bounce
 */
public class Handler {
    private Connection conn;
    private Statement stmt;
    MySQLConnection connect;
    String conn1;
    private static Handler handler = null;
    private String ipAddress = "192.168.0.1";
    private String username = "cftcmw_mtende";
    private String password="mtende2019";
    

    public Handler() 
    {
       //   RemoteConnection();
        CreateConnection();
        TableUsers();
        TableStudent();
        TableApplyLoan();
        
       
        
    }


    public static Handler getInstance()
    {
        if(handler==null)
        {
            handler = new Handler();
        }
        return handler;
    }
   void CreateConnection() 
    {
       try
       {
           Class.forName("com.mysql.jdbc.Driver");
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/loan", "root","");
           stmt = conn.createStatement();
           System.out.println("Connection to the database has been done successfully");
       }
       catch(Exception ex)
               {
                  System.out.println("Error." +ex);
               }
    }

   
   void RemoteConnection()
   {
       
      // conn1 ="SERVER=41.216.228.28;PORT=3306;DATABASE=cftc_loan;UID=cftcmw_mtende;PASSWORD=mtende2019";
       try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            System.out.println(ipAddress);
            
            conn= DriverManager.getConnection("jdbc:mysql://"+"41.216.288.28"+ ":3306/cftc_loan", username,password);
           // conn.setAutoCommit(true); 
            stmt = conn.createStatement();
            System.out.println("Connection to the database has been done successfully");
             }catch (Exception e){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Connection error");
                        alert.setContentText("Please make sure you have correct ip address");
                        alert.show();

        }
         
       
   }
           
           
           
     public  void createConnection(String ipAddress, String username){
        conn=null;
        stmt= null;
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            
            System.out.println(ipAddress);
            
            conn= DriverManager.getConnection("jdbc:mysql://" +"192.168.1.10"+ ":3306/loan", username,password);
            conn.setAutoCommit(true); 
             }catch (Exception e){
              Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Connection error");
                        alert.setContentText("Please make sure you have correct ip address");
                        alert.show();

        }
         
     }
    
   public ResultSet execQuery(String query)
    {
       ResultSet rs = null;
        try{
        
            stmt = conn.createStatement();
            System.out.println("Our query was: " + query);
            rs = stmt.executeQuery(query);
        }catch(Exception e){
            System.err.println(e.getClass().getName()+ ": " + e.getMessage());   
            System.exit(0);
        }
        return rs;
    }
    public boolean execAction(String st)
    {
       try
        {
            stmt = conn.createStatement();
            stmt.execute(st);
            return true;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(),
            "Error occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execAction:datahandler" + ex.getLocalizedMessage());
            return false;
        }
        finally{}
    }
      public boolean execUpdate(String qu)
    {
    try
        {
            stmt = conn.createStatement();
            stmt.executeUpdate(qu);
            return true;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(),
            "Error occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execAction:datahandler" + ex.getLocalizedMessage());
            System.out.println("Mwaloka database akulu");
            return false;
        }
        finally{}
    }
   void TableUsers()
    {
        String TableName = "User";
        try
        {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TableName.toUpperCase(), null);
            if(tables.next())
            {
                System.out.println("Table " + TableName +" Already exists in the database");
                
            }
            else
            {
                stmt.execute("CREATE TABLE " +TableName+ "(" + ""
                        + "UserId varchar(200)primary key,\n"
                        + "UserName varchar(40),\n"
                        + "Firstname varchar(40),\n"
                        + "Lastname varchar(40),\n"
                        + "Email varchar(40),\n"
                        + "Password varchar(1000),\n"
                        + "DateIncluded varchar(40),\n"
                        + "Usertype varchar(100),\n"
                        + "Image varchar(3000)\n"
                        + ")");
                System.out.println("Table User has been Created successfully");
            }
            
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage()+"setup database please");
        }
        finally
        {
            
        }
    }
    
    void TableStudent()
    {
        String TableName = "Student";
        try
        {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TableName.toUpperCase(), null);
            if(tables.next())
            {
                System.out.println("Table " + TableName +" Already exists in the database");
                
            }
            else
            {
                stmt.execute("CREATE TABLE " +TableName+ "(" + ""
                        + "StudentId varchar(200)primary key,\n"
                        + "StudentName varchar(500),\n"
                        + "Email varchar(100),\n"
                        + "Phonenumber varchar(40),\n"
                        + "Gender varchar(40),\n"
                        + "EnrollmentYear varchar(1000),\n"
                        + "YearOfStudy varchar (1000),\n"
                        + "Duration Integer,\n"
                        + "Programme varchar(1000),\n"
                        + "Image varchar(3000)\n"
                        + ")");
                System.out.println("Table Student has been Created successfully");
            }
            
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage()+"setup database please");
        }
        finally
        {
            
        }
    }
    void TableApplyLoan()
    {
        String TableName = "Application";
        try
        {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TableName.toUpperCase(), null);
            if(tables.next())
            {
                System.out.println("Table " + TableName +" Already exists in the database");
                
            }
            else
            {
                stmt.execute("CREATE TABLE " +TableName+ "(" + ""
                        + "Loan_no int NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" 
                        + "LoanType varchar(100),\n"
                        + "Amount Double,\n"
                        + "LoanCase varchar (200),\n"
                        + "StudentId varchar(200),\n" 
                        + "IsOnLoan boolean default false,\n"
                        + "ReturnDate varchar(200),\n" 
                        + "IsReturn boolean default false"
                        + ")");
                System.out.println("Table Application has been Created successfully");
            }
            
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage()+"setup database please");
        }
        finally
        {
            
        }
    }
    
    
    
   
}
