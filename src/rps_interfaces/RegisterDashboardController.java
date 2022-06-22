/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rps_interfaces;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

/**
 * FXML Controller class
 *
 * @author Myriam
 */
public class RegisterDashboardController implements Initializable {

          @FXML 
          private BorderPane mainPane;
          @FXML
          private ImageView imageview;
    @FXML
    private AnchorPane ap;
    @FXML
    private ImageView imageview1;
          
         
          private void loadfxml(String File_name){
              
              Parent root=null;
              try {
                  root = FXMLLoader.load(getClass().getResource(File_name +".fxml"));
              } catch (IOException ex) {
                  Logger.getLogger(RegisterDashboardController.class.getName()).log(Level.SEVERE, null, ex);
              }
              mainPane.setCenter(root);
              
          }
              
          
          
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //load images
        /*Image image = null;
              try {
                  image = new Image(getClass().getResource("access_40px.png").toURI().toString());
              } catch (URISyntaxException ex) {
                  Logger.getLogger(RegisterDashboardController.class.getName()).log(Level.SEVERE, null, ex);
              }
        imageview.setImage(image);*/
        
    }    
   
    @FXML
    private void RegisterStudentAction(MouseEvent event) {
        loadfxml("RegStd");
        
    }
    @FXML
    private void ViewGradeAction(MouseEvent event) {
        loadfxml("ViewGradeRegister");
    }

    @FXML
    private void UsernamePasswordAction(MouseEvent event) {
        loadfxml("RegUserPassw");
    }

    @FXML
    private void RegisterGuardianAction(MouseEvent event) {
        loadfxml("RegGuardian");
    }

    
    
}
