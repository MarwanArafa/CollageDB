import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public JTabbedPane tabbedPane;
    public StudentPanel studentPanel;
    public InstructorPanel instructorPanel;
    public CoursePanel coursePanel;
    public DepartmentPanel departmentPanel;

    public MainFrame(){
        setTitle("University Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabbedPane = new JTabbedPane();
        studentPanel = new StudentPanel();
        instructorPanel = new InstructorPanel();
        coursePanel = new CoursePanel();
        departmentPanel = new DepartmentPanel();

        tabbedPane.addTab("Students", studentPanel);
        tabbedPane.addTab("Instructors", instructorPanel);
        tabbedPane.addTab("Courses", coursePanel);
        tabbedPane.addTab("Departments", departmentPanel);

        add(tabbedPane);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }

}


