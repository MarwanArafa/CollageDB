import javax.swing.*;
import java.awt.*;

public class CoursePanel extends JPanel {
    private JLabel lblcourseID;
    private JLabel lblcourseName;
    private JLabel lblduration;
    private JLabel lbldeptName;
    private JLabel lbldeptLocation;

    public JTextField txtcourseID;
    public JTextField txtcourseName;
    public JTextField txtduration;
    public JTextField txtdeptName;
    public JTextField txtdeptLocation;

    public JButton btnUpdate;
    public JButton btnSave;
    public JButton btnSearch;
    public JButton btnDelete;

    public JTextArea txtArea;

    public CoursePanel() {
        setLayout(new GridLayout(8, 2, 5, 5));

        lblcourseID = new JLabel("Course ID:");
        lblcourseName = new JLabel("Course Name:");
        lblduration = new JLabel("Course Duration:");
        lbldeptName = new JLabel("Dept Name:");
        lbldeptLocation = new JLabel("Dept Location:");

        txtcourseID = new JTextField();
        txtcourseName = new JTextField();
        txtduration = new JTextField();
        txtdeptName = new JTextField();
        txtdeptLocation = new JTextField();

        btnSave = new JButton("Save");
        btnSearch = new JButton("Search");
        btnDelete = new JButton("Delete");
        btnUpdate = new JButton("Update");
        txtArea = new JTextArea();

        add(lblcourseID); add(txtcourseID);
        add(lblcourseName); add(txtcourseName);
        add(lblduration); add(txtduration);
        add(lbldeptName); add(txtdeptName);
        add(lbldeptLocation); add(txtdeptLocation);

        add(btnSave); add(btnSearch);
        add(btnDelete); add(btnUpdate);

        add(new JLabel("Results:"));
        add(new JScrollPane(txtArea));

        btnSave.addActionListener(e -> { });
        btnSearch.addActionListener(e -> { });
        btnDelete.addActionListener(e -> { });
        btnUpdate.addActionListener(e -> { });
    }
}




















/*import javax.swing.*;
import java.awt.*;

public class CoursePanel extends JPanel  {

    private JLabel lblcourseID;
    private JLabel lblcourseName;
    private JLabel lblduration;
    private JLabel lbldeptName;
    private JLabel lbldeptLocation;

    public JTextField txtcourseID;
    public JTextField txtcourseName;
    public JTextField txtduration;
    public JTextField txtdeptName;
    public JTextField txtdeptLocation;

    public JButton btnUpdate;
    public JButton btnSave;
    public JButton btnSearch;
    public JButton btnDelete;

    public JTextArea txtArea;

    public CoursePanel (){
        setLayout(new GridLayout(8, 2, 5, 5));

        lblcourseID = new JLabel("Course ID:");
        lblcourseName = new JLabel("Course name");
        lblduration = new JLabel("Course duration:");
        lbldeptName = new JLabel("Dept name:");
        lbldeptLocation = new JLabel("Dept location:");

        txtcourseID = new JTextField();
        txtcourseName = new JTextField();
        txtduration = new JTextField();
        txtdeptName = new JTextField();
        txtdeptLocation = new JTextField();

        btnSave = new JButton("Save");
        btnSearch = new JButton("Search");
        btnDelete = new JButton("Delete");
        btnUpdate = new JButton("Update");
        txtArea = new JTextArea();

        add(lblcourseID);
        add(lblcourseName);
        add(lblduration);
        add(lbldeptName);
        add(lbldeptLocation);
        add(txtcourseID);
        add(txtcourseName);
        add(txtduration);
        add(txtdeptName);
        add(txtdeptLocation);

        add(btnSave);
        add(btnDelete);
        add(btnSearch);
        add(btnUpdate);

        add(new JLabel("Results:"));
        add(new JScrollPane(txtArea));






    }



} */
