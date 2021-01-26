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
import Model.Student;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author bounce
 */
public class CreateStudentController implements Initializable {

    @FXML
    private StackPane stackpane;
    @FXML
    private JFXTextField studentId;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField phonenumber;
    @FXML
    private JFXTextField studentname;
    @FXML
    private JFXComboBox<Integer> enrolyear;
    @FXML
    private JFXComboBox<String> studyyear;
    @FXML
    private JFXComboBox<Integer> duration;
    @FXML
    private JFXComboBox<String> programme;
    
    @FXML
    private JFXTextArea imagepath;
    
    ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<String> list1 = FXCollections.observableArrayList();
    ObservableList<String> list2 = FXCollections.observableArrayList();
    ObservableList<String> list3 = FXCollections.observableArrayList();
   
    
    public String pathname="";
    private Image UserImage;
    private FileChooser filechooser;
    private File file;
    @FXML
    private ImageView imageView;
    @FXML
    private Pane pane;
    @FXML
    private JFXComboBox<String> gender;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Handler handler = Handler.getInstance();
        InitEnrolYearCombo();
        list.addAll("First Year", "Second Year", "Third Year", "Fourth Year", "Fifth Year");
        studyyear.getItems().addAll(list);
        list1.addAll("BSc in Information and Communication Technology",
                "BSc in Computer Science", "BSc in Nursing and MidWifery", "Bsc in MBBS", "BSc In Clinical Medicine");
        programme.getItems().addAll(list1);
        list2.addAll("Male", "Female");
        gender.getItems().addAll(list2);
        Duration();
        JFXDepthManager.setDepth(pane, 1);
           
    }   
    
     private void Programme()
     {
      JFXComboBox main = new JFXComboBox();
      main.getItems().addAll("Faculty of Engeneering");
     
      JFXComboBox submenu = new JFXComboBox();
      submenu.getItems().addAll("BSc In Computer Science");
      main.getItems().add(submenu);
      programme.getItems();
     
     }
    
     private void Duration()
    {
     
     
     for(int count=3; count<=6; count++)
        {
          duration.getItems().addAll(count);
  
        }
     
    }
    
    private void InitEnrolYearCombo()
    {
     String date = LocalDateTime.now().toString();
     System.out.println(date);
     System.out.println(date.substring(0, 4));
     
     for(int count=Integer.parseInt(date.substring(0, 4)); count>1999; count--)
        {
          enrolyear.getItems().addAll(count);
  
        }
     
    }

    @FXML
    private void InsertStudentImageFunction(ActionEvent event) {
        
        pathname = "";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif"));
        file = fileChooser.showOpenDialog(imagepath.getScene().getWindow());

        if (file != null) {

            try {
                pathname = file.getAbsolutePath();
                imagepath.setText(file.toURI().toURL().toString());

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
            imagepath.setText("Image file selection cancelled.");
        }

        
    }

    @FXML
    private void RegisterStudentAction(ActionEvent event){
        
        Handler handler = new Handler();
                
        Student student = new Student("", "", "", "", "", "", "", "", "", "");
        String studentid = studentId.getText();
        student.setStudentId(studentid);
        String studentname = this.studentname.getText();
        student.setStudentName(studentname);
        String email = this.email.getText();
        student.setEmail(email);
        String phonenumber = this.phonenumber.getText();
        student.setPhonenumber(phonenumber);
        String enrollment = this.enrolyear.getValue().toString();
        student.setEnrollmentYear(enrollment);
        String yearOfStudy = this.studyyear.getValue().toString();
        student.setYearOfStudy(yearOfStudy);
        String duration = this.duration.getValue().toString();
        student.setDuration(duration);
        String programme = this.programme.getValue().toString();
        student.setProgramme(programme);
        String gender = this.gender.getValue().toString();
        student.setGender(gender);
        Boolean updated=false;
        
       if(studentid.isEmpty() || studentname.isEmpty() || email.isEmpty() || phonenumber.isEmpty() || 
               enrollment.isEmpty() || yearOfStudy.isEmpty() || duration.isEmpty() || programme.isEmpty() || gender.isEmpty())
       {
           Notification notify = new Notification(3, "Registration Section", "Please Fill in the Fields");
           notify.start();
       }
          
       
        if (imagepath.getText().isEmpty()) {
            Notification notification = new Notification(2, "Information entry error",
                    "Please makes sure Profile Picture is selected", "error");
            notification.start();

        } else {
            Folder folder = new Folder();
            folder.createFolders("Loan\\StudentProfile");
            FileCopier copier = new FileCopier(studentid, "StudentProfile", pathname);
            copier.copyFile();
            updated = true;
        }
        
       String extension = FilenameUtils.getExtension(pathname);
       String new_image_path = System.getProperty("user.home") + "\\Documents\\Loan\\StudentProfile\\" + studentid + "." + extension;        
       String replace = new_image_path.replace("\\", "@");
       student.setImage(replace);
       System.out.println("----->"+replace);   
       
        if(handler.execAction(student.RegisterStudent()))
        {
           Notification notify = new Notification(2, "Registration Section", "Registration Successful");
           notify.start();
        }
        else
        {
           Notification notify = new Notification(2, "Registration Section", "Registration Failed");
           notify.start();
        }
   
        
   }
   
    

    @FXML
    private void ClearFieldsAction(ActionEvent event) {
        
   studentId.clear();
   email.clear();
  phonenumber.clear();
   studentname.clear();
   enrolyear.setValue(null);
   studyyear.setValue(null);
   duration.setValue(null);
    programme.setValue(null);
    imagepath.clear();
    imageView.setImage(null);
    }
    
    
}
