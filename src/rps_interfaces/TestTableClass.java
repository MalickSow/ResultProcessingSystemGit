/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rps_interfaces;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Myriam
 */
public class TestTableClass {
    private String id;
    private Integer atd;

    public TestTableClass(String id, Integer atd) {
        this.id=id;
        this.atd = atd;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public Integer getAtd() {
        return atd;
    }

    public void setAtd(Integer atd) {
        this.atd = atd;
    }
    
}
