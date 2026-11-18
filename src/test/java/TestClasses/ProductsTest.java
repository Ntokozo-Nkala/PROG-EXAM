/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TestClasses;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.mycompany.prog.exam.ProductReportApp.Product;

public class ProductsTest {
    
    private final String[] names = {"Cozy Nights Monarch mattress", "Restonic Barbados mattress",
                                    "Sealy Phuket mattress"};
    private final int[][] sales = { {10, 20, 25}, {15, 10, 20}, {19, 22, 5} };
    private final Product product = new Product(names, sales);

    @Test
    void GetTotalProductSales_WithValidData_ReturnsCorrectValue() {
        assertEquals(195, product.GetTotalProductsSold());
    }

    @Test
    void GetAverageSales_WithValidData_ReturnsCorrectValue() {
        assertEquals(21.67, product.GetAverageProductSold(), 0.01);
    }
}
