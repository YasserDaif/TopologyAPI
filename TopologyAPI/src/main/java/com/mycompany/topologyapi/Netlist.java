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
public class Netlist {
    private String  t1, t2, drain, gate, source;
    private ArrayList<String> Nodes = new ArrayList<String>();
    
    
    public Netlist(String t1, String t2) {
        this.t1 = t1;
        this.t2 = t2;
        Nodes.add(this.t1);
        Nodes.add(this.t2);
    }

    public Netlist(String drain, String gate, String source) {
        this.drain = drain;
        this.gate = gate;
        this.source = source;
        Nodes.add(this.drain);
        Nodes.add(this.gate);
        Nodes.add(this.source);
        
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getDrain() {
        return drain;
    }

    public ArrayList<String> getNodes() {
        return Nodes;
    }

    public void setNodes(ArrayList<String> Nodes) {
        this.Nodes = Nodes;
    }

    public void setDrain(String drain) {
        this.drain = drain;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
