import db.DBConnection;
import db.StudentDAO;
import model.Student;
import java.util.List;

public class TestDB {
    public static void main(String[] args) {
        // Step 1: create all tables
        DBConnection.initializeDatabase();

        // Step 2: insert a student
        StudentDAO.insertStudent(1, "Marwan", "Arafa", "01012345678");

        // Step 3: get all students and print them
        List<Student> students = StudentDAO.getAllStudents();
        for (Student s : students) {
            System.out.println(s); // calls your @Override toString()
        }

        // Step 4: search
        Student found = StudentDAO.searchStudent(1);
        System.out.println("Found: " + found);

        // Step 5: update
        StudentDAO.updateStudent(1, "Marwan", "Arafa", "01099999999");

        // Step 6: delete
        StudentDAO.deleteStudent(1);

        System.out.println("All tests passed.");
    }
}