/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Handler;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bounce
 */
public class ReceiptController implements Initializable {

    @FXML
    private Pane textarea;
    @FXML
    private Label studentId;
    @FXML
    private Label totalLoanToBePaid;
    @FXML
    private Label DateOfPayment;
    @FXML
    private Label amountPaid;
    @FXML
    private Label balance;
    
    private Label jobStatus = new Label();
    private Stage owner;
    private String message;
    @FXML
    private Label TimeOfPayment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Handler handler = Handler.getInstance();
        LabelDate();
        // TODO
    }    

    @FXML
    private void PrintReceiptAction(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job == null) {
            return;
        }

        // Show the page setup dialog
        boolean proceed = job.showPageSetupDialog(textarea.getScene().getWindow());

        if (proceed) {
//            printer(job, textarea);
        }
    }
    
     private void printer(PrinterJob job, Node node)
 {
        // Set the Job Status Message
        jobStatus.textProperty().bind(job.jobStatusProperty().asString());

        // Print the node
        boolean printed = job.printPage(node);

        if (printed) {
            job.endJob();
        }
    }
     
     
    private void LabelDate()
    {
        studentId.setText(ReturnLaonController.student_Id);
        totalLoanToBePaid.setText(Double.toString(ReturnLaonController.loanToPay));
        amountPaid.setText(Double.toString(ReturnLaonController.amount_paid));
        balance.setText(Double.toString(ReturnLaonController.balances));
        DateOfPayment.setText(LocalDate.now().toString());
        TimeOfPayment.setText(LocalTime.now().toString());
                   
 
    } 
    
    
}
