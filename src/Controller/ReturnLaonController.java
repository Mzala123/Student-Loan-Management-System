/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Handler;
import Model.Notification;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author bounce
 */
public class ReturnLaonController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private JFXTextField studentId;
    @FXML
    private JFXTextField amountPaid;
    @FXML
    private JFXTextField balance;
    @FXML
    private JFXTextField availableAmount;
    
    
    public static JFXTextField  StudentId;
    public static String student_Id;
    public static JFXTextField  LoanTopay;
     public static Double loanToPay;
    public static JFXTextField  amountpaid;
    public static Double amount_paid;
    public static JFXTextField  Balance;
    public static Double balances;
    @FXML
    private StackPane windowStackpane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        JFXDepthManager.setDepth(pane, 1);
        LoanTopay=availableAmount;
        StudentId = studentId;
        amountpaid = amountPaid;
        Balance = balance;
        
        // TODO
    }    

    @FXML
    private void PrintReceipt(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/View/Receipt.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.DECORATED);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReturnLaonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void CloseWindow(ActionEvent event) {
        
        windowStackpane.getScene().getWindow().hide();
    }

    @FXML
    private void setBalanc(KeyEvent event) {
//        Double availableloan = Double.parseDouble(availableAmount.getText());
//        Double amountpaid = Double.parseDouble(amountPaid.getText());
//        Double balance1=0.0;
//        balance1 = availableloan - amountpaid;
//        balance.setText(Double.toString(balance1));
    }

    @FXML
    private void setBalanceAction(KeyEvent event) {
        
        Handler handler  = new Handler();
        student_Id = studentId.getText();
        loanToPay = Double.parseDouble(availableAmount.getText());
        amount_paid = Double.parseDouble(amountPaid.getText());
        balances =0.0;
        balances = loanToPay - amount_paid;
        balance.setText(Double.toString(balances));
        
        if(balances < loanToPay)
        {
            String query = "UPDATE Application Set Amount ='"+balances+"' WHERE StudentId ='"+student_Id+"'";
            handler.execUpdate(query);
            Notification notify = new Notification(2, "Paid Part of Loan", " Successfully, Check new Balance");
            notify.start();
        }
        
        if (balances == 0.0)
        {
            String query = "UPDATE Application Set IsReturn = '1' WHERE StudentId ='"+student_Id+"'";
            handler.execUpdate(query);
            Notification notify = new Notification(2, "List of Student ReturnedLoan", "Return Loan List Updated");
            notify.start();
        }
        
    }

    @FXML
    private void setBalanceActionOnenter(ActionEvent event) {
   
        Handler handler  = new Handler();
        student_Id = studentId.getText();
        loanToPay = Double.parseDouble(availableAmount.getText());
        amount_paid = Double.parseDouble(amountPaid.getText());
        balances =0.0;
        balances = loanToPay - amount_paid;
        balance.setText(Double.toString(balances));
        
        if(balances < loanToPay)
        {
            String query = "UPDATE Application Set Amount ='"+balances+"' WHERE StudentId ='"+student_Id+"'";
            handler.execUpdate(query);
            Notification notify = new Notification(2, "Paid Part of Loan", " Successfully, Check new Balance");
            notify.start();
        }
        
        if (balances == 0.0)
        {
            String query = "UPDATE Application Set IsReturn = '1' WHERE StudentId ='"+student_Id+"'";
            handler.execUpdate(query);
            Notification notify = new Notification(2, "List of Student ReturnedLoan", "Return Loan List Updated");
            notify.start();
        }
        
        
    }
    
    
}
