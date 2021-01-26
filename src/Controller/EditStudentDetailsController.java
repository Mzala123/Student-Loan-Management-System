/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.ApplyForLoanController.StudentId;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import org.apache.commons.io.FilenameUtils;

/**
 * FXML Controller class
 *
 * @author bounce
 */
public class EditStudentDetailsController implements Initializable {

    @FXML
    private AnchorPane firstAnchorpane;
    @FXML
    private AnchorPane secondAnchorpane;
    @FXML
    private AnchorPane ThirdAnchorpane;
    @FXML
    private AnchorPane headerAnchorPane;
   
    @FXML
    private JFXTextField studentName;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField number;
    @FXML
    private JFXComboBox<String> comboGender;
    @FXML
    private JFXComboBox<Integer> comboEnrol;
    @FXML
    private JFXComboBox<String> comboYearStudy;
    @FXML
    private JFXComboBox<Integer> comboDuration;
    @FXML
    private JFXComboBox<String> comboProgramme;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXTextArea imagePath;
    
    @FXML
    private StackPane MainStackpane;

    
     public static JFXTextField  StudentId;
     
    @FXML
    private JFXTextField studentID;
    
    ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<String> list1 = FXCollections.observableArrayList();
    ObservableList<String> list2 = FXCollections.observableArrayList();
    ObservableList<String> list3 = FXCollections.observableArrayList();
    
    
     public String pathname="";
    private Image UserImage;
    private FileChooser filechooser;
    private File file;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        JFXDepthManager.setDepth(firstAnchorpane, 1);
        JFXDepthManager.setDepth(secondAnchorpane, 1);
        JFXDepthManager.setDepth(ThirdAnchorpane, 1);
        JFXDepthManager.setDepth(headerAnchorPane, 1);
        
        Handler handler = Handler.getInstance();
        InitEnrolYearCombo();
        Duration();
        list.addAll("First Year", "Second Year", "Third Year", "Fourth Year", "Fifth Year");
        comboYearStudy.getItems().addAll(list);
        list1.addAll("BSc in Information and Communication Technology",
                "BSc in Computer Science", "BSc in Nursing and MidWifery", "Bsc in MBBS", "BSc In Clinical Medicine");
        comboProgramme.getItems().addAll(list1);
        list2.addAll("Male", "Female");
        comboGender.getItems().addAll(list2);
       
        StudentId = studentID;
        System.out.println("The selected id from list is tell us man: " +StudentListController.ID );
        
        setValues();
       
    }

private void Duration()
    {
     
     
     for(int count=3; count<=6; count++)
        {
          comboDuration.getItems().addAll(count);
  
        }
     
    }
    
    private void InitEnrolYearCombo()
    {
     String date = LocalDateTime.now().toString();
     System.out.println(date);
     System.out.println(date.substring(0, 4));
     
     for(int count=Integer.parseInt(date.substring(0, 4)); count>1999; count--)
        {
          comboEnrol.getItems().addAll(count);
  
        }
     
    }
    

    @FXML
    private void InsertNewImageAction(ActionEvent event) {
        pathname = "";
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif"));
        file = fileChooser.showOpenDialog(imagePath.getScene().getWindow());

        if (file != null) {

            try {
                pathname = file.getAbsolutePath();
                imagePath.setText(file.toURI().toURL().toString());

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
            imagePath.setText("Image file selection cancelled.");
        }

        
    }

    @FXML
    private void UpdateStudentRectionAction(ActionEvent event) {
           
        Student student = new Student("", "", "", "", "", "", "", "", "", "");
        Handler handler = new Handler();
        String studentid = studentID.getText();
        student.setStudentId(studentid);
        String studentname = this.studentName.getText();
        student.setStudentName(studentname);
        String email = this.email.getText();
        student.setEmail(email);
        String phonenumber = this.number.getText();
        student.setPhonenumber(phonenumber);
        String enrollment = this.comboEnrol.getValue().toString();
        student.setEnrollmentYear(enrollment);
        String yearOfStudy = this.comboYearStudy.getValue().toString();
        student.setYearOfStudy(yearOfStudy);
        String duration = this.comboDuration.getValue().toString();
        student.setDuration(duration);
        String programme = this.comboProgramme.getValue().toString();
        student.setProgramme(programme);
        String gender = this.comboGender.getValue().toString();
        student.setGender(gender);
        Boolean updated = false;
        
        if (imagePath.getText().isEmpty()) {
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
      
       if(studentid.isEmpty() || studentname.isEmpty() || email.isEmpty() || phonenumber.isEmpty())
       {
         Notification noti = new Notification(2, "Update Window", "Set in records to update");
         noti.start();  
       }
       
       if(handler.execUpdate(student.UpdateStudentRecord()))
       {
           Notification noti = new Notification(2, "Update Window", "Students Details updated successfully");
           noti.start();
       }
        else
       {
         Notification noti = new Notification(2, "Update Window", "Failed to update record!");
         noti.start();  
       }
    }

    @FXML
    private void CloseUpdateSection(ActionEvent event) {
        MainStackpane.getScene().getWindow().hide();
    }
    
    
    private void setValues()
    {
        
        String id = studentID.getText(); 
        System.out.println("The selected id: " +id);
        Handler handler = new Handler();
        String query1 = "Select * FROM Student WHERE StudentId='" +StudentListController.ID+ "';";
        System.out.println("The selected id from list is: " +StudentListController.ID );
        ResultSet rs = handler.execQuery(query1);
        try
        {
            if(rs.next())
            {
                String studentname = rs.getString("StudentName");
                String email = rs.getString("Email");
                String number = rs.getString("Phonenumber");
                String gender = rs.getString("Gender");
                Integer enrolyear= rs.getInt("EnrollmentYear");
                String studyYear = rs.getString("YearOfStudy");
                Integer Duration = rs.getInt("Duration");
                String faculty = rs.getString("Programme");
                String image = rs.getString("Image");
                studentName.setText(studentname);
                this.email.setText(email);
                this.number.setText(number);
                comboGender.setValue(gender);
                comboEnrol.setValue(enrolyear);
                comboYearStudy.setValue(studyYear);
                comboDuration.setValue(Duration);
                comboProgramme.setValue(faculty);
                imagePath.setText(image);
                
                String replace = image.replace("@", "\\");
                System.out.println(image);
                System.out.println(replace);
                file = new File(replace);
                if (file.exists()) {

                    Image image1 = null;
                    image1 = new Image(file.toURI().toString(), 300, 300, false, true);
                    //rectangle.setFill(new ImagePattern(image1));
                    imageView.setImage(image1);
                    // imageView.setFitHeight(300);
                }
            }
        }
        catch(SQLException ex)
        {
            
        }
    }
    
}
