package ru.mirea.student;
import java.util.Arrays;

public class Sort {

    //быстрая сортировка по итоговому баллу студента
    public static void sortStudentsGPA(Student[] mass, boolean T) {
        sortStudentsGPA(mass, 0, mass.length - 1, T, new StudentGPACompare());
    }

    public static void sortStudentsGPA(Student[] mass, int low, int high, boolean T, StudentGPACompare o) {
        if (mass.length == 0)
            return;//завершить выполнение если длина массива равна 0
        if (low >= high)
            return;//завершить выполнение если уже нечего делить
        // выбрать опорный элемент
        Student prop = mass[low + (high - low) / 2];
        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (o.compare(mass[i], prop) < 0 && T || o.compare(mass[i], prop) > 0 && !T) {
                i++;
            }
            while (o.compare(mass[j], prop) > 0 && T || o.compare(mass[j], prop) < 0 && !T) {
                j--;
            }
            if (i <= j) {//меняем местами
                Student temp = mass[i];
                mass[i] = mass[j];
                mass[j] = temp;
                i++;
                j--;
            }
        }
        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            sortStudentsGPA(mass, low, j, T, o);

        if (i < high)
            sortStudentsGPA(mass, i, high, T, o);
    }

    public static Student[] sortMerge(Student[] mass1, Student[] mass2) {
        Student[] mass = new Student[mass1.length + mass2.length];
        System.arraycopy(mass1, 0, mass, 0, mass1.length);
        System.arraycopy(mass2, 0, mass, mass1.length, mass2.length);
        return sortMerge(mass);
    }

    private static Student[] sortMerge(Student[] mass) {
        int len = mass.length;
        if (len < 2) return mass;
        int middle = len / 2;
        Arrays.copyOfRange(mass, 0, middle);
        return merge(sortMerge(Arrays.copyOfRange(mass, 0, middle)), sortMerge(Arrays.copyOfRange(mass, middle, len)));
    }

    private static Student[] merge(Student[] arr_1, Student[] arr_2) {
        int len_1 = arr_1.length, len_2 = arr_2.length;
        int a = 0, b = 0, len = len_1 + len_2; // a, b - счетчики в массивах
        Student[] result = new Student[len];
        for (int i = 0; i < len; i++) {
            if (b < len_2 && a < len_1) {
                if (arr_1[a].compareTo(arr_2[b]) > 0) result[i] = arr_2[b++];
                else result[i] = arr_1[a++];
            } else if (b < len_2) {
                result[i] = arr_2[b++];
            } else {
                result[i] = arr_1[a++];
            }
        }
        return result;
    }
}