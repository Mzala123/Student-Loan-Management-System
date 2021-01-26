/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Handler;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import jfxtras.scene.control.gauge.linear.BasicRoundDailGauge;
import jfxtras.scene.control.gauge.linear.SimpleMetroArcGauge;

/**
 * FXML Controller class
 *
 * @author bounce
 */
public class DashBoardController implements Initializable {

    @FXML
    private Label lblTotalStudents;
    @FXML
    private SimpleMetroArcGauge malesGauge;
    @FXML
    private SimpleMetroArcGauge femalesGauge;
    @FXML
    private Label lblTotalApplied;
    @FXML
    private BasicRoundDailGauge onLoanGauge;
    @FXML
    private BasicRoundDailGauge returnedGauge;
    
    int count = 0;
    int LoanCount = 0 ;
    int onLoan = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        loadDashBoardData();
    }  
    
    
    
    private void loadDashBoardData(){
        try{
              Task<Void> task = new Task<Void>() {
               @Override
               protected Void call() throws Exception {
                         Thread.sleep(1000);
                      
                         Platform.runLater(new Runnable() {
                             @Override
                             public void run() {
                                TotalNumberOfStudents();
                                TotalMaleStudents();
                                TotalFemaleStudents();
                                TotalAppliedForLoanStudents();
                                StudentsOnLoan();
                                StudentsReturnedLoan();
                             }
                         });
                   return null;
               }
           };
           new Thread(task).start();
        }catch(Exception ex){
            
        }
     }
    
    private void TotalNumberOfStudents()
    {
        Handler handler = new Handler();
        String query = "SELECT count(*) FROM Student";
        ResultSet rs = handler.execQuery(query);
 
        try
        {
            while(rs.next())
            {
               count = rs.getInt(1);
            }
            
        }
        catch(SQLException ex)
        {
            
        }
        lblTotalStudents.setText("" +count);
    }
    
    private void TotalMaleStudents()
    {
        malesGauge.setMaxValue(count);
        Handler handler = new Handler();
        String query = "SELECT count(*) FROM Student WHERE Gender='Male'";
        ResultSet rs = handler.execQuery(query);
        int malesCount = 0;
        try
        {
            if(rs.next())
            {
               malesCount = rs.getInt(1);
            }
            malesGauge.setValue(malesCount);
            
        }
        catch(SQLException ex)
        {
            
        }
        
    }  
        private void TotalFemaleStudents()
    {
        femalesGauge.setMaxValue(count);
        Handler handler = new Handler();
        String query = "SELECT count(*) FROM Student WHERE Gender='Female'";
        ResultSet rs = handler.execQuery(query);
        int femalesCount = 0;
        try
        {
            if(rs.next())
            {
               femalesCount = rs.getInt(1);
            }
            femalesGauge.setValue(femalesCount);
            
        }
        catch(SQLException ex)
        {
            
        }
    
        
        
    }
   
        
         private void TotalAppliedForLoanStudents()
    {
        
        Handler handler = new Handler();
        String query = "SELECT count(*) FROM Application WHERE IsReturn='0'";
        ResultSet rs = handler.execQuery(query);
        //int femalesCount = 0;
        try
        {
            if(rs.next())
            {
               LoanCount = rs.getInt(1);
            }
            lblTotalApplied.setText("" +LoanCount);
            
        }
        catch(SQLException ex)
        {
            
        }
    
        
        
    }
    
         
              private void StudentsOnLoan()
    {
       // onLoanGauge.setMaxValue(LoanCount);
        Handler handler = new Handler();
        String query = "SELECT count(*) FROM Application WHERE IsOnLoan='1'";
        ResultSet rs = handler.execQuery(query);
        
        try
        {
            if(rs.next())
            {
               onLoan = rs.getInt(1);
            }
            double percent = (onLoan * 100)/LoanCount ;
            onLoanGauge.setValue(percent);
              
        }
        catch(SQLException ex)
        {
            
        }
    
        
        
    }
              
              
                        private void StudentsReturnedLoan()
    {
       // onLoanGauge.setMaxValue(LoanCount);
        Handler handler = new Handler();
        String query = "SELECT count(*) FROM Application WHERE IsReturn='1'";
        ResultSet rs = handler.execQuery(query);
        int returnLoan = 0;
        try
        {
            if(rs.next())
            {
               returnLoan = rs.getInt(1);
            }
            double percent = (returnLoan * 100)/onLoan ;
            returnedGauge.setValue(percent);
              
        }
        catch(SQLException ex)
        {
            
        }
    
        
        
    }
    
         
}
