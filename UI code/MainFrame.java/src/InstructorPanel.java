import javax.swing.*;
import java.awt.*;

public class InstructorPanel extends JPanel{

    private JLabel lblInstructorId;
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblPhoneNumber;
    private JLabel lblDeptName;
    private JLabel lblDeptLocation;

    public JTextField txtInstructorId;
    public JTextField txtFirstName;
    public JTextField txtLastName;
    public JTextField txtPhone;
    public JTextField txtDeptName;
    public JTextField txtDeptLocation;

    public JButton btnUpdate;
    public JButton btnSave;
    public JButton btnSearch;
    public JButton btnDelete;

    public JTextArea txtArea;




    public InstructorPanel(){
        setLayout(new GridLayout(9, 2, 5, 5));

        lblInstructorId = new JLabel("Instructor ID:");
        txtInstructorId = new JTextField();
        add(lblInstructorId);
        add(txtInstructorId);

        lblFirstName  = new JLabel("First Name:");
        txtFirstName = new JTextField();
        add(lblFirstName);
        add(txtFirstName);

        lblLastName = new JLabel("Last Name:");
        txtLastName  = new JTextField();
        add(lblLastName);
        add(txtLastName);

        lblPhoneNumber = new JLabel("Phone:");
        txtPhone = new JTextField();
        add(lblPhoneNumber);
        add(txtPhone);

        lblDeptName = new JLabel("Dept Name:");
        txtDeptName = new JTextField();
        add(lblDeptName);
        add(txtDeptName);

        lblDeptLocation = new JLabel("Dept Location:");
        txtDeptLocation = new JTextField();
        add(lblDeptLocation);
        add(txtDeptLocation);

        btnSave = new JButton("Save");
        btnSearch = new JButton("Search");
        btnDelete = new JButton("Delete");
        btnUpdate = new JButton("Update");

        add(btnSave);
        add(btnSearch);
        add(btnDelete);
        add(btnUpdate);

        btnSave.addActionListener(e -> { });
        btnSearch.addActionListener(e -> { });
        btnDelete.addActionListener(e -> { });
        btnUpdate.addActionListener(e -> { });

        add(new JLabel("Results:"));
        txtArea = new JTextArea();
        add(new JScrollPane(txtArea));




}
}
