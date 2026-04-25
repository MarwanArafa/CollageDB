package ui;

import javax.swing.*;
import java.awt.*;

public class CoursePanel extends JPanel {

    private JLabel lblCourseId, lblCourseName, lblDuration, lblDeptName, lblDeptLocation, lblInstructorId;

    public JTextField txtCourseId;
    public JTextField txtCourseName;
    public JTextField txtDuration;
    public JTextField txtDeptName;
    public JTextField txtDeptLocation;
    public JTextField txtInstructorId;   // ADDED — needed for insertCourse()

    public JButton btnSave, btnSearch, btnDelete, btnUpdate;
    public JTextArea txtArea;

    public CoursePanel() {
        setLayout(new GridLayout(9, 2, 5, 5));

        lblCourseId = new JLabel("Course ID:");
        txtCourseId = new JTextField();
        add(lblCourseId); add(txtCourseId);

        lblCourseName = new JLabel("Course Name:");
        txtCourseName = new JTextField();
        add(lblCourseName); add(txtCourseName);

        lblDuration = new JLabel("Duration (hours):");
        txtDuration = new JTextField();
        add(lblDuration); add(txtDuration);

        lblDeptName = new JLabel("Dept Name:");
        txtDeptName = new JTextField();
        add(lblDeptName); add(txtDeptName);

        lblDeptLocation = new JLabel("Dept Location:");
        txtDeptLocation = new JTextField();
        add(lblDeptLocation); add(txtDeptLocation);

        lblInstructorId = new JLabel("Instructor ID:");
        txtInstructorId = new JTextField();
        add(lblInstructorId); add(txtInstructorId);

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
