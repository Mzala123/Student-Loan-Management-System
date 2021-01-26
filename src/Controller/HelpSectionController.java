/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.PopWindow;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author bounce
 */
public class HelpSectionController implements Initializable {

    
    
    
    @FXML
    private MediaView mediaview;
    
    private String filepath;
    
    private MediaPlayer mediaplayer;
    @FXML
    private StackPane MainStackpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OpenfileAction(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Choose a FILE (*.Mp4)", "*.Mp4", "*.Mp3", "*.Mvc" ,"*.webm");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        filepath = file.toURI().toString();
        
        if(filepath!= null)
        {
            Media media = new Media(filepath);
            mediaplayer = new MediaPlayer(media);
            mediaview.setMediaPlayer(mediaplayer);
            mediaplayer.play();
//            DoubleProperty width = mediaview.fitWidthProperty();
//            DoubleProperty height = mediaview.fitHeightProperty();
//            
//            width.bind(Bindings.selectDouble(mediaview.sceneProperty(), "width"));
//            height.bind(Bindings.selectDouble(mediaview.sceneProperty(), "height"));
//            
        }
        
    }

    @FXML
    private void PlayAction(ActionEvent event) {
        mediaplayer.play();
    }

    @FXML
    private void pauseAction(ActionEvent event) {
        mediaplayer.pause();
    }

    @FXML
    private void rewindAction(ActionEvent event) {
        
    }

    @FXML
    private void StopAction(ActionEvent event) {
        mediaplayer.stop();
    }

    @FXML
    private void fastForwardAction(ActionEvent event) {
    }

    @FXML
    private void BackToMenuOperations(ActionEvent event) {
        PopWindow window = new PopWindow();  
        window.loadWindow("/View/OperationsMenu.fxml", "",true,true,false,false);
        MainStackpane.getScene().getWindow().hide();
    }
    
}
