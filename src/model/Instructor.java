package model;

public class Instructor {
    private int Instructor_ID;
    private String Fname, Lname, Phone;

    public Instructor(int Instructor_ID, String Fname, String Lname, String Phone) {
        this.Instructor_ID = Instructor_ID; this.Fname = Fname;
        this.Lname = Lname; this.Phone = Phone;
    }

    public int getId()       { return Instructor_ID; }
    public String getFname() { return Fname; }
    public String getLname() { return Lname; }
    public String getPhone() { return Phone; }

    @Override
    public String toString() {
        return "Instructor[" + Instructor_ID + "]: " + Fname + " " + Lname + " | " + Phone;
    }
}