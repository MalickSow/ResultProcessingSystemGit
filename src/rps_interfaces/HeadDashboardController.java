/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rps_interfaces;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Myriam
 */
public class HeadDashboardController implements Initializable {
    @FXML 
    private BorderPane bp;
    @FXML
    private ImageView imageview;
    @FXML
    private ImageView imageview1;
    @FXML
    private ImageView imageview2;
    @FXML
    private ImageView imageview3;
    @FXML
    private ImageView imageview4;
    
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     private void loadPage(String Page){
        Parent root=null;
        
        try {
            root=FXMLLoader.load(getClass().getResource(Page +".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(TeacherDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
       
        
    }

    @FXML
    private void RegisterCourseAction(MouseEvent event) {
        loadPage("RegCourse");
        
    }

    @FXML
    private void RegisterTeacherAction(MouseEvent event) {
        loadPage("RegTeacher");
    }

    @FXML
    private void GradeAction(MouseEvent event) {
        //   not created yet  :loadPage("ViewGradeHead");
    }

    @FXML
    private void UpdateCourseAction(MouseEvent event) {
        loadPage("UpdateCourse");
    }

    @FXML
    private void RegStdtCourseAction(MouseEvent event) {
        loadPage("RegStdtoCourse");
    }
    
}
