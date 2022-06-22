/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rps_interfaces;

import com.jfoenix.controls.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Myriam
 */

public class LoginController implements Initializable {
      @FXML
    private Label label;
      @FXML
    private JFXTextField username;
      @FXML
    private PasswordField password;
    
      
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        


    }   
      @FXML
      public void loginAction(ActionEvent event) throws IOException{
         
          String user = username.getText();
          String pass = password.getText();
          
          System.out.println(user);
          
          
          try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection conn = DriverManager.getConnection(url,"project","project");  
            String QueryTeacher = "select * from teacher where username=? and password=?";
            String QueryHead =  "select * from head where username=? and password=?";
            String QueryRegister =  "select * from register where username=? and password=?";
            
            PreparedStatement pst_Teacher = conn.prepareStatement(QueryTeacher);
            PreparedStatement pst_Head = conn.prepareStatement(QueryHead);
            PreparedStatement pst_Register = conn.prepareStatement(QueryRegister);
            
            
            
            pst_Teacher.setString(1,user);
            pst_Teacher.setString(2,pass);
            
            pst_Head.setString(1,user);
            pst_Head.setString(2,pass);
            
            pst_Register.setString(1,user);
            pst_Register.setString(2,pass);
            
            ResultSet rs_Teacher=pst_Teacher.executeQuery();
            ResultSet rs_Head=pst_Head.executeQuery();
            ResultSet rs_Register=pst_Register.executeQuery();
            if(rs_Teacher.next()){
                
                //System.out.println(user);
                
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("TeacherDashboard.fxml"));
                
                Parent root=loader.load();
                //Parent root= FXMLLoader.load(getClass().getResource("TeacherDashboard.fxml"));
                
                Scene TeacherScene = new Scene(root);
                
                TeacherDashboardController controller= loader.getController();
               
                controller.getTeacherID(rs_Teacher.getString("Id"));
                // Put Scene on same Stage
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                
                // put scene on new stage : Stage window = new Stage();
                
                
                window.setScene(TeacherScene);
                window.show();
                
            }
            
            if(rs_Head.next()){
                
                //System.out.println(user);
                Parent root= FXMLLoader.load(getClass().getResource("HeadDashboard.fxml"));
               
                
                Scene TeacherScene = new Scene(root);
                
                
                // Put Scene on same Stage
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                
                // put scene on new stage : Stage window = new Stage();
                
                
                window.setScene(TeacherScene);
                window.show();
                
            }
            
            if(rs_Register.next()){
                
                //System.out.println(user);
                
                Parent root= FXMLLoader.load(getClass().getResource("RegisterDashboard.fxml"));
                
                Scene TeacherScene = new Scene(root);
                // Put Scene on same Stage
              
               
                
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                
                // put scene on new stage : Stage window = new Stage();
                
                
                window.setScene(TeacherScene);
                window.show();
                
            }
           
            
            pst_Teacher.close();
            pst_Head.close();
            pst_Register.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
          
      }
    
}
