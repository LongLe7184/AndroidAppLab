package com.example.androidlab;

public class Student {
    private int studentID;
    private String name;
    private byte[] image; // Store image as byte array
    private String classID;
    private String phoneNumber;
    private int seniority;
    private String majority;

    public Student(int studentID, String name, byte[] image, String classID, String phoneNumber, int seniority, String majority) {
        this.studentID = studentID;
        this.name = name;
        this.image = image;
        this.classID = classID;
        this.phoneNumber = phoneNumber;
        this.seniority = seniority;
        this.majority = majority;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }

    public String getClassID() {
        return classID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getSeniority() {
        return seniority;
    }
    public String getMajority() {
        return majority;
    }

}