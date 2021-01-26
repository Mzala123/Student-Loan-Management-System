/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.FileCopier;
import Model.Folder;
import Model.Handler;
import Model.Notification;
import Model.User;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;

/**
 * FXML Controller class
 *
 * @author bounce
 */
public class AdminSettingsController implements Initializable {

    @FXML
    private Pane pane;
    @FXML
    private JFXTextField userId;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField currentpassword;
    @FXML
    private JFXPasswordField newPassword;
    @FXML
    private Pane pane1;
    @FXML
    private JFXPasswordField confirmPassword;
    @FXML
    private JFXTextField fname;
    @FXML
    private JFXTextField sname;
    @FXML
    private JFXTextField email;
    @FXML
    private Pane pane2;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXTextArea ImagePath;
    @FXML
    private StackPane windowStackpane;
    
    
    
    public String pathname = "";
    private Image UserImage = null;
    private FileChooser filechooser;
    private File file;
    
    private ImageView imgProfile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JFXDepthManager.setDepth(pane, 1);
        JFXDepthManager.setDepth(pane1, 1);
        JFXDepthManager.setDepth(pane2, 1);
        SetValues();
        // TODO
    }    

    @FXML
    private void InsertNewImage(ActionEvent event) {
        pathname = "";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif"));
        file = fileChooser.showOpenDialog(ImagePath.getScene().getWindow());

        if (file != null) {

            try {
                pathname = file.getAbsolutePath();
                ImagePath.setText(file.toURI().toURL().toString());

                Image image = null;
                try {
                    image = new Image(file.toURI().toURL().toString());
                } catch (MalformedURLException malformedURLException) {
                }
                imageView.setImage(image);
                imageView.setFitWidth(300);
                imageView.setFitHeight(300);
            } catch (MalformedURLException ex) {
                Logger.getLogger(AdminSettingsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ImagePath.setText("Image file selection cancelled.");
        }

    }

    @FXML
    private void UpDateAction(ActionEvent event) {
        Handler handler = new Handler();
        User user = new User("","", "", "", "", "", "", "","");
        String fname = this.fname.getText();
        String new_pass = newPassword.getText();
        String confirm_pass = confirmPassword.getText();
        String lname = sname.getText();
        String mail = email.getText();
        String uname = username.getText();
        String id = userId.getText();
        String path = ImagePath.getText();
        String current_p = currentpassword.getText();
        
         Boolean updated = false;

        if (path.isEmpty()) {
            Notification notification = new Notification(2, "Information entry error",
                    "Please makes sure Profile Picture is selected", "error");
            notification.start();
        }
        else {
            Folder folder = new Folder();
            folder.createFolders("Loan\\UserProfile\\");
            FileCopier copier = new FileCopier(id, "UserProfile", pathname);
            copier.copyUserFile();
            updated = true;
        }
          
       String extension = FilenameUtils.getExtension(pathname);
       String new_image_path = System.getProperty("user.home") + "\\Documents\\Loan\\UserProfile\\" + id + "." + extension;        
       String replace = new_image_path.replace("\\", "@");
       user.setImage(replace);
       System.out.println("----->"+replace);
     
       
           if (confirm_pass.isEmpty() & new_pass.isEmpty()) {

            String query = "UPDATE User SET UserName='" + uname + "', Firstname='" + fname + "',"
                    + "Lastname='" + lname + "', Email='" + mail + "',Image='" + replace + "', Password='"
                    + current_p + "' WHERE UserId='" + id + "'";

            System.out.println(query);

            if (handler.execAction(query)) {
                Notification notify = new Notification(1, "Admin Settings ", "Admin details updated successfully");
                notify.start();
            } else {
                Notification notify = new Notification(1, "Admin Settings ", "Failed To Update Admin Details");
                notify.start();
            }
        }
           
           else if (confirm_pass.equals(new_pass) && !new_pass.trim().isEmpty() && !confirm_pass.trim().isEmpty() ) {
            String query = "UPDATE User SET UserName='" + uname + "', Firstname='" + fname + "',"
                    + "Lastname='" + lname + "', Email='" + mail + "',Image='" + replace + "', Password='"
                    + confirm_pass + "' WHERE UserId='" + id + "'";

            System.out.println(query);

            if (handler.execAction(query)) {
                Notification notify = new Notification(1, "Admin Settings ", "Admin details updated successfully");
                notify.start();
            } else {
                Notification notify = new Notification(1, "Admin Settings ", "Failed To Update Admin Details");
                notify.start();
            }

        } else {
            Notification notify = new Notification(1, "Admin Settings ", "Password Error during Confirmation..Check Spellings");
            notify.start();
        }
           
        
    }

    @FXML
    private void CloseWindow(ActionEvent event) {
        windowStackpane.getScene().getWindow().hide();
    }
    
   private void SetValues()
   {
        Handler handler = new Handler();
        String query1 = "Select * FROM User WHERE UserName='" + LoginFormController.Username +
                "' AND Password='"+LoginFormController.pass+"';";
        ResultSet rs = handler.execQuery(query1);
        try
        {
           while(rs.next())
           {
                String fname = rs.getString("Firstname");
                String lname = rs.getString("Lastname");
                String id = rs.getString("UserId");
                String uname = rs.getString("UserName");
                String password = rs.getString("Password");
                String mail = rs.getString("Email");
                String image = rs.getString("Image");
                
               userId.setText(id);
               username.setText(uname);
               currentpassword.setText(password);
               this.fname.setText(fname);
               sname.setText(lname);
               email.setText(mail);
               ImagePath.setText(image);
               
               
               String replace = image.replace("@", "\\");
                System.out.println(image);
                System.out.println(replace);
                file = new File(replace);
                if(file.exists()){
                    Image image1 = new Image(file.toURI().toString(), 300, 300, false,true);
                //rectangle.setFill(new ImagePattern(image1));
                imageView.setImage(image1);
                imageView.setFitHeight(300);
                imageView.setFitWidth(300);
                }
           }
        }
        catch(SQLException ex)
        {
            
        }
   }
   
}
