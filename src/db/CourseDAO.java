package db;

import model.Course;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public static void insertCourse(int id, String name, int duration,
                                    String deptName, String deptLocation, int instructorId) {
        String sql = "INSERT INTO Course " +
                     "(Course_ID, Course_Name, Duration, Dept_Name, Dept_Location, Instructor_ID) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, duration);
            ps.setString(4, deptName);
            ps.setString(5, deptLocation);
            ps.setInt(6, instructorId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("insertCourse failed: " + e.getMessage());
        }
    }

    public static List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM Course";
        try (Connection con = DBConnection.getConnection();
             Statement st  = con.createStatement();
             ResultSet rs  = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Course(
                    rs.getInt("Course_ID"),
                    rs.getString("Course_Name"),
                    rs.getInt("Duration")
                ));
            }
        } catch (SQLException e) {
            System.err.println("getAllCourses failed: " + e.getMessage());
        }
        return list;
    }

    public static Course searchCourse(int id) {
        String sql = "SELECT * FROM Course WHERE Course_ID = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Course(
                    rs.getInt("Course_ID"),
                    rs.getString("Course_Name"),
                    rs.getInt("Duration")
                );
            }
        } catch (SQLException e) {
            System.err.println("searchCourse failed: " + e.getMessage());
        }
        return null;
    }

    public static void updateCourse(int id, String name, int duration,
                                    String deptName, String deptLocation, int instructorId) {
        String sql = "UPDATE Course SET Course_Name=?, Duration=?, Dept_Name=?, " +
                     "Dept_Location=?, Instructor_ID=? WHERE Course_ID=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setInt(2, duration);
            ps.setString(3, deptName);
            ps.setString(4, deptLocation);
            ps.setInt(5, instructorId);
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("updateCourse failed: " + e.getMessage());
        }
    }

    public static void deleteCourse(int id) {
        // Delete enrollments first (FK constraint)
        String deleteEnrollments = "DELETE FROM Enrollment WHERE Course_ID = ?";
        String deleteCourse      = "DELETE FROM Course WHERE Course_ID = ?";
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);
            try (PreparedStatement p1 = con.prepareStatement(deleteEnrollments);
                 PreparedStatement p2 = con.prepareStatement(deleteCourse)) {
                p1.setInt(1, id); p1.executeUpdate();
                p2.setInt(1, id); p2.executeUpdate();
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                System.err.println("deleteCourse failed, rolled back: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("deleteCourse connection failed: " + e.getMessage());
        }
    }
}
