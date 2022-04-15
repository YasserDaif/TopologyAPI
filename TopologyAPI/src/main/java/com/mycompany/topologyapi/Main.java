package com.mycompany.topologyapi;

import java.io.FileNotFoundException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author yasser
 */

public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Start Main ...");
        Test MyTest = new Test();
        if(MyTest.APILevelTest()){
            System.out.println("");
            System.out.println("________________________________________________");
            System.out.println("");
            System.out.println("API Level Test Passed");           
            System.out.println("________________________________________________");
            System.out.println("");
        }else{
            System.out.println("");
            System.out.println("________________________________________________");
            System.out.println("");
            System.out.println("API Level Test Fails");
            System.out.println("________________________________________________");
            System.out.println("");
        }
    }    
}
