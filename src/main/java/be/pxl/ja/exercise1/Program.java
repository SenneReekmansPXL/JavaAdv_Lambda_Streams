package be.pxl.ja.exercise1;

import java.io.Console;
import java.time.LocalDate;
import java.util.stream.Stream;

public class Program {
    public static void main(String[] args) {

        getBirthdayStudents();
    }

    public static void getBirthdayStudents() {
        Stream<Student> studentStream = StudentList.createStudents().stream().filter(student ->
                student.getDateOfBirth().getMonth() == LocalDate.now().getMonth() &&
                        student.getDateOfBirth().getDayOfMonth() == LocalDate.now().getDayOfMonth());
        System.out.println(studentStream);


    }





}
