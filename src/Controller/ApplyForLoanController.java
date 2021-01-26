/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Handler;
import Model.Notification;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observer;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author bounce
 */
public class ApplyForLoanController implements Initializable {

    @FXML
    private Pane headerPane;
    @FXML
    private Pane bodyPane;
    @FXML
    private JFXTextField studentId;
    @FXML
    private JFXComboBox<String> LoanType;
    @FXML
    private JFXTextArea loanReason;
    @FXML
    private StackPane windowStackpane;
    @FXML
    private JFXTextField amount;
    
    public static JFXTextField  StudentId;
    ObservableList<String> list = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Handler handler = Handler.getInstance();
        JFXDepthManager.setDepth(headerPane, 1);
        JFXDepthManager.setDepth(bodyPane, 1);
        StudentId = studentId;
        list.addAll("Partial Loan Agreement", "Full Loan Agreement");
        LoanType.getItems().addAll(list);
        
        // TODO
    }    

    @FXML
    private void ConfirmLoanApplication(ActionEvent event) {
        Handler handler = new Handler();
        String loanReason = this.loanReason.getText();
        Double amount = Double.parseDouble(this.amount.getText());
        String loanType = this.LoanType.getValue().toString();
        String StudentId = studentId.getText();     
         
        if(loanReason.isEmpty() || amount.toString().isEmpty() || 
                loanType.isEmpty() || StudentId.isEmpty())
        {
            Notification notify = new Notification(2, "Application Window", "Fill in To Complete Loan Application");
            notify.start();
        }
        
        try
        {  
         
            String st = "select * from Application where StudentId='"+StudentId+"'";
            ResultSet rs = handler.execQuery(st); 
            if(!rs.next())
            { 
                 String query="INSERT INTO Application (LoanType,Amount,LoanCase,StudentId,IsOnLoan)"+
             "VALUES("
                    +"'"+loanType+"',"
                    +"'"+amount+"',"
                    +"'"+loanReason+"',"
                    +"'"+StudentId+"',"
                    +""+"false"+ ""
            +")";        
             handler.execAction(query);
             Notification noti = new Notification(2, "Application Window", "Application Submitted Successfully");
             noti.start();              
            }
            else
            {
             
              Notification noti = new Notification(3,"Loan Window","The Student is either onLoan or Pending List");
              noti.start();
            }
              
            
        }
        catch (SQLException ex)
        {
            
        }
               
    }

    @FXML
    private void CloseWindowAction(ActionEvent event) {
        windowStackpane.getScene().getWindow().hide();
        
    }
    
}
