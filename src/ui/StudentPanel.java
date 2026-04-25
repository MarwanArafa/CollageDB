package ui;

import javax.swing.*;
import java.awt.*;

public class StudentPanel extends JPanel {

    private JLabel lblId, lblFirstName, lblLastName, lblPhoneNumber;

    public JTextField txtStudentId;
    public JTextField txtFname;
    public JTextField txtLname;
    public JTextField txtPhone;

    public JButton btnSave, btnSearch, btnDelete, btnUpdate;
    public JTextArea txtArea;

    public StudentPanel() {
        setLayout(new GridLayout(7, 2, 5, 5));

        lblId = new JLabel("Student ID:");
        txtStudentId = new JTextField();
        add(lblId); add(txtStudentId);

        lblFirstName = new JLabel("First Name:");
        txtFname = new JTextField();
        add(lblFirstName); add(txtFname);

        lblLastName = new JLabel("Last Name:");
        txtLname = new JTextField();
        add(lblLastName); add(txtLname);

        lblPhoneNumber = new JLabel("Phone:");
        txtPhone = new JTextField();
        add(lblPhoneNumber); add(txtPhone);

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

        // ActionListeners wired in MainFrame
        btnSave.addActionListener(e -> { });
        btnSearch.addActionListener(e -> { });
        btnDelete.addActionListener(e -> { });
        btnUpdate.addActionListener(e -> { });
    }
}
