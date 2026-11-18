/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog.exam;
import java.util.Scanner;


public class Interface {
    Scanner scanner = new Scanner(System.in);
    //CITIES, SALES AND SALES TOTALS ARRAYS
    int[] totalProductsSold = new int[3];
    String[] mostPopularProduct = new String[3];
    int[][] salesQuantities = new int[3][3];

    //INTERFACE
    public interface IProduct{
         int GetTotalProductsSold();
         double GetAverageProductSold();
         String GetMostPopularProduct();
         public void displayReport();
    }
    //
    public abstract class Console implements IProducts{
        private String consoleType;
        private String store;
        private int totalSales;
        
        public Console(String consoleType, String store, int totalSales){
            this.consoleType = consoleType;
            this.store = store;
            this.totalSales = totalSales;
        }
        public String getConsoleType(){
            return consoleType;
        }
        public int getTotalSales(){
            return totalSales;
        }
        public String getStore(){
            return store;
        }
        
        @Override
        public void displayReport(){
        System.out.print("\nPRODUCT SALES REPORT - 2026");
        System.out.print("\n*********************************");
       System.out.print("\nTOTAL PRODUCT SALES: " + totalSales());
              System.out.print("\nAVERAGE PRODUUCT SALES: " + averageSales());
       System.out.print("\nMOST POPULAR PRODUCT: " + mostPopular() + maxSales());
        System.out.print("\n--------------------------------");

    
    }
    
    
    public class ConsoleSales extends Console{
    public ConsoleSales(String consoleType, String store, int totalSales){
        super(consoleType, store, totalSales);
    }
       
    
}

