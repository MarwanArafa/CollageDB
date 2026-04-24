package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// No Enrollment model class needed — it's just two foreign keys.
// This DAO handles the M:N relationship between Student and Course.

public class EnrollmentDAO {

    // Enroll a student in a course
    public static void enroll(int studentId, int courseId) {
        String sql = "INSERT INTO Enrollment (Student_ID, Course_ID) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
            System.out.println("Student " + studentId + " enrolled in Course " + courseId);
        } catch (SQLException e) {
            System.err.println("enroll failed: " + e.getMessage());
        }
    }

    // Remove a student from a course
    public static void unenroll(int studentId, int courseId) {
        String sql = "DELETE FROM Enrollment WHERE Student_ID=? AND Course_ID=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("unenroll failed: " + e.getMessage());
        }
    }

    // Get all Course IDs a student is enrolled in
    public static List<Integer> getCoursesForStudent(int studentId) {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT Course_ID FROM Enrollment WHERE Student_ID=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) ids.add(rs.getInt("Course_ID"));
        } catch (SQLException e) {
            System.err.println("getCoursesForStudent failed: " + e.getMessage());
        }
        return ids;
    }

    // Get all Student IDs enrolled in a course
    public static List<Integer> getStudentsForCourse(int courseId) {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT Student_ID FROM Enrollment WHERE Course_ID=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) ids.add(rs.getInt("Student_ID"));
        } catch (SQLException e) {
            System.err.println("getStudentsForCourse failed: " + e.getMessage());
        }
        return ids;
    }
}
