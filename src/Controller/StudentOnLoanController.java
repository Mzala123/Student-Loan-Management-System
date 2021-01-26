/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Handler;
import Model.Loan;
import Model.Notification;
import Model.Student;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author bounce
 */
public class StudentOnLoanController implements Initializable {

    //Table values for students who applied for loan
    @FXML
    private StackPane mainStakpane;
    
    
    @FXML
    private TableView<Loan> PendingLoanTableList;
    @FXML
    private TableColumn<Loan, String> colStudentId;
    @FXML
    private TableColumn<Loan, String> colStudentname;
    @FXML
    private TableColumn<Loan, String> colLoantype;
    @FXML
    private TableColumn<Loan, Double> colAmountGiven;
    @FXML
    private TableColumn<Loan, String> colCase;
    @FXML
    private TableColumn<Loan, JFXButton> colConfirmloan;
    
    
    
    
    @FXML
    private TableView<Loan> OnLoanTableView;
    @FXML
    private TableColumn<Loan, String> colLoanname;
    @FXML
    private TableColumn<Loan, String> colLoanId;
    @FXML
    private TableColumn<Loan, String> colOnLoanLoanType;
    @FXML
    private TableColumn<Loan, Double> colLoanAmount;
    @FXML
    private TableColumn<Loan, String> colLoanStatus;
    @FXML
    private TableColumn<Loan, JFXButton> colIncrement;
    @FXML
    private TableColumn<Loan, JFXButton> colLoanReturn;
    @FXML
    private TableColumn<Loan, String> colLoanCase;
        

    ObservableList<Loan> list = FXCollections.observableArrayList();
    ObservableList<Loan> list1 = FXCollections.observableArrayList();
    @FXML
    private JFXTextField FindStudent;
    
   
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Handler handler = Handler.getInstance();
        LoadDataPendingList();
        initCol();
        LoadDataOnLoanList();
        LoanInitCol();
        
        
        
    }    

    @FXML
    private void FindStudentAction(InputMethodEvent event) {
        
        FilteredList <Loan> filteredList = new FilteredList<>(list,p->true);  
            FindStudent.textProperty().addListener((ObservableValue,oldValue,newValue)->{
            filteredList.setPredicate((Predicate<? super Loan>) loan->{ 
                
               if (newValue == null || newValue.isEmpty()){
                        return true;
                    }  
                 String filterLowerCase = newValue.toLowerCase();
                 
               if ((loan.getStudentId()).toLowerCase().contains(filterLowerCase)){
                        return true;
                    }
               if (loan.getStudentname().toLowerCase().contains(filterLowerCase)){
                        return true;
                    }
              
               if (loan.getLoanReason().toLowerCase().contains(filterLowerCase)){
                        return true;
                    } 
               if (loan.getLoanType().toLowerCase().contains(filterLowerCase)){
                        return true;
                    }
                
               
                    return false;
                  });
                  SortedList<Loan> sortedData = new SortedList<>(filteredList); 
                  sortedData.comparatorProperty().bind(PendingLoanTableList.comparatorProperty());
                  PendingLoanTableList.getItems().setAll(sortedData);
            });
        
    }

    @FXML
    private void FindStudentAction(KeyEvent event) {
          FilteredList <Loan> filteredList = new FilteredList<>(list,p->true);  
            FindStudent.textProperty().addListener((ObservableValue,oldValue,newValue)->{
            filteredList.setPredicate((Predicate<? super Loan>) loan->{ 
                
               if (newValue == null || newValue.isEmpty()){
                        return true;
                    }  
                 String filterLowerCase = newValue.toLowerCase();
                 
               if ((loan.getStudentId()).toLowerCase().contains(filterLowerCase)){
                        return true;
                    }
               if (loan.getStudentname().toLowerCase().contains(filterLowerCase)){
                        return true;
                    }
              
               if (loan.getLoanReason().toLowerCase().contains(filterLowerCase)){
                        return true;
                    } 
               if (loan.getLoanType().toLowerCase().contains(filterLowerCase)){
                        return true;
                    }
                
               
                    return false;
                  });
                  SortedList<Loan> sortedData = new SortedList<>(filteredList); 
                  sortedData.comparatorProperty().bind(PendingLoanTableList.comparatorProperty());
                  PendingLoanTableList.getItems().setAll(sortedData);
            });
        
    }
    
    private void LoadDataPendingList()
    {
        Handler handler = new Handler();
        Loan loan = new Loan();
        String query = "SELECT * FROM Student";
        String query2 = "SELECT * FROM Application where IsOnLoan='0' and IsReturn='0' ";
        ResultSet rs = handler.execQuery(query);
        ResultSet rs1 = handler.execQuery(query2);
        try
        {
            list.clear();
            while(rs.next() && rs1.next())
            {
               String name = rs.getString("StudentName");
               String id =   rs1.getString("StudentId");
               String loanType = rs1.getString("LoanType");     
               Double amountgiven = rs1.getDouble("Amount");
               String loanReason = rs1.getString("LoanCase");                           
               System.out.println("Retrieved Loan Reason is " +loanReason);
              JFXButton confirm_loan = new JFXButton("Confirm");
              confirm_loan.setId(id);
              confirm_loan.setPrefWidth(100);
              confirm_loan.setPrefHeight(30);  
              confirm_loan.setContentDisplay(ContentDisplay.RIGHT);
              FontAwesomeIconView icon2 = new FontAwesomeIconView(FontAwesomeIcon.MONEY);
              confirm_loan.setStyle("-fx-text-fill: #ffffff");
              confirm_loan.setStyle("-fx-background-color: linear-gradient(to top right,#0159a1,#8ba4b9)");
              icon2.setGlyphSize(20);
              icon2.setStyle("-fx-fill: #ffffff");              
              confirm_loan.setGraphic(icon2);     
              confirm_loan.getId();
              confirm_loan.setOnAction(new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent event) {
                  Handler handler = new Handler();
                
                  System.out.println("The button id is" +confirm_loan);
                  
                  if(id==confirm_loan.getId())
                  {
//                      try {
                          
                          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                          alert.setHeaderText("Grant Loan");
                          alert.setContentText("Do You want To Grant This Student Loan?");
                          alert.showAndWait();
                          if(alert.getResult().getText().equals("OK"))
                          {
                          String update = "UPDATE Application SET IsOnLoan='1' WHERE StudentId='"+id+"'";
                          handler.execUpdate(update);
                          list.clear();
                          LoadDataPendingList();
                          Notification noti = new Notification(2, "Grant Section", "Loan Has been Granted");
                          noti.start();
                          }
                          else
                          {
                             Notification noti = new Notification(2, "Grant Section", "Loan Not Granted For now");
                             noti.start();
                          }
                  }
                  }
              });
            list.add(new Loan(name, id, loanType, amountgiven, loanReason, confirm_loan));
            }  
        }
        
        catch (SQLException ex)
        {
            
        }
        PendingLoanTableList.getItems().setAll(list);
        
    }

    private void LoadDataOnLoanList()
    {
        Handler handler = new Handler();
        Loan loan = new Loan();
        String query = "SELECT * FROM Student";
        String query2 = "SELECT * FROM Application where IsOnLoan='1' and IsReturn='0' ";
        ResultSet rs = handler.execQuery(query);
        ResultSet rs1 = handler.execQuery(query2);
        try
        {
            list1.clear();
            while(rs.next() && rs1.next())
            {
               String name = rs.getString("StudentName");
               String id =   rs1.getString("StudentId");
               String loanType = rs1.getString("LoanType");     
               Double amountgiven = rs1.getDouble("Amount");
               String loanReason = rs1.getString("LoanCase");  
               String Status = "Onloan";
               
              System.out.println("Retrieved Loan Reason is " +loanReason);
              
              JFXButton increment_loan = new JFXButton("Increment");
              increment_loan.setId(id);
              increment_loan.setPrefWidth(100);
              increment_loan.setPrefHeight(30);  
              increment_loan.setContentDisplay(ContentDisplay.RIGHT);
              FontAwesomeIconView icon2 = new FontAwesomeIconView(FontAwesomeIcon.MONEY);
              increment_loan.setStyle("-fx-text-fill: #ffffff");
              increment_loan.setStyle("-fx-background-color: linear-gradient(to top right,#0159a1,#8ba4b9)");
              icon2.setGlyphSize(20);
              icon2.setStyle("-fx-fill: #ffffff");              
              increment_loan.setGraphic(icon2);     
              increment_loan.getId();
             increment_loan.setOnAction(new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent event) {
                  Handler handler = new Handler();
                
                  System.out.println("The button id is" +increment_loan);
                  
                  if(id==increment_loan.getId())
                  {                          
                  try {
                          Parent root = FXMLLoader.load(getClass().getResource("/View/ReturnLaon.fxml"));
                          Scene scene = new Scene(root);
                          Stage stage = new Stage();
                          stage.setScene(scene);
                          stage.initStyle(StageStyle.DECORATED);
                          //ApplyForLoanController.StudentId.setText(id);
                          //stage.initStyle(StageStyle.TRANSPARENT);
                          //stage.setMaximized(true);
                          // stage.sizeToScene();
                          stage.show();
                      } catch (IOException ex) {
                          Logger.getLogger(StudentOnLoanController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }
                  }
              });
            
             
             
             JFXButton Return_loan = new JFXButton("Return");
              Return_loan.setId(id);
              Return_loan.setPrefWidth(100);
              Return_loan.setPrefHeight(30);  
              Return_loan.setContentDisplay(ContentDisplay.RIGHT);
              FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.MONEY);
              Return_loan.setStyle("-fx-text-fill: #ffffff");
              Return_loan.setStyle("-fx-background-color: linear-gradient(to top right,#0159a1,#8ba4b9)");
              icon.setGlyphSize(20);
              icon.setStyle("-fx-fill: #ffffff");              
              Return_loan.setGraphic(icon);     
              Return_loan.getId();
             Return_loan.setOnAction(new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent event) {
                  Handler handler = new Handler();
                
                  System.out.println("The button id is" +increment_loan);
                  
                  if(id==Return_loan.getId())
                  {                          
                  try {
                          Parent root = FXMLLoader.load(getClass().getResource("/View/ReturnLaon.fxml"));
                          Scene scene = new Scene(root);
                          Stage stage = new Stage();
                          stage.setScene(scene);
                          stage.initStyle(StageStyle.DECORATED);
                          ReturnLaonController.StudentId.setText(id);
                          ReturnLaonController.StudentId.disableProperty();
                          ReturnLaonController.LoanTopay.setText(Double.toString(amountgiven)); 
                          ReturnLaonController.LoanTopay.disableProperty();
                          //ApplyForLoanController.StudentId.setText(id);
                          //stage.initStyle(StageStyle.TRANSPARENT);
                          //stage.setMaximized(true);
                          // stage.sizeToScene();
                          stage.show();
                      } catch (IOException ex) {
                          Logger.getLogger(StudentOnLoanController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }
                  }
              });
            
             
             
              
             list1.add(new Loan(name, id, loanType, amountgiven, loanReason, Status, increment_loan, Return_loan));
             
            }  
        }
        
        catch (SQLException ex)
        {
            
        }
        OnLoanTableView.getItems().setAll(list1);
        
    }
    
    private void initCol(){

       
        colStudentname.setCellValueFactory(new PropertyValueFactory<>("Studentname"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        colLoantype.setCellValueFactory(new PropertyValueFactory<>("loanType"));
        colAmountGiven.setCellValueFactory(new PropertyValueFactory<>("LoanAmount"));
        colCase.setCellValueFactory(new PropertyValueFactory<>("LoanReason"));
        colConfirmloan.setCellValueFactory(new PropertyValueFactory<>("ConfirmLoan"));
       
        colConfirmloan.setMaxWidth(2500);

      
    }
    
    private void LoanInitCol()
    {
    
     
        colLoanname.setCellValueFactory(new PropertyValueFactory<>("Studentname"));
        colLoanId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        colOnLoanLoanType.setCellValueFactory(new PropertyValueFactory<>("loanType"));
        colLoanAmount.setCellValueFactory(new PropertyValueFactory<>("LoanAmount"));
        colLoanCase.setCellValueFactory(new PropertyValueFactory<>("LoanReason"));
        colLoanStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colIncrement.setCellValueFactory(new PropertyValueFactory<>("IncrementLoan"));      
        colLoanReturn.setCellValueFactory(new PropertyValueFactory<>("ReturnLoan"));
        
        
        colLoanReturn.setMaxWidth(3500);
        colIncrement.setMaxWidth(3500);
        colLoanname.setMaxWidth(8000);
        
    }

    @FXML
    private void RefreshTablePending(Event event) {
        list.clear();
        LoadDataPendingList();
    }

    @FXML
    private void RefreshTableLoan(Event event) {
        list1.clear();
        LoadDataOnLoanList();
    }
    
  
}
