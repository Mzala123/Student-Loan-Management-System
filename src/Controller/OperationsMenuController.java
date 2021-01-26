/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Notification;
import Model.PopWindow;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author bounce
 */
public class OperationsMenuController implements Initializable {

    @FXML
    private StackPane stackPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GoToHomePage(ActionEvent event) {
        PopWindow window = new PopWindow();  
        window.loadWindow("/View/OperationsPanel.fxml", "",true,true,false,false);
        stackPane.getScene().getWindow().hide();
    }

    @FXML
    private void GoToUserSettings(ActionEvent event) {
        PopWindow window = new PopWindow();  
        window.loadWindow("/View/OperationSettings.fxml", "",true,true,false,false);
        stackPane.getScene().getWindow().hide();
    }

    @FXML
    private void GotoReportSection(ActionEvent event) {
    }

    @FXML
    private void GoToHelpSection(ActionEvent event) {
        PopWindow window = new PopWindow();  
        window.loadWindow("/View/HelpSection.fxml", "",true,true,false,false);
        stackPane.getScene().getWindow().hide();
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
        stackPane.getScene().getWindow().hide();
        Notification notify = new Notification(3, "Login Section", "Signed Out");
        notify.start();
    }
    
}
