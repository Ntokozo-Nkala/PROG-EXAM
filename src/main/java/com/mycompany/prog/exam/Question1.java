/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog.exam;

import java.util.Scanner;
public class Question1 {
       
    Scanner scanner = new Scanner(System.in);
    //ARRAYS 
    int[][] salesQuantities = new int[3][3];
    int[] salesTotal = new int[3];
    String[] company = {"Cozy Nights Monarch matteress","Restonic Barbados mattress", "Sealy Phuket mattress"};

    public void main(String[] args){
        Question1 report = new Question1();
        report.enterStock();
        report.salesReport();
        report.totalSales();
        report.averageSales();
        report.maxSales();
        report.mostPopular();
    }
    
    //PROMPTS USER TO ENTER THE SALES OF THE MONTH
    public void enterStock(){
        for(int i = 0; i < company.length; i++){
        for(int month = 0; month < 3; month++){
            System.out.print("Enter number of sales for month " + (month + 1) + " " + company[i] + ": ");
            salesQuantities[i][month] = scanner.nextInt();
            salesTotal[i] += salesQuantities[i][month];
        }
    }
}
    
     //PRINTS THE REPORT
    public void salesReport(){
        System.out.print("\nPRODUCT SALES REPORT 2026");
        System.out.print("\n*********************************");
       System.out.print("\nTOTAL PRODUCT SALES: " + totalSales());
              System.out.print("\nAVERAGE PRODUUCT SALES: " + averageSales());
       System.out.print("\nMOST POPULAR PRODUCT: " + mostPopular());

    }
    
    //CALCULATES THE TOTAL SALES
    public int totalSales(){
        int total = 0;
        for(int i = 0; i < salesQuantities.length; i++){
            for( int x = 0; x < salesQuantities[i].length; x++){
                total += salesQuantities[i][x]; 
            }
        }
        return total;
    }
    
    //CALCULATES AVERAGE
    public int averageSales(){
        int total = 0;
        int count = 0;
        for(int i = 0; i < salesQuantities.length; i++){
       for( int x = 0; x < salesQuantities[i].length; x++){
           total += salesQuantities[i][x];
           count++; 
            }
        }
        int average = (int) Math.round((double)total / count); 
     return average;
    }
    
    //CALCULATES MAX SALES
    public int maxSales(){
        int maximum = salesQuantities[0][0]; 
         for(int i = 0; i < salesQuantities.length; i++){ 
       for( int x = 0; x < salesQuantities[i].length; x++){ 
           if(salesQuantities[i][x] > maximum){ 
               maximum = salesQuantities[i][x]; 
           }
       }
    }
         return maximum;
         }
    //SHOWS THE MOST POPULAR 
public String mostPopular(){
     int maxTotal = salesTotal[0];
    int maxIndex = 0;
    for(int i = 1; i < company.length; i++){
        if(salesTotal[i] > maxTotal){
            maxTotal = salesTotal[i];
            maxIndex = i;
        }
    }
    return company[maxIndex] + "(" + maxTotal + ")";  
}
}
    
