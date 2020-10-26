package ru.mirea.inn;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Ввод ИНН
        boolean T = true;
        while (T) {
            try {
                BigInteger num;
                Scanner sc = new Scanner(System.in);
                String s = sc.nextLine();
                try {
                    num = new BigInteger(s);
                } catch (NumberFormatException err) {
                    throw new BadINNException("ИНН должен содержать только числа.");
                }
                if (s.length() != 10 && s.length() != 12)
                    throw new BadINNException("ИНН может быть только 10-изначным или 12-изначным числом.");
            } catch (BadINNException err) {
                System.out.println(err.getMessage());
                System.out.println("Введите ИНН повторно.");
                continue;
            }
            T = false;
            System.out.println("ИНН действителен");
        }

    }
}