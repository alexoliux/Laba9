package ru.mirea.inn;

public class BadINNException extends Exception {
    public BadINNException(String NameException) {
        super(NameException + " ИНН недействителен.");
    }
}