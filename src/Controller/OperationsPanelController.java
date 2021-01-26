/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Constants;
import Model.Handler;
import Model.Notification;
import Model.PopWindow;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author bounce
 */
public class OperationsPanelController implements Initializable {

    @FXML
    private StackPane MainStackpane;
    @FXML
    private StackPane middle_stackpane;
    @FXML
    private Label labelLoggedInUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        TakeUsername();
        SwitchCenterPane(Constants.DashBoard);
      
    }    

    public void TakeUsername()
    {
        Handler handler = new Handler();
        String query1 = "Select * FROM User WHERE UserName='" + LoginFormController.Username + "';";
        ResultSet rs = handler.execQuery(query1);
        try {
            while (rs.next()) {
                String fname = rs.getString("Firstname");
                String lname = rs.getString("Lastname");
                labelLoggedInUser.setText(fname + " " + lname);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperationsPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void DashBoardSection(ActionEvent event) {
        SwitchCenterPane(Constants.DashBoard);
    }
    
    @FXML
    private void AddStudentDetails(ActionEvent event) {
        SwitchCenterPane(Constants.AddStudents);
        
    }
    

    @FXML
    private void SwitchToStudentsRegistered(ActionEvent event) {
        SwitchCenterPane(Constants.StudentList);
    }

    @FXML
    private void SwitchToStudentsOnLoan(ActionEvent event) {
         SwitchCenterPane(Constants.OnLoanList);
    }

    @FXML
    private void UserSettingsAction(ActionEvent event) {
        SwitchCenterPane(Constants.ReturnList);
    }

    @FXML
    private void LogOutAction(ActionEvent event) {
        PopWindow window = new PopWindow();
        if (PopWindow.primaryStage.isMaximized())
        {
        window.loadWindow("/View/LoginForm.fxml", "",true,true,false,false);
        }
        else
        {
         window.loadWindow("/View/LoginForm.fxml", "",true,true,false,false);
        }
        MainStackpane.getScene().getWindow().hide();
        Notification notify = new Notification(3, "Login Section", "Signed Out");
        notify.start();
    }

    @FXML
    private void ExitSystemAction(ActionEvent event) {
        System.exit(0);  
    }

  private void SwitchCenterPane(String pane) {
      
       middle_stackpane.getChildren().clear();
        try {
            StackPane pane2 = FXMLLoader.load(getClass().getResource(pane));
            ObservableList<Node> elements = pane2.getChildren();
            middle_stackpane.getChildren().setAll(elements);

        } catch (IOException ex) {
            Logger.getLogger(OperationsPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void GoToMenuPanel(ActionEvent event) {
        PopWindow window = new PopWindow();  
        window.loadWindow("/View/OperationsMenu.fxml", "",true,true,false,false);
        MainStackpane.getScene().getWindow().hide();
    }
}
