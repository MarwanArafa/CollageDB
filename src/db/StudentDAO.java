package db;

import model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // ── INSERT ───────────────────────────────────────────────────────────────
    public static void insertStudent(int id, String fname, String lname, String phone) {
        String sql = "INSERT INTO Student (Student_ID, Fname, Lname, Phone) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, fname);
            ps.setString(3, lname);
            ps.setString(4, phone);
            ps.executeUpdate();
            System.out.println("Student inserted: " + id);
        } catch (SQLException e) {
            System.err.println("insertStudent failed: " + e.getMessage());
        }
    }

    // ── SELECT ALL ───────────────────────────────────────────────────────────
    public static List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM Student";
        try (Connection con = DBConnection.getConnection();
             Statement st  = con.createStatement();
             ResultSet rs  = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("Student_ID"),
                    rs.getString("Fname"),
                    rs.getString("Lname"),
                    rs.getString("Phone")
                ));
            }
        } catch (SQLException e) {
            System.err.println("getAllStudents failed: " + e.getMessage());
        }
        return list;
    }

    // ── SEARCH BY ID ─────────────────────────────────────────────────────────
    public static Student searchStudent(int id) {
        String sql = "SELECT * FROM Student WHERE Student_ID = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Student(
                    rs.getInt("Student_ID"),
                    rs.getString("Fname"),
                    rs.getString("Lname"),
                    rs.getString("Phone")
                );
            }
        } catch (SQLException e) {
            System.err.println("searchStudent failed: " + e.getMessage());
        }
        return null; // not found
    }

    // ── UPDATE ───────────────────────────────────────────────────────────────
    public static void updateStudent(int id, String fname, String lname, String phone) {
        String sql = "UPDATE Student SET Fname=?, Lname=?, Phone=? WHERE Student_ID=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, phone);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            System.out.println("Updated " + rows + " student(s).");
        } catch (SQLException e) {
            System.err.println("updateStudent failed: " + e.getMessage());
        }
    }

    // ── DELETE ───────────────────────────────────────────────────────────────
    public static void deleteStudent(int id) {
        // Delete enrollments first (FK constraint)
        String deleteEnrollments = "DELETE FROM Enrollment WHERE Student_ID = ?";
        String deleteStudent     = "DELETE FROM Student WHERE Student_ID = ?";
        try (Connection con = DBConnection.getConnection()) {
            // Use transaction — both must succeed or both roll back
            con.setAutoCommit(false);
            try (PreparedStatement ps1 = con.prepareStatement(deleteEnrollments);
                 PreparedStatement ps2 = con.prepareStatement(deleteStudent)) {
                ps1.setInt(1, id);
                ps1.executeUpdate();
                ps2.setInt(1, id);
                ps2.executeUpdate();
                con.commit();
                System.out.println("Student " + id + " deleted.");
            } catch (SQLException e) {
                con.rollback();
                System.err.println("deleteStudent failed, rolled back: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("deleteStudent connection failed: " + e.getMessage());
        }
    }
}
