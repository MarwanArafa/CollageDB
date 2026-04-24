package model;

public class Department {
    private String Department_Name, Location;

    public Department(String Department_Name, String Location) {
        this.Department_Name = Department_Name;
        this.Location = Location;
    }


    public String getDepartmentName() { return Department_Name; }
    public String getLocation()       { return Location; }

    @Override
    public String toString() {
        return "Department[" + Department_Name + "]: " + Location;
    }
}
