package com.mycompany.topologyapi;
import com.google.gson.Gson;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yasser
 */

public class Main {
    static ArrayList<Topology> TopologyList = new ArrayList<Topology>();
    static Gson gson = new Gson();
    
    public static void main(String[] args) {
        System.out.println("Start main ...");
        String FileName = "topology.json";
        
        // Read Json File Test.
        System.out.println(" Read Json File Test ...");
        try {
            TopologyList = readJSON(FileName);                      // read Json file ("topology.json").
            System.out.println(TopologyList.get(0).getTopid());     // Print Topology ID of the first topology in the file.
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        // Write a given topology to a json file test.
        System.out.println("Write a given topology to a json file test ...");
        try {
            if(writeJSON("WriteTest")){                             // This function search for the given topology id in the memory and write it to a json file ("GSONOutPut.json").
                System.out.println("File written successfully");
            }             
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // Topologies query test.
        System.out.println("Topologies query test ...");
        ArrayList<Topology> TopologyQuery =  queryTopologies();     // Get all Topologies in the memory.
        for(int t=0; t<TopologyQuery.size(); t++){
            System.out.println(TopologyQuery.get(t).getTopid());    // print all topology IDs
        }
        
        // Delete a topology test.
        System.out.println("Delete a topology test ...");
        ArrayList<Topology> UpdatedTopology = deleteTopology("DeleteTest");     // Delete a topology named "DeleteTest"
        TopologyQuery =  queryTopologies();                                     // Get all Topologies in the memory.
        for(int t=0; t<TopologyQuery.size(); t++){
            System.out.println(TopologyQuery.get(t).getTopid());                // print all topology IDs
        }
        
        
        // Get devices in a given topology test.
        System.out.println("Get devices in a given topology test ...");
        ArrayList<Component> devices = queryDevices("top3");                    // Get a list of all devices in topology 3 ("top3").
        for(int d=0; d<devices.size(); d++){
            System.out.println(devices.get(d).getId());                         // print all devices IDs
        }
        
        
        // Devices connected with the same node test.
        System.out.println("Devices connected with the same node test...");
        ArrayList<Component> comp = queryDevicesWithNetlistNode("top3", "n1");  // Seearch in top3 for devices connected with node "n1".
        for(int c=0; c<comp.size(); c++){
            System.out.println(comp.get(c).getId());                            // print all devices IDs.
        }
        
    }
    

    // This is a helper function that takes a topology id and return its index in the TopologyList
    public static int getTopologyIndex(String TopologyID)
    {
        for(int i=0; i<TopologyList.size(); i++){
            if(TopologyList.get(i).getTopid().equals(TopologyID))
            {
                return i;
            }
        }
        return -1;
    }
    
    // Read File Function
    // This function is used to read a json file and convert it to a topology list object.
    // It takes the FileName as input argument, and return a TopologyList which is a list that contains all topologies in that file.
    public static ArrayList<Topology> readJSON(String FileName) throws FileNotFoundException{
        Topology[] JSONList;
        FileReader fileReader = new FileReader(FileName);           // Create a fileReader object that takes all text in a file.
        JSONList = gson.fromJson(fileReader, Topology[].class );    // Map the data from the file to a JSON object with the format of Topology class.
        
        for(int i=0; i<JSONList.length; i++){                       // Convert the data from Array to ArrayList
            TopologyList.add(JSONList[i]);
        }
        return TopologyList;
    }
    
    
    // Write to a file
    // This function search for the given topology id in the memory and write it to a json file ("GSONOutPut.json").
    // The Function return true if the topology id is found and the file is created, and return false if the file didn't created.
    public static boolean writeJSON(String TopologyID) throws IOException{
         FileWriter fileWriter = new FileWriter("GSONOutPut.json");             // Create an output file to write in.
         int i = getTopologyIndex(TopologyID);                                  // get the topology index
         if(i != -1){
            fileWriter.write(gson.toJson(TopologyList.get(i)));                 // if found write it in the output file.
            fileWriter.flush();
            return true;
         }
         return false;
    }
    
    
    // Topologies query
    // This function return an ArrayList of all topologies in the memory.
    public static ArrayList<Topology> queryTopologies(){
        ArrayList<Topology> TopologyQuery = new ArrayList<Topology>();
        for (int i=0; i<TopologyList.size(); i++){
            TopologyQuery.add(TopologyList.get(i));
        }
        return TopologyQuery;
    }
    
    
    // Delete a given topology from memory
    // This function takes a topology ID and delete it from the memory.
    // It return an Updated TopologyList.
    public static ArrayList<Topology> deleteTopology(String TopologyID){
        int TopologyIndex = getTopologyIndex(TopologyID);                       // Get TopologyID index
        if(TopologyIndex != -1){                                                // If found remove it fromt the list and return the updated list.
            TopologyList.remove(TopologyIndex);
        }
        return TopologyList;
    }
    
    
    //This function takes a topologyID and it returns all devices within it.
    public static ArrayList<Component> queryDevices(String TopologyID){
        int TopologyIndex = getTopologyIndex(TopologyID);                       // Get TopologyID index
        if(TopologyIndex != -1){
            return TopologyList.get(TopologyIndex).getComponents();             // If found get the list of devices in it and return it.
        }
        return null;                                                            // It return null if a topology is not found..
    }
    
    
    
    // This function takes a topology id and a node id, and it returns a list of devices within this topology that are connected to the given node.
    public static ArrayList<Component> queryDevicesWithNetlistNode(String TopologyID, String NetlistNodeID){
        ArrayList<Component> DevicesList = new ArrayList<Component>();
        int TopologyIndex = getTopologyIndex(TopologyID);                       // Get Topology index.
        if(TopologyIndex == -1){
            return null;                                                        // If not fount a null will be returned.                                
        }else{                                                                  // If found it will return all devices that are connected to the given node.
            for(int comp=0; comp<TopologyList.get(TopologyIndex).getComponents().size(); comp++){
                for(int nodes=0; nodes<TopologyList.get(TopologyIndex).getComponents().get(comp).getNetlist().getNodes().size(); nodes++){
                    String node = TopologyList.get(TopologyIndex).getComponents().get(comp).getNetlist().getNodes().get(nodes);
                    if(node.equals(NetlistNodeID)){                        
                        DevicesList.add(TopologyList.get(TopologyIndex).getComponents().get(comp));
                    }                
                }
            }            
        }
        return DevicesList;
    }
}
