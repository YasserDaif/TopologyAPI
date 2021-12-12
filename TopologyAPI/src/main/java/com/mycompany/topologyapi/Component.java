/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.topologyapi;

/**
 *
 * @author yasser
 */
public class Component {
    private String type, id;
    ComponentValue componentValue;
    Netlist netlist;

    public Component(String type, String id, ComponentValue componentValue, Netlist netlist) {
        this.type = type;
        this.id = id;
        this.componentValue = componentValue;
        this.netlist = netlist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ComponentValue getComponentValue() {
        return componentValue;
    }

    public void setComponentValue(ComponentValue componentValue) {
        this.componentValue = componentValue;
    }

    public Netlist getNetlist() {
        return netlist;
    }

    public void setNetlist(Netlist netlist) {
        this.netlist = netlist;
    }
}
