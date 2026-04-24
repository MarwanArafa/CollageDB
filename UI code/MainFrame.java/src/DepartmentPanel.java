import javax.swing.*;
import java.awt.*;

public class DepartmentPanel extends JPanel {
    // Labels
    private JLabel lblDeptName, lblLocation;

    // Public components for future database logic
    public JTextField txtDeptName, txtLocation;
    public JButton btnSave, btnSearch, btnDelete, btnUpdate;
    public JTextArea txtArea;

    public DepartmentPanel() {
        // 5 Rows (2 Input + 2 Button + 1 Result), 2 Columns
        setLayout(new GridLayout(5, 2, 5, 5));

        // Row 1: Name
        lblDeptName = new JLabel("Department Name:");
        txtDeptName = new JTextField();
        add(lblDeptName);
        add(txtDeptName);

        // Row 2: Location
        lblLocation = new JLabel("Department Location:");
        txtLocation = new JTextField();
        add(lblLocation);
        add(txtLocation);

        // Rows 3 & 4: Buttons
        btnSave = new JButton("Save");
        btnSearch = new JButton("Search");
        btnDelete = new JButton("Delete");
        btnUpdate = new JButton("Update");

        add(btnSave);   add(btnSearch);
        add(btnDelete); add(btnUpdate);

        // Listeners (Empty for now)
        btnSave.addActionListener(e -> { });
        btnSearch.addActionListener(e -> { });
        btnDelete.addActionListener(e -> { });
        btnUpdate.addActionListener(e -> { });

        // Row 5: Results
        add(new JLabel("Results:"));
        txtArea = new JTextArea();
        add(new JScrollPane(txtArea));
    }
}













/*import javax.swing.*;
import java.awt.*;

public class DepartmentPanel extends JPanel {

    private JLabel lblLocation;
    private JLabel lblDeptName;

    public JTextField txtDeptName;
    public JTextField txtLocation;

    public JButton btnDeptName;
    public JButton btnLocation;

    public JTextArea txtArea;

    DepartmentPanel(){
        setLayout(new GridLayout(5, 2, 5, 5));


    }

}*/
