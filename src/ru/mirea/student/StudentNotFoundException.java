package ru.mirea.student;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String NameException) {
        super(NameException);
    }
}