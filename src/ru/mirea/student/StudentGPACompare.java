package ru.mirea.student;

import java.util.Comparator;

public class StudentGPACompare implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getGPA() - o2.getGPA();
    }
}