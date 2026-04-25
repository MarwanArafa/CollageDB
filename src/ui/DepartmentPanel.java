package ui;

import javax.swing.*;
import java.awt.*;

public class DepartmentPanel extends JPanel {
    
    private JLabel lblDeptName, lblLocation;

    public JTextField txtDeptName;
    public JTextField txtLocation;

    public JButton btnSave, btnSearch, btnDelete, btnUpdate;
    public JTextArea txtArea;

    public DepartmentPanel() {
        setLayout(new GridLayout(5, 2, 5, 5));

        lblDeptName = new JLabel("Department Name:");
        txtDeptName = new JTextField();
        add(lblDeptName); add(txtDeptName);

        lblLocation = new JLabel("Location:");
        txtLocation = new JTextField();
        add(lblLocation); add(txtLocation);

        btnSave   = new JButton("Save");
        btnSearch = new JButton("Search");
        btnDelete = new JButton("Delete");
        btnUpdate = new JButton("Update");

        add(btnSave);   add(btnSearch);
        add(btnDelete); add(btnUpdate);

        add(new JLabel("Results:"));
        txtArea = new JTextArea();
        txtArea.setEditable(false);
        add(new JScrollPane(txtArea));

        btnSave.addActionListener(e -> { });
        btnSearch.addActionListener(e -> { });
        btnDelete.addActionListener(e -> { });
        btnUpdate.addActionListener(e -> { });
    }
}
