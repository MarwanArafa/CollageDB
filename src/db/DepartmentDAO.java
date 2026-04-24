package db;

import model.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

    public static void insertDepartment(String name, String location) {
        String sql = "INSERT INTO Department (Department_Name, Location) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, location);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("insertDepartment failed: " + e.getMessage());
        }
    }

    public static List<Department> getAllDepartments() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM Department";
        try (Connection con = DBConnection.getConnection();
             Statement st  = con.createStatement();
             ResultSet rs  = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Department(
                    rs.getString("Department_Name"),
                    rs.getString("Location")
                ));
            }
        } catch (SQLException e) {
            System.err.println("getAllDepartments failed: " + e.getMessage());
        }
        return list;
    }

    public static Department searchDepartment(String name) {
        String sql = "SELECT * FROM Department WHERE Department_Name = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Department(
                    rs.getString("Department_Name"),
                    rs.getString("Location")
                );
            }
        } catch (SQLException e) {
            System.err.println("searchDepartment failed: " + e.getMessage());
        }
        return null;
    }

    public static void updateDepartment(String name, String newLocation, Integer headId) {
        String sql = "UPDATE Department SET Location=?, Head_ID=? WHERE Department_Name=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, newLocation);
            if (headId != null) ps.setInt(2, headId);
            else                ps.setNull(2, Types.INTEGER);
            ps.setString(3, name);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updateDepartment failed: " + e.getMessage());
        }
    }

    public static void deleteDepartment(String name) {
        // Null out FK references in Instructor and Course first
        String nullInstructor = "UPDATE Instructor SET Dept_Name=NULL, Dept_Location=NULL WHERE Dept_Name=?";
        String nullCourse     = "UPDATE Course SET Dept_Name=NULL, Dept_Location=NULL WHERE Dept_Name=?";
        String deleteDept     = "DELETE FROM Department WHERE Department_Name=?";
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement p1 = con.prepareStatement(nullInstructor);
                 PreparedStatement p2 = con.prepareStatement(nullCourse);
                 PreparedStatement p3 = con.prepareStatement(deleteDept)) {
                p1.setString(1, name); p1.executeUpdate();
                p2.setString(1, name); p2.executeUpdate();
                p3.setString(1, name); p3.executeUpdate();
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                System.err.println("deleteDepartment failed, rolled back: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("deleteDepartment connection failed: " + e.getMessage());
        }
    }

    // Set or update the head of a department
    public static void setDepartmentHead(String deptName, int instructorId) {
        String sql = "UPDATE Department SET Head_ID=? WHERE Department_Name=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, instructorId);
            ps.setString(2, deptName);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("setDepartmentHead failed: " + e.getMessage());
        }
    }
}
