package ru.mirea.student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LabClassUI extends JFrame {
    private JTable table;
    private JButton b1, b2, b3, b4;
    private JScrollPane scroll;
    private JComboBox comboBox;
    private JTextField search;

    public LabClassUI(){
        super("Студенты");
        setLayout(null);
        setSize(700, 400);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Student[] students = {new Student(1, "Петрова Л.К.", 70),
                new Student(4, "Сидоров О.О.", 99),
                new Student(12, "Иванов И.И.", 55),
                new Student(505, "Седова В.Р.", 54),
                new Student(7, "Иванов И.И.", 100),
                new Student(155, "Цветков Ф.А.", 84),
                new Student(12, "Иванов И.И.", 55),
                new Student(505, "Седова В.Р.", 54),
                new Student(7, "Иванов И.И.", 100),
                new Student(155, "Цветков Ф.А.", 84),
                new Student(12, "Иванов И.И.", 55),
                new Student(505, "Седова В.Р.", 54),
                new Student(7, "Иванов И.И.", 100),
                new Student(155, "Цветков Ф.А.", 84),
                new Student(87, "Жуков Я.Я.", 67)};
        writeTableStudents();
        String[] comboBoxArray = {"По возрастанию", "По убыванию"};

        comboBox = new JComboBox(comboBoxArray);
        search = new JTextField( 20);
        search.setToolTipText("Введите ФИО студента");
        b1 = new JButton("Вывод всех студентов на экран");
        b2 = new JButton("Сортировать по среднему баллу студента");
        b3 = new JButton("Поиск по ФИО студента \uD83D\uDD0D");
        b4 = new JButton("Очистить");

        b1.setBounds(10, 225,335, 30);
        b2.setBounds(10, b1.getY() + b1.getHeight() + 5,335, 30);
        comboBox.setBounds(b2.getX() + b2.getWidth() + 10, b1.getY() + b1.getHeight() + 5, 335, 30);
        search.setBounds(10, b2.getY() + b2.getHeight() + 5,335, 30);
        b3.setBounds(search.getX() + search.getWidth() + 10, b2.getY() + b2.getHeight() + 5,335, 30);
        b4.setBounds(10, search.getY() + search.getHeight() + 5,335, 30);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeTableStudents(students);
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Student[] arr;
                if (table.getRowCount() == 0)
                    arr = students;
                else arr = readTableStudents();
                if (comboBox.getSelectedIndex() == 0) {
                    Sort.sortStudentsGPA(arr, true);
                }
                if (comboBox.getSelectedIndex() == 1) {
                    Sort.sortStudentsGPA(arr, false);
                }
                writeTableStudents(arr);
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fio = search.getText();
                    if (fio.isEmpty()) throw new EmptyStringException("Поле ввода пустое.");
                    List<Student> arr = new ArrayList<Student>();
                    for(Student s : students) {
                        if (s.getName().equals(fio)) {
                            arr.add(s);
                        }
                    }
                    if (arr.isEmpty()) throw new StudentNotFoundException("Студент с именем " + fio + " не найден.");
                    writeTableStudents(arr.toArray(new Student[0]));
                }
                catch(EmptyStringException err) {
                    JOptionPane.showMessageDialog(null, err.getMessage(), "Некорректный ввод", JOptionPane.ERROR_MESSAGE);}
                catch(StudentNotFoundException err) {
                    JOptionPane.showMessageDialog(null, err.getMessage(), "Некорректный ввод", JOptionPane.ERROR_MESSAGE);}
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                writeTableStudents();
                search.setText("");
            }
        });

        getContentPane().add(search);
        getContentPane().add(comboBox);
        getContentPane().add(b1);
        getContentPane().add(b2);
        getContentPane().add(b3);
        getContentPane().add(b4);

        setVisible(true);
    }

    public Student[] readTableStudents() {
        Student[] arr = new Student[table.getRowCount()];
        for(int i = 0; i < table.getRowCount(); i++) {
            arr[i] = new Student(Integer.parseInt((String)table.getValueAt(i, 0)),
                    (String)table.getValueAt(i, 1),
                    Integer.parseInt((String)table.getValueAt(i, 2)));
        }
        return arr;
    }

    public void writeTableStudents(Student... students) {
        String[][] arr = new String[students.length][];
        for (int i = 0; i < students.length; i++) {
            arr[i] = new String[]{String.valueOf(students[i].getIdNumber()), students[i].getName(), String.valueOf(students[i].getGPA())};
        }
        String[] columnsHeader = new String[] {"ID", "ФИО студента", "Средний балл"};
        table = new JTable(arr, columnsHeader);
        //table.setBounds(10, 5, 680, 200);
        //table.setFillsViewportHeight(true);

        scroll = new JScrollPane();
        scroll.setViewportView(table);
        scroll.setBounds(10, 5, 680, 200);

        getContentPane().add(scroll);
    }

    public static void main(String[] args) {
        new LabClassUI();
    }
}