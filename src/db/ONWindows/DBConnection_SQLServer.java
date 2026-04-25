package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// ══════════════════════════════════════════════════════════════
// SATURDAY VERSION — Replace DBConnection.java with this file
// Steps:
// 1. Add mssql-jdbc-12.x.x.jre11.jar to Libraries in NetBeans
// 2. Replace DBConnection.java content with this content
// 3. Set USER and PASS to match Mohamed's SQL Server login
// 4. Mohamed runs college_db_sqlserver.sql in SSMS first
// 5. Run the app — no other file changes needed
// ══════════════════════════════════════════════════════════════

public class DBConnection {

    private static final String URL  = "jdbc:sqlserver://localhost:1433;databaseName=CollegeDB;encrypt=false";
    private static final String USER = "sa";
    private static final String PASS = "YourPasswordHere"; // ← change this

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
        // No PRAGMA needed — SQL Server enforces FKs by default
    }

    public static void initializeDatabase() {
        // Do NOT call this on Saturday
        // Tables are already created by running college_db_sqlserver.sql in SSMS
        System.out.println("SQL Server mode — skipping table creation.");
    }
}
