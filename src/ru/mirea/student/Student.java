package ru.mirea.student;

public class Student  implements Comparable<Student> {
    private int idNumber;
    private String name;
    private int GPA;

    Student(int idNumber, String name, int GPA) {
        this.idNumber= idNumber;
        this.name = name;
        this.GPA = GPA;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public String getName() {
        return name;
    }

    public int getGPA() { return GPA; }

    @Override
    public String toString() {
        return "id: " + idNumber + ", ФИО: " + name + ", Итоговый балл: " + GPA;
    }

    @Override
    public int compareTo(Student o) {
        return this.idNumber - o.idNumber;
    }
}