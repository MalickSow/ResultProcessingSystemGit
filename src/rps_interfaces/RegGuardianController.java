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
public class RegGuardianController implements Initializable {

    @FXML
    private JFXTextField ID;
    @FXML
    private JFXTextField Name;
    @FXML
    private JFXTextField Phone;
    @FXML
    private JFXTextField Office;

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
             PreparedStatement pst= con.prepareStatement("insert into guardian values(?,?,?,?)");
           
          
           pst.setString(1, ID.getText());
           pst.setString(2, Name.getText());
           pst.setString(3, Phone.getText());
           pst.setString(4, Office.getText());
          
          
 
           // execute database Stored procedure
           pst.executeUpdate();

            
            
        }
        catch (ClassNotFoundException | SQLException e) {
            
            System.out.println(e);
        }
    }

    @FXML
    private void ResetAllAction(MouseEvent event) {
        
        ID.setText("");
        Name.setText("");
        Phone.setText("");
        Office.setText(""); 
    }
    
}
