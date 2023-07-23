package edu.lysak.comparing;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student(1, "Yuliia", 28);
        Student student2 = new Student(1, "Yuliia", 28);
        SchoolBoy schoolBoy = new SchoolBoy(1, "Yuliia", 28);
        System.out.println(student1.equals(student2));
        System.out.println(student1.equals(schoolBoy));
    }
}
