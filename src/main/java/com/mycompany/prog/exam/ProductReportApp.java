/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog.exam;
import java.util.Scanner;


public class ProductReportApp {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] productNames = {"Cozy Nights Monarch mattress",
                                 "Restonic Barbados mattress",
                                 "Sealy Phuket mattress"};
        int[][] salesQuantities = new int[3][3];

        // Capture 3 months of sales 
        for (int i = 0; i < productNames.length; i++) {
            for (int month = 0; month < salesQuantities[i].length; month++) {
                System.out.print("Enter number of sales for month " + (month + 1)
                        + " " + productNames[i] + ": ");
                salesQuantities[i][month] = scanner.nextInt();
            }
        }

        IProducts product = new Product(productNames, salesQuantities);
        PrintProduct printer = new PrintProduct(product);
        printer.printReport();
    }

    public interface IProducts {
        int GetTotalProductsSold();
        double GetAverageProductSold();
        String GetMostPopularProduct();
    }

    public static class Product implements IProducts {
        private final int[][] salesQuantities;  
        private final String[] productNames;    

        public Product(String[] productNames, int[][] salesQuantities) {
            this.productNames = productNames;
            this.salesQuantities = salesQuantities;
        }

        // Add every numbers in the array
        @Override
        public int GetTotalProductsSold() {
            int total = 0;
            for (int i = 0; i < salesQuantities.length; i++) {           
                for (int x = 0; x < salesQuantities[i].length; x++) {    
                    total += salesQuantities[i][x];
                }
            }
            return total;
        }

        // Average per product per month 
        @Override
        public double GetAverageProductSold() {
            int count = 0;
            for (int i = 0; i < salesQuantities.length; i++) {
                count += salesQuantities[i].length;                  
            }
            return (double) GetTotalProductsSold() / count;            
        }

        // Product with the highest total sales
        @Override
        public String GetMostPopularProduct() {
            int maxTotal = getProductTotal(0);     
             int maxIndex = 0;
            for (int i = 1; i < productNames.length; i++) {          
                if (getProductTotal(i) > maxTotal) {
                    maxTotal = getProductTotal(i);
                    maxIndex = i;
                }
            }
            return productNames[maxIndex] + " " + maxTotal ;
        }

        // total sales of one product (one row of the 2D array)
        private int getProductTotal(int row) {
            int total = 0;
            for (int x = 0; x < salesQuantities[row].length; x++) {
                total += salesQuantities[row][x];
            }
            return total;
        }
    }

    //prints the report 
    public static class PrintProduct {

        private final IProducts products;      

        public PrintProduct(IProducts products) {
            this.products = products;
        }

        public void printReport() {
            System.out.println("\nPRODUCT SALES REPORT 2026");
            System.out.println("****************************");
            System.out.println("TOTAL PRODUCT SALES: " + products.GetTotalProductsSold());
            System.out.println("AVERAGE PRODUCT SALES: " + Math.round(products.GetAverageProductSold()));
            System.out.println("MOST POPULAR PRODUCT: " + products.GetMostPopularProduct());
        }
    }

    
}