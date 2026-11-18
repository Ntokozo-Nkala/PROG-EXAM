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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Question2 extends JFrame {

    // Q.1.1: arrays that store up to five inspections (parallel arrays)
    private static final int MAX_INSPECTIONS = 5;
    private static final int DEFAULT_YEARS_INDEX = 2;      // "3 Years" is the default
    private final String[] hospitalNames = new String[MAX_INSPECTIONS];
    private final String[] hospitalLocations = new String[MAX_INSPECTIONS];
    private final int[] yearsSince = new int[MAX_INSPECTIONS];
    private int inspectionCount = 0;                       // how many are stored so far

    // Q.1.1: form objects
    private final JLabel lblName = new JLabel("Customer Name:");
    private final JLabel lbProduct = new JLabel("Select Product:");
    private final JLabel lblYears = new JLabel("Enter Price:");
    private final JTextField txtName = new JTextField(15);
    private final JTextField txtLocation = new JTextField(15);
    private final JComboBox<String> cboYears = new JComboBox<>(
            new String[] {"Cozy Nights Monarch", "Restonic Barbodos", "Sealy Phuket"});
    private final JButton btnSave = new JButton("PROCESS");
    private final JButton btnPrint = new JButton("EXIT");
    private final JButton btnClear = new JButton("CLEAR");

    // The list box and its model (the model holds the lines that are shown)
    private final DefaultListModel<String> lstModel = new DefaultListModel<>();
    private final JList<String> lstInspections = new JList<>(lstModel);

    private final Color panelColour = new Color(230, 240, 245);

    public HospitalInspectionsApp() {
        setTitle("Hospital Inspections");
        setSize(400, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        cboYears.setSelectedIndex(DEFAULT_YEARS_INDEX);

        setJMenuBar(buildMenuBar());                       // Q.1.2
        add(buildInputPanel(), BorderLayout.NORTH);
        add(buildDisplayPanel(), BorderLayout.CENTER);
        add(buildButtonPanel(), BorderLayout.SOUTH);
        setVisible(true);
    }

    // Top panel: text fields, combo box and the SAVE button
    private JPanel buildInputPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 8, 8));
        panel.setBackground(panelColour);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Q.1.1: SAVE button calls the same method as the Tools - Save menu item
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveInspection();
            }
        });

        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblProduct);
        panel.add(txtPrice);
        panel.add(lblYears);
        panel.add(cboYears);
        panel.add(new JLabel(""));                         // empty cell so SAVE sits under the combo
        panel.add(btnSave);
        return panel;
    }

    // Centre panel: the list box that shows the printed inspections
    private JPanel buildDisplayPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(panelColour);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Inspections"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        panel.add(new JScrollPane(lstInspections), BorderLayout.CENTER);
        return panel;
    }

    // Bottom panel: PRINT and CLEAR buttons
    private JPanel buildButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 8));
        panel.setBackground(panelColour);

        btnPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printInspections();
            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearControls();
            }
        });

        panel.add(btnPrint);
        panel.add(btnClear);
        return panel;
    }

    // Q.1.2: menu system
    private JMenuBar buildMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem itemExit = new JMenuItem("Exit");
        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);                            // close the application
            }
        });
        fileMenu.add(itemExit);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem itemSave = new JMenuItem("Process");
        JMenuItem itemClear = new JMenuItem("Clear");

        // Each menu item calls the same method as its button
        itemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveInspection();
            }
        });
        itemPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printInspections();
            }
        });
        itemClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearControls();
            }
        });

        toolsMenu.add(itemSave);
        toolsMenu.add(itemPrint);
        toolsMenu.add(itemClear);

        menuBar.add(fileMenu);
        menuBar.add(toolsMenu);
        return menuBar;
    }

    // Q.1.1 / Q.1.2: save the details to the arrays
    private void saveInspection() {
        // Error message when more than five inspections are entered
        if (inspectionCount >= MAX_INSPECTIONS) {
            JOptionPane.showMessageDialog(this, "Maximum inspection count reached!");
            return;
        }

        String name = txtName.getText().trim();
        String location = txtLocation.getText().trim();

        // Do not store blank details
        if (name.isEmpty() || location.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the hospital name and location.");
            return;
        }

        hospitalNames[inspectionCount] = name;
        hospitalLocations[inspectionCount] = location;
        yearsSince[inspectionCount] = cboYears.getSelectedIndex() + 1;   // index 0 = 1 year
        inspectionCount++;

        // Reset the input controls ready for the next hospital
        txtName.setText("");
        txtLocation.setText("");
        cboYears.setSelectedIndex(DEFAULT_YEARS_INDEX);
        JOptionPane.showMessageDialog(this, "Inspection saved!");
    }

    // Q.1.1 / Q.1.2: print the array contents to the list box
    //MUST BE IN MESSAGE FORMAT
    private void printInspections() {
        lstModel.clear();                                  // avoid printing duplicates
        for (int i = 0; i < inspectionCount; i++) {
            lstModel.addElement("Customer Name: " + hospitalNames[i]);
            lstModel.addElement("Product: " + hospitalLocations[i]);
            lstModel.addElement("VAT: " + yearsSince[i] + " years"); //FORMAT TO RANDS
            lstModel.addElement("COMMISSION: " + yearsSince[i] + " years"); //FORMAT TO RANDS
            lstModel.addElement("TOTAL: " + yearsSince[i] + " years"); //FORMAT TO RANDS
            
            //THERE MUST BE A "OK" BUTTON HERE
        }
    }
    
    private void saveData() {
   try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt"))) {
    writer.println("DATA LOG");
    writer.println("*********************************");
    writer.println(txtDisplay.getText());
    writer.println("*********************************");
    JOptionPane.showMessageDialog(this, "Saved to data.txt");
} catch (IOException e) {
    JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
}
}

    // Q.1.1 / Q.1.2: clear the controls (the saved array data is kept)
    private void clearControls() {
        txtName.setText("");
        txtLocation.setText("");
        cboYears.setSelectedIndex(DEFAULT_YEARS_INDEX);
        lstModel.clear();
    }

    public static void main(String[] args) {
        new Question2();
    }
}

