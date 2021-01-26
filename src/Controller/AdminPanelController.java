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
import static java.lang.System.exit;
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
public class AdminPanelController implements Initializable {

    @FXML
    private StackPane switch_stackpane;
    @FXML
    private StackPane stackpane;
    @FXML
    private Label labelUsername;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PopWindow window = new PopWindow();
        SwitchCenterPane(Constants.RegisterUserPanel);
        TakeUsername();
    }   
     

    @FXML
    private void DashBoard(ActionEvent event) {
        
    }

    @FXML
    private void SwitchToCreateUser(ActionEvent event) {
        SwitchCenterPane(Constants.RegisterUserPanel);
        
    }

    @FXML
    private void SwitchToAdminSection(ActionEvent event) {
        SwitchCenterPane(Constants.AdminSettings);
    }

    @FXML
    private void SwitchToHelpSupport(ActionEvent event) {
        
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
        stackpane.getScene().getWindow().hide();
        Notification notify = new Notification(3, "Login Section", "Signed Out");
        notify.start();
    }

    @FXML
    private void ExitSystemAction(ActionEvent event) {
        //exit(0);
    }
    
  private void SwitchCenterPane(String pane) {
      
       switch_stackpane.getChildren().clear();
        try {
            StackPane pane2 = FXMLLoader.load(getClass().getResource(pane));
            ObservableList<Node> elements = pane2.getChildren();
            switch_stackpane.getChildren().setAll(elements);

        } catch (IOException ex) {
            Logger.getLogger(AdminPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                labelUsername.setText(fname + " " + lname);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperationsPanelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
}
    