/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.topologyapi;

/**
 *
 * @author yasser
 */
public class Resistor extends Component{
    public Resistor(String Res_id, double Res_def, double Res_Min, double Res_Max, String Res_t1,String Res_t2){
        super("resistor", Res_id, new ComponentValue(Res_def, Res_Min, Res_Max), new Netlist(Res_t1, Res_t2));
    }
}
