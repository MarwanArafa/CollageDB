package model;

public class Course {
    private int Course_ID, Duration;
    private String Course_Name;
    
    public Course(int Course_ID, String Course_Name, int Duration) {
        this.Course_ID = Course_ID;
        this.Course_Name = Course_Name;
        this.Duration = Duration;
    }


    public int getId()             { return Course_ID; }
    public String getCourseName()  { return Course_Name; }
    public int getDuration()       { return Duration; }


    @Override
    public String toString() {
        return "Course[" + Course_ID + "]: " + Course_Name + " | Time: " + Duration + "h";
    }
}
