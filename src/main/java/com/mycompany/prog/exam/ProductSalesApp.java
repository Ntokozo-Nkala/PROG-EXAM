/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog.exam;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProductSalesApp extends JFrame {

  private static final double VAT_RATE = 0.15;          
    private static final double COMMISSION_RATE = 0.05;   

    // Form objects
    private final JLabel lblName = new JLabel("Customer Name:");
    private final JLabel lblProduct = new JLabel("Select Product:");
    private final JLabel lblPrice = new JLabel("Enter Price:");
    private final JTextField txtName = new JTextField(15);
    private final JTextField txtPrice = new JTextField(15);
    private final JComboBox<String> cboProduct = new JComboBox<>(
            new String[] {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"});
    private final JButton btnProcess = new JButton("PROCESS");
    private final JButton btnClear = new JButton("CLEAR");
    private final JButton btnExit = new JButton("EXIT");

    private final Color panelColour = new Color(230, 240, 245);

    public ProductSalesApp() {
        setTitle("Product Sales App");
        setSize(400, 260);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        setJMenuBar(buildMenuBar());
        add(buildInputPanel(), BorderLayout.CENTER);
        add(buildButtonPanel(), BorderLayout.SOUTH);
        setVisible(true);
    }

    // Centre panel: customer name, product combo box and price
    private JPanel buildInputPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 8, 8));
        panel.setBackground(panelColour);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Sales Details"),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblProduct);
        panel.add(cboProduct);
        panel.add(lblPrice);
        panel.add(txtPrice);
        return panel;
    }

    // Bottom panel
    private JPanel buildButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 8));
        panel.setBackground(panelColour);

        btnProcess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processSale();
            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);                          
            }
        });

        panel.add(btnProcess);
        panel.add(btnClear);
        panel.add(btnExit);
        return panel;
    }

    // Menu system: File - Exit, Tools - Process, Clear
    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem itemExit = new JMenuItem("Exit");
        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);                           
            }
        });
        fileMenu.add(itemExit);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem itemProcess = new JMenuItem("Process");
        JMenuItem itemClear = new JMenuItem("Clear");

        // Each menu item calls the same method as its button
        itemProcess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processSale();
            }
        });
        itemClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        toolsMenu.add(itemProcess);
        toolsMenu.add(itemClear);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        return menuBar;
    }

    private void processSale() {
        String name = txtName.getText().trim();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the customer name ");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(txtPrice.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "The price must be a number.");
            return;
        }
        if (price <= 0) {
            JOptionPane.showMessageDialog(this, "The price must be more than 0.");
            return;
        }

        String product = (String) cboProduct.getSelectedItem();

        double vat = price * VAT_RATE;                     
        double commission = price * COMMISSION_RATE;       
        double total = price + vat + commission;           

        // Build the message text (amounts formatted to rands)
        String message = "Customer Name: " + name + "\n"
                + "Product: " + product + "\n"
               + "VAT: " +  "R "+ vat + "\n"
              + "Commission: " + "R " + commission + "\n"
                + "Total Sales: " + formatRands(total);

        JOptionPane.showMessageDialog(this, message, "Sales Details",
                JOptionPane.INFORMATION_MESSAGE);
        saveData(name, product, vat, total);
    }

    private void saveData(String name, String product, double vat, double total) {
         try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt"))){ 
            writer.println("DATA LOG");
           writer.println("*********************************");
             writer.println("Customer Name: " + name);
            writer.println("Product: " + product);
         writer.println("VAT: " + formatRands(vat));
            writer.println("Total Sales: " + "R " + total );
             writer.println("************************************");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
        }
    }

    private String formatRands(double amount) {
        return "R " + String.format("%.2f", amount);
    }

    private void clearForm() {
        txtName.setText("");
        txtPrice.setText("");
        cboProduct.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new ProductSalesApp();
    }
}