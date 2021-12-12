/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.topologyapi;

import java.util.ArrayList;

/**
 *
 * @author yasser
 */
public class Topology {
    private String Topid;
    ArrayList<Component> components;

    public Topology(String Topid, ArrayList<Component> components) {
        this.Topid = Topid;
        this.components = components;
    }

    public String getTopid() {
        return Topid;
    }

    public void setTopid(String Topid) {
        this.Topid = Topid;
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<Component> components) {
        this.components = components;
    }    
}
