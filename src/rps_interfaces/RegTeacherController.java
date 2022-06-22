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
public class RegTeacherController implements Initializable {

    @FXML
    private JFXTextField TeacherID;
    @FXML
    private JFXTextField TeacherName;
    @FXML
    private JFXTextField Username;
    @FXML
    private JFXTextField Password;

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
           CallableStatement callableStmt = con.prepareCall("{call PROC_TEACHER_INSERT_IN(?,?,?,?)}");
           
           //IN parameter -
           //   1) set methods are used for setting IN parameter values of Stored procedure
           callableStmt.setString(1, TeacherID.getText());
           callableStmt.setString(2, TeacherName.getText());
           callableStmt.setString(3, Username.getText());
           callableStmt.setString(4, Password.getText());
           
           // Should search in others databases if username and password already exist
          
          
 
           // execute database Stored procedure
           callableStmt.executeUpdate();
            
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
            
        }
    }

    @FXML
    private void ResetAllAction(MouseEvent event) {
        TeacherID.setText("");
        TeacherName.setText("");
        Username.setText("");
        Password.setText("");
    }
    
}
