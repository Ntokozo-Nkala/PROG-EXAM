/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog.exam;

import java.util.Scanner;
public class Question1 {
       
    Scanner scanner = new Scanner(System.in);
    int[][] salesQuantities = new int[3][3];
    int[] salesTotal = new int[3];
    String[] company = {"Cozy Nights Monarch matteress","Restonic Barbados mattress", "Sealy Phuket mattress"};

    public void main(String[] args){
        Question1 report = new Question1();
        report.salesReport();
        report.totalSales();
        report.averageSales();
        report.maxSales();
        report.mostPopular();
    }
    
    public void enterStock(){
        for(int i = 0; i < company.length; i++){
        System.out.print("Enter number of sales for month 1 " + company[i] + ": ");
        salesQuantities[0][0] = scanner.nextInt();
        
        System.out.print(" Enter number of sales for month 2 " + company[i] + ": ");
        salesQuantities[1][0] = scanner.nextInt();
        
        System.out.print("Enter number of sales for month 3 " + company[i] + ": ");
        salesQuantities[2][0] = scanner.nextInt();
        
        salesTotal[i] = salesQuantities[i][0] + salesQuantities[i][1] + salesQuantities[i][2];
    }
}
    
     
    public void salesReport(){
        System.out.print("\nPRODUCT SALES REPORT - 2026");
        System.out.print("\n*********************************");
       System.out.print("\nTOTAL PRODUCT SALES: " + totalSales());
              System.out.print("\nAVERAGE PRODUUCT SALES: " + averageSales());
       System.out.print("\nMOST POPULAR PRODUCT: " + mostPopular() + maxSales());
        System.out.print("\n--------------------------------");

    }
    
    public int totalSales(){
        int total = 0;
        for(int i = 0; i < salesQuantities.length; i++){
            for( int x = 0; x < salesQuantities[i].length; x++){
                total += salesQuantities[i][x]; //ADD ALL THE NUMBERS IN BOTH ROW AND COL
            }
        }
        return total;
    }
    
    public int averageSales(){
        int total = 0;
        int count = 0;
        for(int i = 0; i < salesQuantities.length; i++){
       for( int x = 0; x < salesQuantities[i].length; x++){
           total += salesQuantities[i][x];
           count++; //COUNTS THE NUMBERS IN THE ARRAY
            }
        }
        int average = (int) Math.round((double)total / count); //THIS ROUNDS THE NUMBER OFF TO A WHOLE NUMBER 
     return average;
    }
    
    public int maxSales(){
        int maximum = salesQuantities[0][0]; 
         for(int i = 0; i < salesQuantities.length; i++){ //TAKE NOTE OF THE I AND X, I = ROW, X = COL
       for( int x = 0; x < salesQuantities[i].length; x++){ 
           if(salesQuantities[i][x] > maximum){ 
               maximum = salesQuantities[i][x]; //STORE THE NUMBER FOUND AS MAXIMUM/BIGGEST
           }
       }
    }
         return maximum;
         }
    
public void mostPopular(){
       //WE START WITH THE FIRST CITY'S TOTAL
       int maxCompany = salesTotal[0];
       //STORE THE INDEX OF THE COMPANY WITH THE MOST ACCIIDENTS
       int maxCompanyIndex = 0;
       
       //USING THE CITIES ARRAY BECAUSE WE ARE TRYING TO FIND ONE MAX CITY
       for( int i = 1; i < company.length; i++){ //ALWAYS START AT 1
           
           if(salesTotal[i] > maxCompany){
               maxCompany = salesTotal[i];
               maxCompanyIndex = i;
           }
       }
       System.out.println();
       //PRINTS MOST POPULAR PRODUCT
       System.out.println("MOST POPULAR PRODUCT: " + company [maxCompanyIndex] + maxSales());
   }


}
    
