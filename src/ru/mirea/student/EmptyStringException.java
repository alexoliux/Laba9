package ru.mirea.student;

public class EmptyStringException extends Exception {
    public EmptyStringException(String NameException) {
        super(NameException);
    }
}