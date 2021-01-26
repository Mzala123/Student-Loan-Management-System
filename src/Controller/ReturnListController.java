/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Handler;
import Model.Loan;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author bounce
 */
public class ReturnListController implements Initializable {

    @FXML
    private JFXTextField findStudent;
    @FXML
    private TableView<Loan> ReturnTableList;
    @FXML
    private TableColumn<Loan, String> colStudentname;
    @FXML
    private TableColumn<Loan, String> colstudentId;
    @FXML
    private TableColumn<Loan, String> colLoantype;
    @FXML
    private TableColumn<Loan, String> ColReason;

    
    
     ObservableList<Loan> list = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Handler handler = Handler.getInstance();
        LoadTable();
        initCol();
        // TODO
    }    

    @FXML
    private void FindStudentReturnAction(ActionEvent event) {
        FilteredList <Loan> filteredList = new FilteredList<>(list,p->true);  
            findStudent.textProperty().addListener((ObservableValue,oldValue,newValue)->{
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
                  sortedData.comparatorProperty().bind(ReturnTableList.comparatorProperty());
                  ReturnTableList.getItems().setAll(sortedData);
            });
        
    }
    
    private void LoadTable()
    {
        Handler handler = new Handler();
        Loan loan = new Loan();
        String query = "SELECT * FROM Student";
        String query2 = "SELECT * FROM Application where IsReturn='1' ";
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
               String loanReason = rs1.getString("LoanCase");                           
               System.out.println("Retrieved Loan Reason is " +loanReason);
               
               list.add(new Loan(name, id, loanType, loanReason));
            }
        }
        catch(SQLException ex)
        {
            
        }
        ReturnTableList.getItems().setAll(list);
    }
    
    
    
    private void initCol()
    {
        colStudentname.setCellValueFactory(new PropertyValueFactory<>("Studentname"));
        colstudentId.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        colLoantype.setCellValueFactory(new PropertyValueFactory<>("loanType"));
        ColReason.setCellValueFactory(new PropertyValueFactory<>("LoanReason"));
        
          
    }

    @FXML
    private void FindStudentAction(KeyEvent event) {
        FilteredList <Loan> filteredList = new FilteredList<>(list,p->true);  
            findStudent.textProperty().addListener((ObservableValue,oldValue,newValue)->{
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
                  sortedData.comparatorProperty().bind(ReturnTableList.comparatorProperty());
                  ReturnTableList.getItems().setAll(sortedData);
            });
        
    }

    @FXML
    private void FindReturnAction(KeyEvent event) {
        FilteredList <Loan> filteredList = new FilteredList<>(list,p->true);  
            findStudent.textProperty().addListener((ObservableValue,oldValue,newValue)->{
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
                  sortedData.comparatorProperty().bind(ReturnTableList.comparatorProperty());
                  ReturnTableList.getItems().setAll(sortedData);
            });
        
    }
}
