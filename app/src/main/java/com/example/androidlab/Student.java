package com.example.androidlab;

public class Student {
    private int studentID;
    private String name;
    private byte[] image; // Store image as byte array

    public Student(int studentID, String name, byte[] image) {
        this.studentID = studentID;
        this.name = name;
        this.image = image;
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
}