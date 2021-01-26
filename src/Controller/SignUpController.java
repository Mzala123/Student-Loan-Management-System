/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Handler;
import Model.Notification;
import Model.User;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author bounce
 */
public class SignUpController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXComboBox<String> userTypeCombo;
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private JFXPasswordField confirm_password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Handler handler = Handler.getInstance();
        list.addAll("Admin");
        userTypeCombo.getItems().addAll(list);
        // TODO
    }    

    @FXML
    private void RegisterAdmin(ActionEvent event) {
        Handler handler = new Handler();
        User user = new User("","", "","","","","","","");
        String username = this.username.getText();
        user.setUsername(username);
        String email = this.email.getText();
        user.setEmail(email);
        String password = this.password.getText();
        user.setPassword(password);
        String utype = userTypeCombo.getValue().toString();
        user.setUserType(utype);
        String password2 = this.confirm_password.getText();
        
        if(username.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty() || utype.isEmpty())
        {
            Notification notification = new Notification(5, "SignUp Panel", "Please Fill in the required fields");
            notification.start();
        }
        if(!password.equals(password2))
        {
            Notification notification = new Notification(5, "SignUp Panel", "Make Sure the passwords match");
            notification.start();
        }
        Handler Query = new Handler();
        
        try {
            String query = "select count(*) as no_of_rows FROM User";
            ResultSet rs = Query.execQuery(query);
            
            if(rs.next())
            {
               int found = rs.getInt("no_of_rows");
               System.out.println("Rows found ahahaha" +found); 
               if(found == 0)
               {
                   handler.execAction(user.InserUser());
                   Notification notification = new Notification(5, "SignUp Panel", "Admin Registered Successfully");
                   notification.start();
               }
               else
            {
                Notification notification = new Notification(5, "SignUp Panel", "You're Restricted To Create One Admin Account");
                notification.start();
            }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
