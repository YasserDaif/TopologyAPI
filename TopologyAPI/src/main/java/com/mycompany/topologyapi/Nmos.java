/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.topologyapi;

/**
 *
 * @author yasser
 */
public class Nmos extends Component{
    
    public Nmos(String nmos_id, double nmos_def, double nmos_Min, double nmos_Max, String nmos_drain, String nmos_gate, String nmos_source){
        super("nmos", nmos_id, new ComponentValue(nmos_Max, nmos_Min, nmos_Max), new Netlist(nmos_drain, nmos_gate, nmos_source));
    }
    
}
