package ui;

import db.*;
import model.*;
import javax.swing.*;
import java.util.List;

public class MainFrame extends JFrame {

    public JTabbedPane tabbedPane;
    public StudentPanel    studentPanel;
    public InstructorPanel instructorPanel;
    public CoursePanel     coursePanel;
    public DepartmentPanel departmentPanel;

    public MainFrame() {
        setTitle("College Database System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Init DB — creates all SQLite tables on first run
        DBConnection.initializeDatabase();

        tabbedPane      = new JTabbedPane();
        studentPanel    = new StudentPanel();
        instructorPanel = new InstructorPanel();
        coursePanel     = new CoursePanel();
        departmentPanel = new DepartmentPanel();

        tabbedPane.addTab("Students",    studentPanel);
        tabbedPane.addTab("Instructors", instructorPanel);
        tabbedPane.addTab("Courses",     coursePanel);
        tabbedPane.addTab("Departments", departmentPanel);

        add(tabbedPane);

        wireStudents();
        wireInstructors();
        wireCourses();
        wireDepartments();
    }

    // ── STUDENTS ─────────────────────────────────────────────────────────────
    private void wireStudents() {
        StudentPanel p = studentPanel;

        p.btnSave.addActionListener(e -> {
            try {
                int    id    = Integer.parseInt(p.txtStudentId.getText().trim());
                String fname = p.txtFname.getText().trim();
                String lname = p.txtLname.getText().trim();
                String phone = p.txtPhone.getText().trim();

                if (fname.isEmpty() || lname.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "First and Last name are required.");
                    return;
                }
                StudentDAO.insertStudent(id, fname, lname, phone);
                p.txtArea.setText("Student saved: " + fname + " " + lname);
                clearFields(p.txtStudentId, p.txtFname, p.txtLname, p.txtPhone);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Student ID must be a number.");
            }
        });

        p.btnSearch.addActionListener(e -> {
            try {
                int id = Integer.parseInt(p.txtStudentId.getText().trim());
                Student s = StudentDAO.searchStudent(id);
                if (s != null) {
                    p.txtArea.setText(s.toString());
                    // Auto-fill fields for easy update
                    p.txtFname.setText(s.getFname());
                    p.txtLname.setText(s.getLname());
                    p.txtPhone.setText(s.getPhone());
                } else {
                    p.txtArea.setText("No student found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid Student ID to search.");
            }
        });

        p.btnUpdate.addActionListener(e -> {
            try {
                int    id    = Integer.parseInt(p.txtStudentId.getText().trim());
                String fname = p.txtFname.getText().trim();
                String lname = p.txtLname.getText().trim();
                String phone = p.txtPhone.getText().trim();

                StudentDAO.updateStudent(id, fname, lname, phone);
                p.txtArea.setText("Student " + id + " updated.");
                clearFields(p.txtStudentId, p.txtFname, p.txtLname, p.txtPhone);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Student ID must be a number.");
            }
        });

        p.btnDelete.addActionListener(e -> {
            try {
                int id = Integer.parseInt(p.txtStudentId.getText().trim());
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete student " + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    StudentDAO.deleteStudent(id);
                    p.txtArea.setText("Student " + id + " deleted.");
                    clearFields(p.txtStudentId, p.txtFname, p.txtLname, p.txtPhone);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid Student ID to delete.");
            }
        });
    }

    // ── INSTRUCTORS ───────────────────────────────────────────────────────────
    private void wireInstructors() {
        InstructorPanel p = instructorPanel;

        p.btnSave.addActionListener(e -> {
            try {
                int    id       = Integer.parseInt(p.txtInstructorId.getText().trim());
                String fname    = p.txtFname.getText().trim();
                String lname    = p.txtLname.getText().trim();
                String phone    = p.txtPhone.getText().trim();
                String deptName = p.txtDeptName.getText().trim();
                String deptLoc  = p.txtDeptLocation.getText().trim();

                if (fname.isEmpty() || lname.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "First and Last name are required.");
                    return;
                }
                InstructorDAO.insertInstructor(id, fname, lname, phone, deptName, deptLoc);
                p.txtArea.setText("Instructor saved: " + fname + " " + lname);
                clearFields(p.txtInstructorId, p.txtFname, p.txtLname,
                            p.txtPhone, p.txtDeptName, p.txtDeptLocation);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Instructor ID must be a number.");
            }
        });

        p.btnSearch.addActionListener(e -> {
            try {
                int id = Integer.parseInt(p.txtInstructorId.getText().trim());
                Instructor inst = InstructorDAO.searchInstructor(id);
                if (inst != null) {
                    p.txtArea.setText(inst.toString());
                    p.txtFname.setText(inst.getFname());
                    p.txtLname.setText(inst.getLname());
                    p.txtPhone.setText(inst.getPhone());
                } else {
                    p.txtArea.setText("No instructor found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid Instructor ID.");
            }
        });

        p.btnUpdate.addActionListener(e -> {
            try {
                int    id      = Integer.parseInt(p.txtInstructorId.getText().trim());
                String fname   = p.txtFname.getText().trim();
                String lname   = p.txtLname.getText().trim();
                String phone   = p.txtPhone.getText().trim();
                String deptName= p.txtDeptName.getText().trim();
                String deptLoc = p.txtDeptLocation.getText().trim();

                InstructorDAO.updateInstructor(id, fname, lname, phone, deptName, deptLoc);
                p.txtArea.setText("Instructor " + id + " updated.");
                clearFields(p.txtInstructorId, p.txtFname, p.txtLname,
                            p.txtPhone, p.txtDeptName, p.txtDeptLocation);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Instructor ID must be a number.");
            }
        });

        p.btnDelete.addActionListener(e -> {
            try {
                int id = Integer.parseInt(p.txtInstructorId.getText().trim());
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete instructor " + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    InstructorDAO.deleteInstructor(id);
                    p.txtArea.setText("Instructor " + id + " deleted.");
                    clearFields(p.txtInstructorId, p.txtFname, p.txtLname,
                                p.txtPhone, p.txtDeptName, p.txtDeptLocation);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid Instructor ID.");
            }
        });
    }

    // ── COURSES ───────────────────────────────────────────────────────────────
    private void wireCourses() {
        CoursePanel p = coursePanel;

        p.btnSave.addActionListener(e -> {
            try {
                int    id       = Integer.parseInt(p.txtCourseId.getText().trim());
                String name     = p.txtCourseName.getText().trim();
                int    duration = Integer.parseInt(p.txtDuration.getText().trim());
                String deptName = p.txtDeptName.getText().trim();
                String deptLoc  = p.txtDeptLocation.getText().trim();
                int    instId   = Integer.parseInt(p.txtInstructorId.getText().trim());

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Course Name is required.");
                    return;
                }
                CourseDAO.insertCourse(id, name, duration, deptName, deptLoc, instId);
                p.txtArea.setText("Course saved: " + name);
                clearFields(p.txtCourseId, p.txtCourseName, p.txtDuration,
                            p.txtDeptName, p.txtDeptLocation, p.txtInstructorId);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID and Duration must be numbers.");
            }
        });

        p.btnSearch.addActionListener(e -> {
            try {
                int id = Integer.parseInt(p.txtCourseId.getText().trim());
                Course c = CourseDAO.searchCourse(id);
                if (c != null) {
                    p.txtArea.setText(c.toString());
                    p.txtCourseName.setText(c.getCourseName());
                    p.txtDuration.setText(String.valueOf(c.getDuration()));
                } else {
                    p.txtArea.setText("No course found with ID: " + id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid Course ID.");
            }
        });

        p.btnUpdate.addActionListener(e -> {
            try {
                int    id       = Integer.parseInt(p.txtCourseId.getText().trim());
                String name     = p.txtCourseName.getText().trim();
                int    duration = Integer.parseInt(p.txtDuration.getText().trim());
                String deptName = p.txtDeptName.getText().trim();
                String deptLoc  = p.txtDeptLocation.getText().trim();
                int    instId   = Integer.parseInt(p.txtInstructorId.getText().trim());

                CourseDAO.updateCourse(id, name, duration, deptName, deptLoc, instId);
                p.txtArea.setText("Course " + id + " updated.");
                clearFields(p.txtCourseId, p.txtCourseName, p.txtDuration,
                            p.txtDeptName, p.txtDeptLocation, p.txtInstructorId);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID and Duration must be numbers.");
            }
        });

        p.btnDelete.addActionListener(e -> {
            try {
                int id = Integer.parseInt(p.txtCourseId.getText().trim());
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete course " + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    CourseDAO.deleteCourse(id);
                    p.txtArea.setText("Course " + id + " deleted.");
                    clearFields(p.txtCourseId, p.txtCourseName, p.txtDuration,
                                p.txtDeptName, p.txtDeptLocation, p.txtInstructorId);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid Course ID.");
            }
        });
    }

    // ── DEPARTMENTS ───────────────────────────────────────────────────────────
    private void wireDepartments() {
        DepartmentPanel p = departmentPanel;

        p.btnSave.addActionListener(e -> {
            String name = p.txtDeptName.getText().trim();
            String loc  = p.txtLocation.getText().trim();
            if (name.isEmpty() || loc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and Location are required.");
                return;
            }
            DepartmentDAO.insertDepartment(name, loc);
            p.txtArea.setText("Department saved: " + name);
            clearFields(p.txtDeptName, p.txtLocation);
        });

        p.btnSearch.addActionListener(e -> {
            String name = p.txtDeptName.getText().trim();
            Department d = DepartmentDAO.searchDepartment(name);
            if (d != null) {
                p.txtArea.setText(d.toString());
                p.txtLocation.setText(d.getLocation());
            } else {
                p.txtArea.setText("No department found: " + name);
            }
        });

        p.btnUpdate.addActionListener(e -> {
            String name = p.txtDeptName.getText().trim();
            String loc  = p.txtLocation.getText().trim();
            DepartmentDAO.updateDepartment(name, loc, null);
            p.txtArea.setText("Department updated: " + name);
            clearFields(p.txtDeptName, p.txtLocation);
        });

        p.btnDelete.addActionListener(e -> {
            String name = p.txtDeptName.getText().trim();
            int confirm = JOptionPane.showConfirmDialog(this,
                "Delete department '" + name + "'?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                DepartmentDAO.deleteDepartment(name);
                p.txtArea.setText("Department deleted: " + name);
                clearFields(p.txtDeptName, p.txtLocation);
            }
        });
    }

    // ── UTILITY ───────────────────────────────────────────────────────────────
    private void clearFields(JTextField... fields) {
        for (JTextField f : fields) f.setText("");
    }

    // ── ENTRY POINT ───────────────────────────────────────────────────────────
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
