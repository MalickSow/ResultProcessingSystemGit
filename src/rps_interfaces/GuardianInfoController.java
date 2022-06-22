/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rps_interfaces;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class GuardianInfoController implements Initializable {

    @FXML
    private JFXTextField studentid;
    @FXML
    private JFXTextArea guardianname;
    @FXML
    private JFXTextArea phone;
    @FXML
    private JFXTextArea office;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GuardianInfoAction(MouseEvent event) {
        
        System.out.println("RE");
        String stdid = studentid.getText();
        String GuardianId = null;
        //select guardian id from student table
        
        try {

          Class.forName("oracle.jdbc.OracleDriver");
            String url_ = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection conn = DriverManager.getConnection(url_,"project","project");
            String query = "select * from guardian where id =(select guardianid from student where id=?)";
            
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1,stdid);
           
         
            ResultSet rs = pst.executeQuery();
            if(rs.next())
            {
                guardianname.setText(rs.getString("name"));
                phone.setText(rs.getString("phonenumber"));
                office.setText(rs.getString("office"));
                System.out.println("rs");
            }
            pst.close();
            rs.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        
    }
    
}
