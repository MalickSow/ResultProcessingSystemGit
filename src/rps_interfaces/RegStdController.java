/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rps_interfaces;

import com.jfoenix.controls.JFXComboBox;
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
public class RegStdController implements Initializable {
    @FXML
    JFXComboBox<String> combobox;
    @FXML
    private JFXTextField ID;
    @FXML
    private JFXTextField Name;
    @FXML
    private JFXTextField Phone;
    @FXML
    private JFXTextField Address;
    @FXML
    private JFXTextField Guardian_ID;
    @FXML
    private JFXTextField Country;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combobox.getItems().add("CSE");
        combobox.getItems().add("EEE");
        combobox.getItems().add("MCE");
       
    }    

    @FXML
    private void SaveAction(MouseEvent event) {
        try{
            Class.forName("oracle.jdbc.OracleDriver");
             String host ="jdbc:oracle:thin:@localhost:1521:XE";
            String username ="project";
            String userpass ="project";
            Connection con =DriverManager.getConnection(host,username,userpass);
           CallableStatement callableStmt = con.prepareCall("{call PROC_STUDENT_INSERT_IN(?,?,?,?,?,?)}");
           
           //IN parameter -
           //   1) set methods are used for setting IN parameter values of Stored procedure
           callableStmt.setString(1, ID.getText());
           callableStmt.setString(2, Name.getText());
           callableStmt.setString(3, Phone.getText());
           callableStmt.setString(4, Address.getText());
           callableStmt.setString(5, Guardian_ID.getText());
           callableStmt.setString(6, Country.getText());
          
 
           // execute database Stored procedure
           callableStmt.executeUpdate();

            
            
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        try {
            Class.forName("oracle.jdbc.OracleDriver");
             String host ="jdbc:oracle:thin:@localhost:1521:XE";
            String username ="project";
            String userpass ="project";
            Connection con =DriverManager.getConnection(host,username,userpass);
            String query = "insert into TCGPA(Student_ID) values(?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, ID.getText());
          
            
            pst.executeUpdate();
            pst.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        
        ResetAllAction(event);
    }

    @FXML
    private void ResetAllAction(MouseEvent event) {
        ID.setText("");
        Name.setText("");
        Phone.setText("");
        Address.setText("");
        Guardian_ID.setText("");
        Country.setText("");
    }
    
}
