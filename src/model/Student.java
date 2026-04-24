package model;

public class Student {
    private int Student_ID;
    private String Fname, Lname, Phone;

    public Student(int Student_ID, String Fname, String Lname, String Phone) {
        this.Student_ID = Student_ID; this.Fname = Fname;
        this.Lname = Lname; this.Phone = Phone;
    }

    public int getId()       { return Student_ID; }
    public String getFname() { return Fname; }
    public String getLname() { return Lname; }
    public String getPhone() { return Phone; }

    @Override
    public String toString() {
        return "Student[" + Student_ID + "]: " + Fname + " " + Lname + " | " + Phone;
    }
}