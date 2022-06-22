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
import java.sql.PreparedStatement;
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
public class RegStdtoCourseController implements Initializable {

    @FXML
    private JFXTextField StudentID;
    @FXML
    private JFXTextField CourseID;
    @FXML
    private JFXTextField Year;
    @FXML
    private JFXTextField Semester;
    

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
           CallableStatement callableStmt = con.prepareCall("{call PROC_STUDENTCOURSE_INSERT_IN(?,?,?)}");
           
           //IN parameter -
           //   1) set methods are used for setting IN parameter values of Stored procedure
           callableStmt.setString(1, CourseID.getText());
           callableStmt.setString(2, StudentID.getText());
           callableStmt.setString(3, Year.getText());
         
          
          
 
           // execute database Stored procedure
           callableStmt.executeUpdate();
            
        }catch(ClassNotFoundException | SQLException e){
            System.out.println(e);
            
        }
        try {
            Class.forName("oracle.jdbc.OracleDriver");
             String host ="jdbc:oracle:thin:@localhost:1521:XE";
            String username ="project";
            String userpass ="project";
            Connection conn =DriverManager.getConnection(host,username,userpass);
            String query = "insert into Marks(StudentID,CourseID,Semester) values(?,?,?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, StudentID.getText());
            pst.setString(2, CourseID.getText());
            pst.setInt(3, Integer.parseInt(Semester.getText()));
            
            pst.executeUpdate();
            pst.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void ResetAllAction(MouseEvent event) {
        StudentID.setText("");
        CourseID.setText("");
        Year.setText("");
        Semester.setText("");
       
    }
    
}
