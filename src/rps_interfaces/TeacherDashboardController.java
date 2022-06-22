/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rps_interfaces;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Myriam
 */
public class TeacherDashboardController implements Initializable {
    
    @FXML
     private AnchorPane ap;
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
    private JFXButton b;
    
    String TeacherId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
        // TODO
    }    
    
    
    private void setCourses(){
       try{
           Class.forName("oracle.jdbc.OracleDriver");
             String host ="jdbc:oracle:thin:@localhost:1521:XE";
            String username ="project";
            String userpass ="project";
            Connection con =DriverManager.getConnection(host,username,userpass);
            
            PreparedStatement pst= con.prepareStatement("select * from Course where TeacherID=?");
            
            pst.setString(1, TeacherId);
            
            ResultSet rs= pst.executeQuery();
            while(rs.next()){
                
            }
            
       }catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
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
    public void getTeacherID(String id){
        System.out.println("GetId "+ id);
        TeacherId=id;
    }
    @FXML
    private void MarksLabAction(MouseEvent event) {
        
       // b.setStyle("-fx-backgroung-color :  #3E98E4 ;");
        loadPage("MarksLab");
    }

    @FXML
    private void MarksTheoryAction(MouseEvent event) throws IOException {
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("MarksTheory.fxml"));
                
                Parent root=loader.load();
                //Parent root= FXMLLoader.load(getClass().getResource("TeacherDashboard.fxml"));
                
                Scene TeacherScene = new Scene(root);
                
                MarksTheoryController controller= loader.getController();
               
                bp.setCenter(root);
                
                controller.getTeacherId(TeacherId);
                
                // Put Scene on same Center AnchorPane
                
                
                
                // put scene on new stage : Stage window = new Stage();
                
                
                
        //loadPage("MarksTheory");
    }

    @FXML
    private void GradesAction(MouseEvent event) {
        
    }

    @FXML
    private void GuardianInfoAction(MouseEvent event) {
        loadPage("GuardianInfo");
    }
    
}
