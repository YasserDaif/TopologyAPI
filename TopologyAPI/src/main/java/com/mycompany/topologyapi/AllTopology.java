/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.topologyapi;


import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author yasser
 */
public class AllTopology {
    static ArrayList<Topology> TopologyList = new ArrayList<>();
    static Gson gson = new Gson();
    String FileName;

    public AllTopology(String FileName) {
        this.FileName=FileName;
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
    public ArrayList<Topology> readJSON() throws FileNotFoundException{
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
    public boolean writeJSON(String TopologyID) throws IOException{
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
    public ArrayList<Topology> queryTopologies(){
        ArrayList<Topology> TopologyQuery = new ArrayList<Topology>();
        for (int i=0; i<TopologyList.size(); i++){
            TopologyQuery.add(TopologyList.get(i));
        }
        return TopologyQuery;
    }
    
    
    // Delete a given topology from memory
    // This function takes a topology ID and delete it from the memory.
    // It return an Updated TopologyList.
    public ArrayList<Topology> deleteTopology(String TopologyID){
        int TopologyIndex = getTopologyIndex(TopologyID);                       // Get TopologyID index
        if(TopologyIndex != -1){                                                // If found remove it fromt the list and return the updated list.
            TopologyList.remove(TopologyIndex);
        }
        return TopologyList;
    }
    
    
    //This function takes a topologyID and it returns all devices within it.
    public ArrayList<Component> queryDevices(String TopologyID){
        int TopologyIndex = getTopologyIndex(TopologyID);                       // Get TopologyID index
        if(TopologyIndex != -1){
            return TopologyList.get(TopologyIndex).getComponents();             // If found get the list of devices in it and return it.
        }
        return null;                                                            // It return null if a topology is not found..
    }
    
    
    
    // This function takes a topology id and a node id, and it returns a list of devices within this topology that are connected to the given node.
    public ArrayList<Component> queryDevicesWithNetlistNode(String TopologyID, String NetlistNodeID){
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
