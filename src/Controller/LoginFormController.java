/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Handler;
import Model.Notification;
import Model.PopWindow;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bounce
 */
public class LoginFormController implements Initializable {

    @FXML
    private JFXHamburger ham;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private Label label_prompt;
    @FXML
    private StackPane stackpane;
    
    public static String Username;
    public static String pass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Handler handler = Handler.getInstance();
        InitDrawer();
        // TODO
    }  
    
    
    private void InitDrawer() {
       
         drawer.setDefaultDrawerSize(300);
        try {
            System.out.println("Opened successfully");
            VBox sideMenu = FXMLLoader.load(getClass().getResource("/View/SignUp.fxml"));
            System.out.println("yadutsa successfully");
            drawer.setSidePane(sideMenu);
            HamburgerBackArrowBasicTransition transition
                    = new HamburgerBackArrowBasicTransition(ham);
            transition.setRate(-1);
            ham.addEventHandler(MouseEvent.MOUSE_CLICKED, (Event event) -> {
                transition.setRate(transition.getRate()*-1);
                transition.play();
                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
           System.out.println("Wooow Oops Your A Machine I appreciate You");
            });

        } 
        catch (IOException ex) {
           Logger.getLogger(LoginFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EnterOnLogin(ActionEvent event) {
        LogIntoTheSystem(event);
    }

    @FXML
    private void LogIntoTheSystem(ActionEvent event) {
        
        PopWindow window = new PopWindow();           
        String username = this.username.getText();
        String password = this.password.getText();
        Username=this.username.getText();
        pass = this.password.getText();
       
        
        if (username.isEmpty() || password.isEmpty())
        {
            Notification noti = new Notification(2, "Login Section", "Please Fill in both Fields");
            noti.start();
        }
        else
        {
            try{
                   Handler handler = new Handler();
                   String  query1 = "SELECT * from User";
                        ResultSet rs1 = handler.execQuery(query1);
                        while(rs1.next())
                        {
                             if(username.equals(rs1.getString("UserName")) && password.equals(rs1.getString("Password")))
                             {
                                 System.out.println("Zukhala buanj kodi"); 
                                 String usertype = rs1.getString("Usertype");
                                 System.out.println("The usertype is "+usertype);
                                 usertype.toLowerCase();
                                 if(usertype.toLowerCase().equals("admin"))
                                 {
                                     window.loadWindow("/View/AdminPanel.fxml", "",true,true,false,false);
                                     stackpane.getScene().getWindow().hide();
//                                     Notification notify = new Notification(2, "User Section", "Welcome To User Section");
//                                     notify.start();
                                     System.out.println("Talowa ku admin panel man aaa");
                                 }
                                 else if(usertype.toLowerCase().equals("registrar")){
                                     window.loadWindow("/View/OperationsMenu.fxml", "",true,true,false,false);
                                     stackpane.getScene().getWindow().hide();
                                 }
                                 else if(usertype.toLowerCase().equals("accountant"))
                                 {
                                    window.loadWindow("/View/AdminPanel.fxml", "",true,true,false,false);
                                    stackpane.getScene().getWindow().hide(); 
                                 }
              }
                             else if(!username.equals(rs1.getString("UserName")) || !password.equals(rs1.getString("Password")))
                             {
                                 label_prompt.setText("Access Denied!");
//                                 Notification notify = new Notification(5, "Login Section", "Please Set Correct Credentials");
//                                 notify.start();
                                
                             }
                        }
                                  
            }
            catch(SQLException ex)
                    {
                        
                    }
        }
        
    }
    
}
