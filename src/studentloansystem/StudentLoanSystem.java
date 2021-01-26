/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentloansystem;

import Model.Folder;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author bounce
 */
public class StudentLoanSystem extends Application {
     public static Stage primary_stage;
    @Override
    public void start(Stage primaryStage) {
        primary_stage =   primaryStage; 
       Folder folder = new Folder();
       folder.createFolders("Loan");
       folder.createFolders("Loan\\StudentProfile");
       folder.createFolders("Loan\\UserProfile");
       folder.createFolders("Loan\\Reports");   
       primary_stage.initStyle(StageStyle.UNDECORATED);
       
       
       
       
        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/View/LoginForm.fxml"));
                        } catch (IOException ex) {
                          //  Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.DECORATED);
                    //stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setMaximized(true);
                   // stage.sizeToScene();
                    stage.show();
       

                  


           }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
