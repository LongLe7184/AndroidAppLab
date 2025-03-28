package com.example.androidlab;

public class Student {
    private String name;
    private int studentID;
    private String classID;
    private String phoneNumer;
    private int seniority;
    private String majority;
    private String plan;


    Student(String Name, int StudentID, String ClassID, String PhoneNumber, int Seniority, String Majority, String Plan){
        this.name = Name;
        this.studentID = StudentID;
        this.classID = ClassID;
        this.phoneNumer = PhoneNumber;
        this.seniority = Seniority;
        this.majority = Majority;
        this.plan = Plan;
    }

    public String display(){
        String str = "";
        str += "THÔNG TIN SINH VIÊN\n\n";

        str += "Họ và tên: " + name + "\t";
        str += "MSSV: " + String.valueOf(studentID) + "\n";

        str += "Lớp: " + classID + "\t";
        str += "SĐT: " + phoneNumer + "\n";

        str += "Sinh viên năm: " + String.valueOf(seniority) + "\t";
        str += "Chuyên ngành: " + majority + "\n";

        str += "Kế hoạch bản thân: " + plan;

        return str;
    }

}
