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
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import org.apache.commons.io.FilenameUtils;

/**
 * FXML Controller class
 *
 * @author bounce
 */
public class CreateUsersController implements Initializable {

    @FXML
    private JFXTextField userid;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField fname;
    @FXML
    private JFXTextField sname;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXComboBox<String> usertype;
    @FXML
    private JFXDatePicker date;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXTextArea filepath;
    ObservableList<String> list = FXCollections.observableArrayList();
    
    
    public String pathname="";
    private Image UserImage;
    private FileChooser filechooser;
    private File file;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Handler handler = Handler.getInstance();
        list.addAll("Admin", "Accountant", "Registrar");
        usertype.getItems().addAll(list);
        // TODO
    }    

    @FXML
    private void InsertImageAction(ActionEvent event) {
        pathname = "";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif"));
        file = fileChooser.showOpenDialog(filepath.getScene().getWindow());

        if (file != null) {

            try {
                pathname = file.getAbsolutePath();
                filepath.setText(file.toURI().toURL().toString());

                Image image = null;
                try {
                    image = new Image(file.toURI().toURL().toString(), 300, 300, false, true);
                } catch (MalformedURLException malformedURLException) {
                }
                imageView.setImage(image);
            } catch (MalformedURLException ex) {
                Logger.getLogger(CreateStudentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            filepath.setText("Image file selection cancelled.");
        }
         
    }

    @FXML
    private void RegisterUserAction(ActionEvent event) {
        User user = new User("","", "", "", "", "", "", "","");
        Handler handler = new Handler();
        String username = this.username.getText();
        user.setUsername(username);
        String userid = this.userid.getText();
        user.setUserId(userid);
        String fname = this.fname.getText();
        user.setFname(fname);
        String sname = this.sname.getText();
        user.setLname(sname);
        String password = this.password.getText();
        user.setPassword(password);
        String date = this.date.getValue().toString();
        user.setDateIncluded(date);
        String usertype = this.usertype.getValue().toString();
        user.setUserType(usertype);
        String email = this.email.getText();
        user.setEmail(email);
        Boolean updated;
        
        if(username.isEmpty() || userid.isEmpty() || email.isEmpty() || usertype.isEmpty() || date.isEmpty() )
        {
            Notification noti = new Notification(5,"Register User", "Please Fill In the Required Field");
            noti.start();
        }
          if (filepath.getText().isEmpty()) {
            Notification notification = new Notification(2, "Information entry error",
                    "Please makes sure Profile Picture is selected", "error");
            notification.start();

        } else {
            Folder folder = new Folder();
            folder.createFolders("Loan\\UserProfile\\");
            FileCopier copier = new FileCopier(userid, "UserProfile", pathname);
            copier.copyUserFile();
            updated = true;
        }
          
       String extension = FilenameUtils.getExtension(pathname);
       String new_image_path = System.getProperty("user.home") + "\\Documents\\Loan\\UserProfile\\" + userid + "." + extension;        
       String replace = new_image_path.replace("\\", "@");
       user.setImage(replace);
       System.out.println("----->"+replace);
                   
        if(handler.execAction(user.InserUser()))
        {
            Notification noti = new Notification(5,"Register User Section", "Registration Sucessfull");
            noti.start();
        }
        else
        {
            Notification noti = new Notification(5,"Register User", "Registration Unsuccessful, Try Again!");
            noti.start();
        }
        
    }

    @FXML
    private void CancelUserRegistration(ActionEvent event) {
    userid.clear();
    username.clear();
    fname.clear();
    sname.clear();
    password.clear();
    email.clear();
    imageView.setImage(null);
    filepath.clear();
    }
    
}
