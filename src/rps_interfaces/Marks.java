/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rps_interfaces;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author HP
 */
public class Marks extends RecursiveTreeObject<Marks> {
    private String S_ID;
    int attendance,mid,quiz1,quiz2,quiz3,quiz4,finalmark,grade;

    public String getS_ID() {
        
        return S_ID;
    }

    public void setS_ID(String S_ID) {
        this.S_ID =S_ID;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getQuiz1() {
        return quiz1;
    }

    public void setQuiz1(int quiz1) {
        this.quiz1 = quiz1;
    }

    public int getQuiz2() {
        return quiz2;
    }

    public void setQuiz2(int quiz2) {
        this.quiz2 = quiz2;
    }

    public int getQuiz3() {
        return quiz3;
    }

    public void setQuiz3(int quiz3) {
        this.quiz3 = quiz3;
    }

    public int getQuiz4() {
        return quiz4;
    }

    public void setQuiz4(int quiz4) {
        this.quiz4 = quiz4;
    }

    public int getFinalmark() {
        return finalmark;
    }

    public void setFinalmark(int finalmark) {
        this.finalmark = finalmark;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Marks(String S_ID, int attendance, int mid, int quiz1, int quiz2, int quiz3, int quiz4, int finalmark) {
        this.S_ID = S_ID;
        this.attendance = attendance;
        this.mid = mid;
        this.quiz1 = quiz1;
        this.quiz2 = quiz2;
        this.quiz3 = quiz3;
        this.quiz4 = quiz4;
        this.finalmark = finalmark;
        this.grade = grade;
    }
    
}
