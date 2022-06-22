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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Myriam
 */
public class ViewGradeController implements Initializable {
      
    
  
   
    private TableView<GradeClass> table;
    private TableColumn<GradeClass,String> col_course;
    private TableColumn<GradeClass,Integer> col_grade;
    
    ObservableList<GradeClass> oblist=FXCollections.observableArrayList();
    private TableColumn<GradeClass,Integer> col_q1;
    private TableColumn<GradeClass,Integer> col_q2;
    private TableColumn<GradeClass,Integer> col_q3;
    private TableColumn<GradeClass,Integer> col_q4;
    private TableColumn<GradeClass,Integer> col_m;
    private TableColumn<GradeClass,Integer> col_f;

    /**
     * Initializes the controller class.
     */
     public void editcell(CellEditEvent edit){
         
         
         String col= edit.getTableColumn().getText();
         System.out.println(col);
         String newvalue=edit.getNewValue().toString();
         String oldvalue=edit.getOldValue().toString();
         System.out.println(newvalue);
         System.out.println(oldvalue);
         
         
          try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url_ = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection conn = DriverManager.getConnection(url_,"malick","malick");  
            String query = "update U set username=? where username=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1,newvalue);
            pst.setString(2,oldvalue);
            int n = pst.executeUpdate();
            System.out.println(n);
            
            pst.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
      
       //GradeClass G=table.getSelectionModel().getSelectedItem();
       //G.setCourse(edit.getNewValue().toString());
       
       
       
      
       
   } 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url_ = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection conn = DriverManager.getConnection(url_,"project","project");  
            String query = "select * from Marks";
            PreparedStatement pst = conn.prepareStatement(query);
             
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("studentid"));
                String finalm=Integer.toString(rs.getInt("final"));
                
               // oblist.add(new GradeClass(rs.getString("studentid"),finalm));
                oblist.add(new GradeClass(rs.getString("studentid"),rs.getInt("Attendance"),rs.getInt("quiz1"),rs.getInt("quiz2"),rs.getInt("quiz3"),rs.getInt("quiz4"),rs.getInt("mid"),rs.getInt("final")));
            }
            
            pst.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        
        
        col_course.setCellValueFactory(new PropertyValueFactory<>("course"));
        col_grade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        col_q1.setCellValueFactory(new PropertyValueFactory<>("Q1"));
        col_q2.setCellValueFactory(new PropertyValueFactory<>("Q2"));
        col_q3.setCellValueFactory(new PropertyValueFactory<>("Q3"));
        col_q4.setCellValueFactory(new PropertyValueFactory<>("Q4"));
        col_m.setCellValueFactory(new PropertyValueFactory<>("M"));
        col_f.setCellValueFactory(new PropertyValueFactory<>("F"));
        
        table.setEditable(true);
        table.setItems(oblist);
        //table.getItems();
        //to update the table you give edit cell action to all column and in edutcellaction you get selected value and the std id and you update 
        
       // col_course.setEditable(true);
       // col_grade.setEditable(true);
       //col_course.setCellFactory(TextFieldTableCell.forTableColumn());
       //col_grade.setCellFactory(TextFieldTableCell.forTableColumn());
       //Edit Integer
       /* @FXML
        private TableColumn<Product, Integer> col_qty;
        col_qty.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
       */
       
        
    }    

    @FXML
    private void GuardianInfoAction(MouseEvent event) {
    }
    
}
