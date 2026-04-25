package ui;

import javax.swing.*;
import java.awt.*;

public class InstructorPanel extends JPanel {

    private JLabel lblId, lblFname, lblLname, lblPhone, lblDeptName, lblDeptLocation;

    public JTextField txtInstructorId;
    public JTextField txtFname;
    public JTextField txtLname;
    public JTextField txtPhone;
    public JTextField txtDeptName;
    public JTextField txtDeptLocation;

    public JButton btnSave, btnSearch, btnDelete, btnUpdate;
    public JTextArea txtArea;

    public InstructorPanel() {
        setLayout(new GridLayout(9, 2, 5, 5));

        lblId = new JLabel("Instructor ID:");
        txtInstructorId = new JTextField();
        add(lblId); add(txtInstructorId);

        lblFname = new JLabel("First Name:");
        txtFname = new JTextField();
        add(lblFname); add(txtFname);

        lblLname = new JLabel("Last Name:");
        txtLname = new JTextField();
        add(lblLname); add(txtLname);

        lblPhone = new JLabel("Phone:");
        txtPhone = new JTextField();
        add(lblPhone); add(txtPhone);

        lblDeptName = new JLabel("Dept Name:");
        txtDeptName = new JTextField();
        add(lblDeptName); add(txtDeptName);

        lblDeptLocation = new JLabel("Dept Location:");
        txtDeptLocation = new JTextField();
        add(lblDeptLocation); add(txtDeptLocation);

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
