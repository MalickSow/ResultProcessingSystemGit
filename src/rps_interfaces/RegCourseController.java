/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rps_interfaces;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author Myriam
 */
public class RegCourseController implements Initializable {

    @FXML
    private JFXTextField CourseID;
    @FXML
    private JFXTextField CourseName;
    @FXML
    private JFXTextField Credit;
    @FXML
    private JFXTextField CourseTeacher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void SaveAction(MouseEvent event) {
        
        try{
            Class.forName("oracle.jdbc.OracleDriver");
             String host ="jdbc:oracle:thin:@localhost:1521:XE";
            String username ="project";
            String userpass ="project";
            Connection con =DriverManager.getConnection(host,username,userpass);
           CallableStatement callableStmt = con.prepareCall("{call PROC_COURSE_INSERT_IN(?,?,?,?)}");
           
           //IN parameter -
           //   1) set methods are used for setting IN parameter values of Stored procedure
           callableStmt.setString(1, CourseID.getText());
           callableStmt.setString(2, CourseName.getText());
           callableStmt.setString(3, Credit.getText());
           callableStmt.setString(4, CourseTeacher.getText());
          
          
 
           // execute database Stored procedure
           callableStmt.executeUpdate();
            
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
            
        }
    }

    @FXML
    private void ResetAllAction(MouseEvent event) {
        
    }

}
