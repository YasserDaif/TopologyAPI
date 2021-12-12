/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.topologyapi;

/**
 *
 * @author yasser
 */
public class ComponentValue {
    private double Default, Min, Max;

    public ComponentValue(double Default, double Min, double Max) {
        this.Default = Default;
        this.Min = Min;
        this.Max = Max;
    }

    public double getDefault() {
        return Default;
    }

    public void setDefault(double Default) {
        this.Default = Default;
    }

    public double getMin() {
        return Min;
    }

    public void setMin(double Min) {
        this.Min = Min;
    }

    public double getMax() {
        return Max;
    }

    public void setMax(double Max) {
        this.Max = Max;
    }
}
