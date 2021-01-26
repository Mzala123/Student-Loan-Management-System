/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Handler;
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
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author bounce
 */
public class StudentListController implements Initializable {

    @FXML
    private TableView<Student> TableStudentList;
    @FXML
    private TableColumn<Student, String> colStudentId;
    @FXML
    private TableColumn<Student, String> colStudentName;
    @FXML
    private TableColumn<Student, String> colProgramme;
    @FXML
    private TableColumn<Student, String> colYearStudy;
    @FXML
    private TableColumn<Student, JFXButton> ColEdit;
    @FXML
    private TableColumn<Student, JFXButton> ColDelete;
    @FXML
    private TableColumn<Student, JFXButton> ColApplyLoan;
    
    
    ObservableList<Student> list = FXCollections.observableArrayList();
   FilteredList<Student> filteredList = new FilteredList<>(list,e->true);
    @FXML
    private JFXTextField FindStudent;
    
    private SimpleIntegerProperty index = new SimpleIntegerProperty();
    @FXML
    private TableColumn<Student, String> colGender;
    
    
     public static String ID="";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Handler handler = Handler.getInstance();
        Student student = new Student();
        String id="";
        ShowStudentListAction();
        initCol();
    //    ShowStudentListAction();
//        createDelBtn(id);
        
    }    

    @FXML
    private void RefreshTable(ActionEvent event) {
        ShowStudentListAction();
    }

    @FXML
    private void FindStudentAction(ActionEvent event) {
        
   
    }
    
    
    private void ShowStudentListAction()
    {
         
        Handler handler = new Handler();
        Student student = new Student();
        String query = "SELECT * FROM Student";
        ResultSet rs = handler.execQuery(query);
        try
        {
            list.clear();
            while(rs.next())
            {
              String id = rs.getString("StudentId");
              
              String name = rs.getString("StudentName");
              String Gender = rs.getString("Gender");
              String programme = rs.getString("Programme");
              String YearStudy = rs.getString("YearOfStudy");
              
              
              JFXButton update = new JFXButton("Edit");
              update.setId(id);
              update.setPrefWidth(100);
              update.setPrefHeight(35);  
              update.setContentDisplay(ContentDisplay.RIGHT);
              FontAwesomeIconView icon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE_ALT);
              update.setStyle("-fx-background-color: linear-gradient(to top right,#0159a1,#8ba4b9)");
              icon.setGlyphSize(20);
              icon.setStyle("-fx-fill: #ffffff");
              update.setGraphic(icon);
              update.getId();
              update.setOnAction(new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent event) {
                  Handler handler = new Handler();
                  Student student = new Student();
                  System.out.println("The button id is" +update);
                  if(id==update.getId())
                  {
                      try {
                          Parent root = FXMLLoader.load(getClass().getResource("/View/EditStudentDetails.fxml"));
                          Scene scene = new Scene(root);
                          Stage stage = new Stage();
                          stage.setScene(scene);
                          stage.initStyle(StageStyle.DECORATED);
                          EditStudentDetailsController.StudentId.setText(id);
                          ID=id;
                          System.out.println("The set id is:" +ID);
                          
                         // EditStudentDetailsController.id.;
                          //stage.initStyle(StageStyle.TRANSPARENT);
                          //stage.setMaximized(true);
                          // stage.sizeToScene();
                          stage.show();
                      } catch (IOException ex) {
                          Logger.getLogger(StudentListController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }
                  }
              });
              
              JFXButton archive = new JFXButton("Archive");
              archive.setId(id);
              //id = archive.getText();         
              archive.setPrefWidth(100);
              archive.setPrefHeight(35);  
              archive.setContentDisplay(ContentDisplay.RIGHT);
              FontAwesomeIconView icon1 = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
              archive.setStyle("-fx-background-color: linear-gradient(to top right,#b12020,#ea9084)");
              icon1.setGlyphSize(20);
              icon1.setStyle("-fx-fill: #ffffff");
              archive.setGraphic(icon1);
              archive.getId();    
              archive.setOnAction(new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent event) {
                      Handler handler = new Handler();
                      Student student = new Student();
                      System.out.println(archive);
                      if(id==archive.getId())
                      {
                           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                           alert.setHeaderText("Deletion Message");
                           alert.setContentText("Student Details Sent to Archive");
                           alert.showAndWait();
                           if(alert.getResult().getText().equals("Cancel"))
                         {
                   
//                          handler.execUpdate(student.DeleteStudentDetails(id));
//                          list.clear();
//                          initCol();
//                          ShowStudentListAction(); 
                          Notification notify = new Notification(2, "STUDENT LIST VIEW", "FAILED TO DELETE DETAILS");
                          notify.start();
//                              Alert alert2 = new Alert(Alert.AlertType.ERROR);
//                              alert2.setHeaderText("Deletion Error");
//                              alert2.setContentText("Failed To Archive Student Record");
//                              alert2.showAndWait();
                         }
                           else{
                          handler.execUpdate(student.DeleteStudentDetails(id));
                          list.clear();
                          initCol();
                          ShowStudentListAction(); 
                          Notification notify = new Notification(2, "STUDENT LIST VIEW", "DETAILS ARCHIVED SUCCESSFULLY");
                          notify.start();
//                              Alert alert2 = new Alert(Alert.AlertType.ERROR);
//                              alert2.setHeaderText("Deletion Error");
//                              alert2.setContentText("Failed To Archive Student Record");
//                              alert2.showAndWait();
                           }
                      }

                  }
              });
                         
              JFXButton apply_loan = new JFXButton("Loan");
              apply_loan.setId(id);
              apply_loan.setPrefWidth(100);
              apply_loan.setPrefHeight(35);  
              apply_loan.setContentDisplay(ContentDisplay.RIGHT);
              FontAwesomeIconView icon2 = new FontAwesomeIconView(FontAwesomeIcon.MONEY);
              apply_loan.setStyle("-fx-text-fill: #ffffff");
              apply_loan.setStyle("-fx-background-color: linear-gradient(to top right,#0aaa6e,#bdf1dd)");
              icon2.setGlyphSize(20);
              icon2.setStyle("-fx-fill: #ffffff");              
              apply_loan.setGraphic(icon2);     
              apply_loan.setOnAction(new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent event) {
                  Handler handler = new Handler();
                  Student student = new Student();
                  System.out.println("The button id is" +apply_loan);
                  
                  if(id==apply_loan.getId())
                  {
                      try {
                          Parent root = FXMLLoader.load(getClass().getResource("/View/ApplyForLoan.fxml"));
                          Scene scene = new Scene(root);
                          Stage stage = new Stage();
                          stage.setScene(scene);
                          stage.initStyle(StageStyle.DECORATED);
                          ApplyForLoanController.StudentId.setText(id);
                          
                          //stage.initStyle(StageStyle.TRANSPARENT);
                          //stage.setMaximized(true);
                          // stage.sizeToScene();
                          stage.show();
                      } catch (IOException ex) {
                          Logger.getLogger(StudentListController.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  }
                  }
              });
              
              
              
              list.add(new Student(id, name,Gender, programme, YearStudy, 
                     update, apply_loan, archive));
            }
        }
        catch(SQLException ex)
        {
            
        }
        TableStudentList.getItems().setAll(list);
      
        //              archive.setOnAction((event) -> {
//                  //TableStudentList.getItems().remove(0);
//                 Student student = TableStudentList.getItems().get(0);
//                 TableStudentList.getSelectionModel().getTableView();
//                      
//              });  
    }
    
     private void initCol(){

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("StudentName"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        colProgramme.setCellValueFactory(new PropertyValueFactory<>("Programme"));
        colYearStudy.setCellValueFactory(new PropertyValueFactory<>("YearOfStudy"));
        ColEdit.setCellValueFactory(new PropertyValueFactory<>("Update"));
        ColDelete.setCellValueFactory(new PropertyValueFactory<>("Archive"));
        ColApplyLoan.setCellValueFactory(new PropertyValueFactory<>("Apply_loan"));
      
        ColEdit.setMaxWidth(4000);
        ColDelete.setMaxWidth(3600);
        ColApplyLoan.setMaxWidth(3600);
        colStudentId.setMaxWidth(3600);
        colStudentName.setMaxWidth(8000);
        colYearStudy.setMaxWidth(10000);
        colGender.setMaxWidth(3000);
      
    }

    @FXML
    private void FindStudentAction(InputMethodEvent event) {
        FilteredList <Student> filteredList = new FilteredList<>(list,p->true);  
            FindStudent.textProperty().addListener((ObservableValue,oldValue,newValue)->{
            filteredList.setPredicate((Predicate<? super Student>) student->{ 
                
               if (newValue == null || newValue.isEmpty()){
                        return true;
                    }  
                 String filterLowerCase = newValue.toLowerCase();
                 
               if ((student.getStudentId()).toLowerCase().contains(filterLowerCase)){
                        return true;
                    }
               if (student.getStudentName().toLowerCase().contains(filterLowerCase)){
                        return true;
                    }
               if (student.getProgramme().toLowerCase().contains(filterLowerCase)){
                        return true;
                    }
               if (student.getGender().toLowerCase().contains(filterLowerCase)){
                        return true;
                    } 
               if (student.getYearOfStudy().toLowerCase().contains(filterLowerCase)){
                        return true;
                    }
                
               
                    return false;
                  });
                  SortedList<Student> sortedData = new SortedList<>(filteredList); 
                  sortedData.comparatorProperty().bind(TableStudentList.comparatorProperty());
                  TableStudentList.getItems().setAll(sortedData);
            });

    }

    @FXML
    private void FindStudentAction(KeyEvent event) {
        FilteredList <Student> filteredList = new FilteredList<>(list,p->true);  
            FindStudent.textProperty().addListener((ObservableValue,oldValue,newValue)->{
            filteredList.setPredicate((Predicate<? super Student>) student->{ 
                
               if (newValue == null || newValue.isEmpty()){
                        return true;
                    }  
                 String filterLowerCase = newValue.toLowerCase();
                 
               if ((student.getStudentId()).toLowerCase().contains(filterLowerCase)){
                        return true;
                    }
               if (student.getStudentName().toLowerCase().contains(filterLowerCase)){
                        return true;
                    }
               if (student.getProgramme().toLowerCase().contains(filterLowerCase)){
                        return true;
                    }
               if (student.getYearOfStudy().toLowerCase().contains(filterLowerCase)){
                        return true;
                    }
              if (student.getGender().toLowerCase().contains(filterLowerCase)){
                        return true;
                    } 
               
                    return false;
                  });
                  SortedList<Student> sortedData = new SortedList<>(filteredList); 
                  sortedData.comparatorProperty().bind(TableStudentList.comparatorProperty());
                  TableStudentList.getItems().setAll(sortedData);
            });

    }
   
//              archive.setOnAction(new EventHandler<ActionEvent>() {
//                  @Override
//                  public void handle(ActionEvent event) {
//                      Handler handler = new Handler();
//                      Student student = new Student();
//                      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                      alert.setHeaderText("Deletion Message");
//                      alert.setContentText("Student Details Sent to Archive");
//                      alert.showAndWait();
//                      if(alert.getResult().getText()!=null && alert.getResult().getText().equals("Ok"))
//                      {
//                          if(handler.execUpdate(student.DeleteStudentDetails(id)))
//                          {
//                              list.clear();
//                              initCol();
//                              ShowStudentListAction();
//                              
//                          }
//                          else{
//                              Alert alert2 = new Alert(Alert.AlertType.ERROR);
//                              alert2.setHeaderText("Deletion Error");
//                              alert2.setContentText("Failed To Archive Student Record");
//                              alert2.showAndWait();
//                          }
//                      }
//                  }
//              });
//                
     
     
}
