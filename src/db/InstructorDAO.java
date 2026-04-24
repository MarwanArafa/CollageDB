package db;

import model.Instructor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstructorDAO {

    public static void insertInstructor(int id, String fname, String lname,
                                        String phone, String deptName, String deptLocation) {
        String sql = "INSERT INTO Instructor " +
                     "(Instructor_ID, Fname, Lname, Phone, Dept_Name, Dept_Location) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, fname);
            ps.setString(3, lname);
            ps.setString(4, phone);
            ps.setString(5, deptName);
            ps.setString(6, deptLocation);
            ps.executeUpdate();
            System.out.println("Instructor inserted: " + id);
        } catch (SQLException e) {
            System.err.println("insertInstructor failed: " + e.getMessage());
        }
    }

    public static List<Instructor> getAllInstructors() {
        List<Instructor> list = new ArrayList<>();
        String sql = "SELECT * FROM Instructor";
        try (Connection con = DBConnection.getConnection();
             Statement st  = con.createStatement();
             ResultSet rs  = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Instructor(
                    rs.getInt("Instructor_ID"),
                    rs.getString("Fname"),
                    rs.getString("Lname"),
                    rs.getString("Phone")
                ));
            }
        } catch (SQLException e) {
            System.err.println("getAllInstructors failed: " + e.getMessage());
        }
        return list;
    }

    public static Instructor searchInstructor(int id) {
        String sql = "SELECT * FROM Instructor WHERE Instructor_ID = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Instructor(
                    rs.getInt("Instructor_ID"),
                    rs.getString("Fname"),
                    rs.getString("Lname"),
                    rs.getString("Phone")
                );
            }
        } catch (SQLException e) {
            System.err.println("searchInstructor failed: " + e.getMessage());
        }
        return null;
    }

    public static void updateInstructor(int id, String fname, String lname,
                                        String phone, String deptName, String deptLocation) {
        String sql = "UPDATE Instructor SET Fname=?, Lname=?, Phone=?, " +
                     "Dept_Name=?, Dept_Location=? WHERE Instructor_ID=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, phone);
            ps.setString(4, deptName);
            ps.setString(5, deptLocation);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updateInstructor failed: " + e.getMessage());
        }
    }

    public static void deleteInstructor(int id) {
        // Null out Head_ID in Department if this instructor is a head
        String nullHead   = "UPDATE Department SET Head_ID = NULL WHERE Head_ID = ?";
        // Null out courses taught by this instructor
        String nullCourse = "UPDATE Course SET Instructor_ID = NULL WHERE Instructor_ID = ?";
        String deleteInst = "DELETE FROM Instructor WHERE Instructor_ID = ?";
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement p1 = con.prepareStatement(nullHead);
                 PreparedStatement p2 = con.prepareStatement(nullCourse);
                 PreparedStatement p3 = con.prepareStatement(deleteInst)) {
                p1.setInt(1, id); p1.executeUpdate();
                p2.setInt(1, id); p2.executeUpdate();
                p3.setInt(1, id); p3.executeUpdate();
                con.commit();
                System.out.println("Instructor " + id + " deleted.");
            } catch (SQLException e) {
                con.rollback();
                System.err.println("deleteInstructor failed, rolled back: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("deleteInstructor connection failed: " + e.getMessage());
        }
    }
}
