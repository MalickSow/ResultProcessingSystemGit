/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rps_interfaces;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Myriam
 */
public class GradeClass extends RecursiveTreeObject<GradeClass> {
    private String ID;
    private Integer Quiz1;
    private Integer A;
    private Integer Quiz2;
    private Integer Quiz3;
    private Integer Quiz4;
    private Integer Mid;
    private Integer Final;

    public GradeClass(String ID, Integer Quiz1, Integer A, Integer Quiz2, Integer Quiz3, Integer Quiz4, Integer Mid, Integer Final) {
        this.ID = ID;
        this.Quiz1 = Quiz1;
        this.A = A;
        this.Quiz2 = Quiz2;
        this.Quiz3 = Quiz3;
        this.Quiz4 = Quiz4;
        this.Mid = Mid;
        this.Final = Final;
    }
    
    /* public GradeClass(String course, String grade) {
        this.course = course;
        this.grade = grade;
    }*/

   /* public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
*/

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Integer getQuiz1() {
        return Quiz1;
    }

    public void setQuiz1(Integer Quiz1) {
        this.Quiz1 = Quiz1;
    }

    public Integer getA() {
        return A;
    }

    public void setA(Integer A) {
        this.A = A;
    }

    public Integer getQuiz2() {
        return Quiz2;
    }

    public void setQuiz2(Integer Quiz2) {
        this.Quiz2 = Quiz2;
    }

    public Integer getQuiz3() {
        return Quiz3;
    }

    public void setQuiz3(Integer Quiz3) {
        this.Quiz3 = Quiz3;
    }

    public Integer getQuiz4() {
        return Quiz4;
    }

    public void setQuiz4(Integer Quiz4) {
        this.Quiz4 = Quiz4;
    }

    public Integer getMid() {
        return Mid;
    }

    public void setMid(Integer Mid) {
        this.Mid = Mid;
    }

    public Integer getFinal() {
        return Final;
    }

    public void setFinal(Integer Final) {
        this.Final = Final;
    }
   
    
}
