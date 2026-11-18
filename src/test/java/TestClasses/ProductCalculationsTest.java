/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TestClasses;
import com.mycompany.prog.exam.ProductInterface;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ProductCalculationsTest {

    @Test
    void GetVat_WithValidDara_ReturnsCorrectValue() {
        double price = 100.0;
        ProductInterface.IProductCalculations calculation = new ProductInterface.ProductCalculation(price);
        double actualVat = calculation.getVat();
        assertEquals(15.0, actualVat, 0.001, "VAT calculation is incorrect");
    }

    @Test
    void GetTotal_WithValidData_RetrunsCorrectValue() {
        double price = 100.0;
        ProductInterface.IProductCalculations calculation = new ProductInterface.ProductCalculation(price);
        double actualTotal = calculation.getTotal();
        assertEquals(120.0, actualTotal, 0.001, "Total sales calculation is incorrect");
    }
}