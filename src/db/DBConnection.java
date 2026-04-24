package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    // ── SWAP THIS ONE LINE ON SATURDAY ──────────────────────────────────────
    // SQLite:     "jdbc:sqlite:college.db"
    /*
        Go to: https://learn.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server
        Download the .jar file — it's called something like mssql-jdbc-12.x.x.jre11.jar. Add it to the NetBeans project the same way you added the SQLite jar.
        Then:
            private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=CollegeDB;encrypt=false";
        Also add:
            private static final String USER = "Metro Al Maadi";      // SQL Server username
            private static final String PASS = "yourpassword"; // whatever they set
    
    */
    // SQL Server: "jdbc:sqlserver://localhost:1433;databaseName=CollegeDB;user=sa;password=YOUR_PASS"
    private static final String URL = "jdbc:sqlite:college.db";

    
    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(URL); // IN ----SQL Server---- getConnection(URL, USER, PASS)....
        // Enable foreign key enforcement in SQLite (disabled by default)
        try (Statement st = con.createStatement()) {
            st.execute("PRAGMA foreign_keys = ON"); // DELETE this three lines — it's SQLite only:
        }
        return con;
    }

    // Call this once at app startup to build all tables if they don't exist
    public static void initializeDatabase() {
        String createDepartment =
            "CREATE TABLE IF NOT EXISTS Department (" +
            "    Department_Name VARCHAR(100) NOT NULL," +
            "    Location        VARCHAR(100) NOT NULL," +
            "    Head_ID         INT," +
            "    PRIMARY KEY (Department_Name, Location)," +
            "    FOREIGN KEY (Head_ID) REFERENCES Instructor(Instructor_ID)" +
            ")";

        String createInstructor =
            "CREATE TABLE IF NOT EXISTS Instructor (" +
            "    Instructor_ID INT         PRIMARY KEY," +
            "    Fname         VARCHAR(50) NOT NULL," +
            "    Lname         VARCHAR(50) NOT NULL," +
            "    Phone         VARCHAR(20)," +
            "    Dept_Name     VARCHAR(100)," +
            "    Dept_Location VARCHAR(100)," +
            "    FOREIGN KEY (Dept_Name, Dept_Location)" +
            "        REFERENCES Department(Department_Name, Location)" +
            ")";

        String createCourse =
            "CREATE TABLE IF NOT EXISTS Course (" +
            "    Course_ID     INT          PRIMARY KEY," +
            "    Course_Name   VARCHAR(100) NOT NULL," +
            "    Duration      INT," +
            "    Dept_Name     VARCHAR(100)," +
            "    Dept_Location VARCHAR(100)," +
            "    Instructor_ID INT," +
            "    FOREIGN KEY (Dept_Name, Dept_Location)" +
            "        REFERENCES Department(Department_Name, Location)," +
            "    FOREIGN KEY (Instructor_ID) REFERENCES Instructor(Instructor_ID)" +
            ")";

        String createStudent =
            "CREATE TABLE IF NOT EXISTS Student (" +
            "    Student_ID INT         PRIMARY KEY," +
            "    Fname      VARCHAR(50) NOT NULL," +
            "    Lname      VARCHAR(50) NOT NULL," +
            "    Phone      VARCHAR(20)" +
            ")";

        String createEnrollment =
            "CREATE TABLE IF NOT EXISTS Enrollment (" +
            "    Student_ID INT NOT NULL," +
            "    Course_ID  INT NOT NULL," +
            "    PRIMARY KEY (Student_ID, Course_ID)," +
            "    FOREIGN KEY (Student_ID) REFERENCES Student(Student_ID)," +
            "    FOREIGN KEY (Course_ID)  REFERENCES Course(Course_ID)" +
            ")";

        // Order matters — parent tables first, then child tables
        String[] tables = {
            createDepartment,
            createInstructor,
            createCourse,
            createStudent,
            createEnrollment
        };

        try (Connection con = getConnection();
             Statement st  = con.createStatement()) {
            for (String sql : tables) {
                st.execute(sql);
            }
            System.out.println("Database initialized successfully.");
        } catch (SQLException e) {
            System.err.println("DB init failed: " + e.getMessage());
        }
    }
}
