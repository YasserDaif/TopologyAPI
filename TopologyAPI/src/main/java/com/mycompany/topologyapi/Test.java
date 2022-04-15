/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.topologyapi;

import static com.mycompany.topologyapi.AllTopology.TopologyList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yasser
 */
public class Test {

    public Test() {
        
    }
 
    
    public boolean APILevelTest(){
        
        String FileName = "topology.json";
        AllTopology MyTopology = new AllTopology(FileName);
        
        // Read Json File Test.
        System.out.println("Read Json File Test ...");
        try {
            TopologyList = MyTopology.readJSON();                       // read Json file ("topology.json").
            if("WriteTest".equals(TopologyList.get(0).getTopid())){
                System.out.println("  Read Json File Test Passed");       
            }else{
                System.out.println("  Read Json File Test Fail");      
                return false;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // Write a given topology to a json file test.
        System.out.println("Write a given topology to a json file test ...");
        try {
            if(MyTopology.writeJSON("WriteTest")){                             // This function search for the given topology id in the memory and write it to a json file ("GSONOutPut.json").
                System.out.println("  File written successfully");
            }else{
                System.out.println("  Fail to write the File");
                return false;
            }             
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Topologies query test.
        System.out.println("Topologies query test ...");
        ArrayList<Topology> TopologyQuery =  MyTopology.queryTopologies();     // Get all Topologies in the memory.
        if(!"WriteTest".equals(TopologyQuery.get(0).getTopid())){System.out.println("  Topologies query test Fail");return false;}
        if(!"DeleteTest".equals(TopologyQuery.get(1).getTopid())){System.out.println("  Topologies query test Fail");return false;}
        if(!"top1".equals(TopologyQuery.get(2).getTopid())){System.out.println("  Topologies query test Fail");return false;}
        if(!"top2".equals(TopologyQuery.get(3).getTopid())){System.out.println("  Topologies query test Fail");return false;}
        if(!"top3".equals(TopologyQuery.get(4).getTopid())){System.out.println("  Topologies query test Fail");return false;}
        System.out.println("  Topologies query test Passed");
        
        
        // Delete a topology test.
        System.out.println("Delete a topology test ...");
        ArrayList<Topology> UpdatedTopology = MyTopology.deleteTopology("DeleteTest");     // Delete a topology named "DeleteTest"
        TopologyQuery =  MyTopology.queryTopologies();                                     // Get all Topologies in the memory.
        for(int t=0; t<TopologyQuery.size(); t++){
            if(UpdatedTopology.get(t).getTopid().equals("DeleteTest"))
            {
                System.out.println("  Delete a topology test Fails");
                return false;                
            }            
        }
        System.out.println("  Delete a topology test Passed");
        
        
        // Get devices in a given topology test.
        System.out.println("Get devices in a given topology test ...");
        ArrayList<Component> devices = MyTopology.queryDevices("top3");                    // Get a list of all devices in topology 3 ("top3").
        if(!devices.get(0).getId().equals("r3")){System.out.println("  Get devices in a given topology test Fail");return false;}
        if(!devices.get(1).getId().equals("r4")){System.out.println("  Get devices in a given topology test Fail");return false;}
        if(!devices.get(2).getId().equals("m3")){System.out.println("  Get devices in a given topology test Fail");return false;}
        System.out.println("  Get devices in a given topology test Passed");

        
        // Devices connected with the same node test.
        System.out.println("Devices connected with the same node test...");
        ArrayList<Component> comp = MyTopology.queryDevicesWithNetlistNode("top3", "n1");  // Seearch in top3 for devices connected with node "n1".
        if(!comp.get(0).getId().equals("r4")){System.out.println("  Devices connected with the same node test Fail");return false;}
        if(!comp.get(1).getId().equals("m3")){System.out.println("  Devices connected with the same node test Fail");return false;}
        System.out.println("  Devices connected with the same node test Passed");
        return true;
    }
    
}
