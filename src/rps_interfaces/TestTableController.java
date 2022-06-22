/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rps_interfaces;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Myriam
 */
public class TestTableController implements Initializable {

    @FXML
    private TableColumn<TestTableClass, String> studentid;
    @FXML
    private TableColumn<TestTableClass, Integer> atd;
    @FXML
    private TableView<TestTableClass> table;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<TestTableClass> oblist=FXCollections.observableArrayList();
        
         try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url_ = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection conn = DriverManager.getConnection(url_,"project","project");  
            String query = "select * from Marks";
            PreparedStatement pst = conn.prepareStatement(query);
             
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
               //System.out.println(rs.getString("studentid"));
                //String finalm=Integer.toString(rs.getInt("final"));
                oblist.add(new TestTableClass(rs.getString("studentid"),rs.getInt("attendance")));
                
             }
            
            pst.close();
            rs.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        
        
        studentid.setCellValueFactory(new PropertyValueFactory<>("id"));
        atd.setCellValueFactory(new PropertyValueFactory<>("atd"));
        
        //table.setEditable(true);
        table.setItems(oblist);
    }    
    
}
