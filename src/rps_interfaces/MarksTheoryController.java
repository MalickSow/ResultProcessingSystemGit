/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rps_interfaces;

import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author Myriam
 */
public class MarksTheoryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList<Marks> studentmarkslist= FXCollections.observableArrayList();
    String TeacherId;
    String CourseID;
    Marks G;
    @FXML
    private JFXComboBox<String> CourseComboBox;
    private TableColumn<Marks,String> col_studentid;
    @FXML
    private TableColumn<Marks,Integer> col_attendance;
    @FXML
    private TableColumn<Marks,Integer> col_quiz1;
    @FXML
    private TableColumn<Marks,Integer> col_quiz2;
    @FXML
    private TableColumn<Marks,Integer> col_quiz3;
    @FXML
    private TableColumn<Marks,Integer> col_quiz4;
    @FXML
    private TableColumn<Marks,Integer> col_mid;
    @FXML
    private TableColumn<Marks,Integer> col_final;
    @FXML
    private TableView<Marks> MarksTable;
    @FXML
    private TableColumn<Marks,String> C_SID;
    
    @FXML
     public void editcell(TableColumn.CellEditEvent edit){
         
         String Course = CourseComboBox.getSelectionModel().getSelectedItem().toString();
      
       
      // String CourseID=null;
       
       try {
            
            
             Class.forName("oracle.jdbc.OracleDriver");
             String host ="jdbc:oracle:thin:@localhost:1521:XE";
            String username ="project";
            String userpass ="project";
            Connection con =DriverManager.getConnection(host,username,userpass);
            String Query = "select ID from Course where Name=?";
            PreparedStatement pst = con.prepareStatement(Query);
            pst.setString(1, Course);
            ResultSet rs = pst.executeQuery();

           
            while (rs.next()) {
                CourseID=rs.getString("ID");
                
            }
            rs.close();
            pst.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
         
         
         String newvalue=edit.getNewValue().toString();
         String oldvalue=edit.getOldValue().toString();
         System.out.println(newvalue);
         System.out.println(oldvalue);
         
         String columnedited = edit.getTableColumn().getText();
         G=MarksTable.getSelectionModel().getSelectedItem();
         String studentid = G.getS_ID();
         
          try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url_ = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection conn = DriverManager.getConnection(url_,"project","project");
            String query = "update Marks set "+columnedited+"=? where studentid=? and courseid=?";
            PreparedStatement pst = conn.prepareStatement(query);
            //pst.setString(1,columnedited);
            pst.setInt(1,Integer.parseInt(newvalue));
            pst.setString(2, studentid);
            pst.setString(3,CourseID);
            int n = pst.executeUpdate();
            System.out.println(n);
            
            pst.close();

        } catch (ClassNotFoundException | SQLException e) {
            
            System.out.println(e);
        }
      
       //GradeClass G=table.getSelectionModel().getSelectedItem();
       //G.setCourse(edit.getNewValue().toString());
       
       computegrade(studentid);
       
      
       
   } 
     public void computegrade(String S_ID){
    
    int attendance = 0,mid = 0,quiz1 = 0,quiz2 = 0,quiz3 = 0,quiz4,finalmark = 0;
        
       try{
           Class.forName("oracle.jdbc.OracleDriver");
             String host ="jdbc:oracle:thin:@localhost:1521:XE";
            String username ="project";
            String userpass ="project";
            Connection con =DriverManager.getConnection(host,username,userpass);
            
            PreparedStatement pst= con.prepareStatement("select * from Marks where studentid=? and CourseId=?");
            
            pst.setString(1,S_ID);
            pst.setString(2,CourseID);
            
            ResultSet rs= pst.executeQuery();
            if(rs.next()){
                S_ID=rs.getString("studentid");
                attendance=rs.getInt("attendance");
                mid=rs.getInt("mid");
                quiz1=rs.getInt("quiz1");
                quiz2=rs.getInt("quiz2");
                quiz3=rs.getInt("quiz3");
                quiz4=rs.getInt("quiz4");
                finalmark=rs.getInt("final");
                
               
                
                
            }
            
            pst.close();
            
       }catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
       
       String value = G.getS_ID();
       String val_CID= CourseID;

        float grade;
        int credit = 0;
        try {
           Class.forName("oracle.jdbc.OracleDriver");
            String url_ = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection conn = DriverManager.getConnection(url_,"project","project");
            String query = "select * from Course where ID=?";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, CourseID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                credit = rs.getInt("credit");

                //System.out.println(credit);
            }

            pst.close();
            rs.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        System.out.println(credit);
        //compute grade here
        int totalcredit = credit * 100;

        grade = (mid + quiz1 + quiz2 + quiz3 + attendance + finalmark);

        grade = grade / totalcredit;

        float percentage;
        double gr;
        percentage = (grade * 100);
        //System.out.println(percentage);
        String gradeCharacter;
        if (percentage >= 80) {
            gradeCharacter = "A+";
            gr = 4.0;
        } else if (percentage > 75 && percentage < 80) {
            gradeCharacter = "A";
            gr = 3.75;
        } else if (percentage >= 70 && percentage < 75) {
            gradeCharacter = "A-";
            gr = 3.5;
        } else if (percentage > 65 && percentage < 70) {
            gradeCharacter = "B+";
            gr = 3.25;
        } else if (percentage >= 60 && percentage < 65) {
            gradeCharacter = "B";
            gr = 3;
        } else if (percentage >= 55 && percentage < 60) {
            gradeCharacter = "B-";
            gr = 2.75;
        } else if (percentage > 50 && percentage < 55) {
            gradeCharacter = "C+";
            gr = 2.5;
        } else if (percentage >= 45 && percentage < 50) {
            gradeCharacter = "C";
            gr = 2.25;
        } else if (percentage >= 40 && percentage < 45) {
            gradeCharacter = "D";
            gr = 2;
        } else {
            gradeCharacter = "F";
            gr = 0;
        }
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url_ = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection conn = DriverManager.getConnection(url_,"project","project");
            String query = "UPDATE Marks SET grade=?,gradecharacter=?,credit=? where StudentID=? and CourseID=?";
            PreparedStatement pst = conn.prepareStatement(query);

            
            
            pst.setDouble(1, gr);

            pst.setString(2, gradeCharacter);
            pst.setInt(3, credit);
            pst.setString(4, value);
            pst.setString(5, val_CID);

            int n = pst.executeUpdate();
            System.out.println(n);
           

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);

        }
        double gpa = 0;
        double tgrade = 0, tcredit = 0;
        try {
            double tempgrade=0,tempcredit=0; 
            int count = 0;
            double temp;
            Class.forName("oracle.jdbc.OracleDriver");
            String url_ = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection conn = DriverManager.getConnection(url_,"project","project");
            String query = "select * from Marks where StudentID=?";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, value);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                temp = rs.getDouble("grade");
                if (temp == 0) {

                    count++;
                    break;
                }
                 tempcredit =rs.getDouble("credit");
                
                tempgrade=tempgrade+temp*tempcredit;
                tcredit = tcredit+tempcredit;
                

                //System.out.println(credit);
            }
            if (count == 0) {
                gpa = tempgrade / tcredit;
            } else {
                gpa = 0;
            }

            pst.close();
            rs.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

        double tempcgpa = 0, tempgpa = 0;;
        try {

          Class.forName("oracle.jdbc.OracleDriver");
            String url_ = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection conn = DriverManager.getConnection(url_,"project","project");
            String query = "select *from TCGPA where Student_ID=?";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1,value);
         
            ResultSet rs = pst.executeQuery();
            while(rs.next())
            {
                tempcgpa = rs.getDouble("CGPA");
                tempgpa = rs.getDouble("GPA");
            }
            pst.close();
            rs.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        
         if (tempcgpa == 0) {
            try {

                Class.forName("oracle.jdbc.OracleDriver");
            String url_ = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection conn = DriverManager.getConnection(url_,"project","project");
                String query = "UPDATE  TCGPA SET CGPA=?,GPA=? where Student_ID=?";

                PreparedStatement pst = conn.prepareStatement(query);
                pst.setDouble(1, gpa);
                pst.setDouble(2, gpa);
                pst.setString(3,value);
                pst.executeUpdate();
                 
                pst.close();
                

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }
                
        }
        else
        {
            try {

              Class.forName("oracle.jdbc.OracleDriver");
            String url_ = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection conn = DriverManager.getConnection(url_,"project","project");
                String query = "UPDATE  TCGPA SET CGPA=?,GPA=? where Student_ID=?";

                PreparedStatement pst = conn.prepareStatement(query);
                if(tempgpa==gpa)
                {
                    pst.setDouble(1,tempcgpa);
                }
                
                else {double cgpa=(gpa+tempcgpa)/2;
                pst.setDouble(1, cgpa);}
                pst.setDouble(2, gpa);
                pst.setString(3, value);
                pst.executeUpdate();
                 
                pst.close();
                

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }
        }
         //Select Semester to enter into gpasemester
         int sem = 0;
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url_ = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection conn = DriverManager.getConnection(url_,"project","project");
            String query = "select * from Marks where StudentID=? and CourseID=?";

            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, value);
             pst.setString(2, val_CID);
            System.out.println("ValCID"+val_CID);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                sem= rs.getInt("Semester");
                
               

                //System.out.println(credit);
            }

            pst.close();
            rs.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
       
        try {

               Class.forName("oracle.jdbc.OracleDriver");
            String url_ = "jdbc:oracle:thin:@localhost:1521:XE";
            Connection conn = DriverManager.getConnection(url_,"project","project");
                String query = "insert into Gpasemester(Student_ID,Semester,GPA) values(?,?,?)";

                PreparedStatement pst = conn.prepareStatement(query);
                pst.setString(1, value);
                
                pst.setInt(2, sem);
                pst.setDouble(3, gpa);
                pst.executeUpdate();
                 
                pst.close();
                

            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }

       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       // System.out.println("Teacher 233 " +TeacherId);
        
        
        
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
                System.out.println("Course " +rs.getString("Name"));
                CourseComboBox.getItems().add(rs.getString("Name"));
            }
            
            pst.close();
            
       }catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
      
    }
     
    
    public void getTeacherId(String id){
        System.out.println("GetTID"+ id);
        TeacherId=id;
        setCourses();
    }

    @FXML
    private void SelectStudentAction(MouseEvent event) {
        
        String Course = CourseComboBox.getSelectionModel().getSelectedItem().toString();
      
       
      // String CourseID=null;
       
       try {
            
            
             Class.forName("oracle.jdbc.OracleDriver");
             String host ="jdbc:oracle:thin:@localhost:1521:XE";
            String username ="project";
            String userpass ="project";
            Connection con =DriverManager.getConnection(host,username,userpass);
            String Query = "select ID from Course where Name=?";
            PreparedStatement pst = con.prepareStatement(Query);
            pst.setString(1, Course);
            ResultSet rs = pst.executeQuery();

           
            while (rs.next()) {
                CourseID=rs.getString("ID");
                
            }
            rs.close();
            pst.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }

       
       
       
       try {
            
            //System.out.println(val_CID);
             Class.forName("oracle.jdbc.OracleDriver");
             String host ="jdbc:oracle:thin:@localhost:1521:XE";
            String username ="project";
            String userpass ="project";
            Connection con =DriverManager.getConnection(host,username,userpass);
            String Query = "select * from Marks where CourseID=?";
            PreparedStatement pst = con.prepareStatement(Query);
            pst.setString(1, CourseID);
            ResultSet rs = pst.executeQuery();

           
            while (rs.next()) {
                studentmarkslist.add(new Marks(rs.getString("StudentID"), rs.getInt("Attendance"), rs.getInt("Mid"), rs.getInt("quiz1"), rs.getInt("quiz2"), rs.getInt("quiz3"), rs.getInt("quiz4"), rs.getInt("final")));                

            }
            rs.close();
            pst.close();

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
       C_SID.setCellValueFactory(new PropertyValueFactory<>("S_ID"));
       col_attendance.setCellValueFactory(new PropertyValueFactory<>("Attendance"));
       col_quiz1.setCellValueFactory(new PropertyValueFactory<>("Quiz1"));
       col_quiz2.setCellValueFactory(new PropertyValueFactory<>("Quiz2"));
       col_quiz3.setCellValueFactory(new PropertyValueFactory<>("Quiz3"));
       col_quiz4.setCellValueFactory(new PropertyValueFactory<>("Quiz4"));
       col_mid.setCellValueFactory(new PropertyValueFactory<>("Mid"));
       col_final.setCellValueFactory(new PropertyValueFactory<>("finalmark"));
       
       MarksTable.setEditable(true);
       MarksTable.setItems(studentmarkslist);
       
       
       
       C_SID.setCellFactory(TextFieldTableCell.forTableColumn());
       col_attendance.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
       col_quiz1.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
       col_quiz2.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
       col_quiz3.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
       col_quiz4.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
       col_mid.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
       col_final.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
       
       //start computation
       
       
        
        
    }
    
    
   
    
}
