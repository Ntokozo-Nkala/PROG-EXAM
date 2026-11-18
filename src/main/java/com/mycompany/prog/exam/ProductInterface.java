/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog.exam;


import java.util.Scanner;

public class ProductInterface {

     public void main(String[] args) {
        ConsoleController controller = new ConsoleController();
        controller.start();
    }
     
    public interface IProductCalculations {
        double getVat();
        double getCommission();
        double getTotal();
    }

    public static class ProductCalculation implements IProductCalculations {
        private static final double VAT_RATE = 0.15;          
        private static final double COMMISSION_RATE = 0.05;   

        private final double price;        

        public ProductCalculation(double price) {
            this.price = price;
        }

        @Override
        public double getVat() {
            return price * VAT_RATE;
        }

        @Override
        public double getCommission() {
            return price * COMMISSION_RATE;
        }

        @Override
        public double getTotal() {
            return price + getVat() + getCommission();
        }
    }

    public static class Sale {
        private final String customerName;
        private final String productName;
        private final IProductCalculations calculations;

        public Sale(String customerName, String productName, double price) {
            this.customerName = customerName;
            this.productName = productName;
            this.calculations = new ProductCalculation(price);
        }

        public String getCustomerName() { 
            return customerName; 
        }
        public String getProductName() { 
            return productName; 
        }
        public double getVat(){ 
            return calculations.getVat(); 
        }
        public double getCommission() { 
            return calculations.getCommission(); 
        }
        public double getTotal() { 
            return calculations.getTotal(); 
        }
    }

    public static class ConsoleController {
        private final Scanner scanner = new Scanner(System.in);

        public void start() {
            System.out.print("Enter Customer Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Product Name: ");
            String product = scanner.nextLine();

            double price = promptForPrice();

            Sale sale = new Sale(name, product, price);
            displaySaleDetails(sale);
            scanner.close();
        }

        //PROMPTS USER FOR INPUT/PRICE
        private double promptForPrice() {
            System.out.print("Enter Product Price: ");
            return Double.parseDouble(scanner.nextLine());
        
        }

        //PRINTS REPORT
        private void displaySaleDetails(Sale sale) {
            System.out.println("\n******* Sales Details *****");
            System.out.println("Customer Name: " + sale.getCustomerName());
            System.out.println("Product: " + sale.getProductName());
            System.out.println("VAT: " + formatRands(sale.getVat()));
            System.out.println("Commission: " + formatRands(sale.getCommission()));
            System.out.println("Total Sales: " + formatRands(sale.getTotal()));
            System.out.println("***************************");
        }

        private String formatRands(double amount) {
            return "R " + String.format("%.2f", amount);
        }
    }

  
}