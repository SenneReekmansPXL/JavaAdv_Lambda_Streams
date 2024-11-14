package be.pxl.ja.exercise1;

import java.io.Console;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {
    public static void main(String[] args) {
        getBirthdayStudents();

        System.out.println("_______________________");
        System.out.println("\n");
        getCarol();

        System.out.println("_______________________");
        System.out.println("\n");
        getGraduatesOf2017();

        System.out.println("_______________________");
        System.out.println("\n");
        getPersonWithHighestScore();

        System.out.println("_______________________");
        System.out.println("\n");
        getPersonWithHighestAge();

        System.out.println("_______________________");
        System.out.println("\n");
        getAllNames();

        System.out.println("_______________________");
        System.out.println("\n");
        getYounghestGraduateOf2018();

        System.out.println("_______________________");
        System.out.println("\n");
        getAverageScorePerGraduateYear();

        System.out.println("_______________________");
        System.out.println("\n");
        sortAndDisplayStudents();
    }

    public static void getBirthdayStudents() {
        StudentList.createStudents().stream()
                .filter(student -> student.getDateOfBirth().getMonth() == LocalDate.now().getMonth() &&
                        student.getDateOfBirth().getDayOfMonth() == LocalDate.now().getDayOfMonth())
                .forEach(System.out::println);
    }

    public static void getCarol() {
        StudentList.createStudents().stream()
                .filter(student -> student.getName().equals("Carol"))
                .forEach(System.out::println);
    }

    public static void getGraduatesOf2017() {
        long aantal = StudentList.createStudents().stream()
                .filter(student -> student.getGraduationYear() == 2017)
                .count();
        System.out.println(aantal);
    }

    public static void getPersonWithHighestScore() {
        Optional<Student> student = StudentList.createStudents().stream()
                .max(Comparator.comparingDouble(Student::getScore));
        System.out.println(student.get().getName() + " " + student.get().getScore());

    }

    public static void getPersonWithHighestAge() {
        Optional<Student> student = StudentList.createStudents().stream()
                .min(Comparator.comparing(Student::getDateOfBirth));
        System.out.println(student.get().getName() + " " + student.get().getDateOfBirth());

    }

    public static void getAllNames() {
        StringBuilder builder = new StringBuilder();
        StudentList.createStudents()
                        .forEach(student1 -> builder.append(student1.getName()).append(", "));
        System.out.println(builder.toString());
    }

    public static void getYounghestGraduateOf2018() {
        Optional<Student> student = StudentList.createStudents().stream()
                .filter(student1 -> student1.getGraduationYear() == 2018)
                .max(Comparator.comparing(Student::getDateOfBirth));

        System.out.println(student.get().getName());
    }

    public static void getAverageScorePerGraduateYear() {
        Map<Integer, Double> averageScorePerYear = StudentList.createStudents().stream()
                .collect(Collectors.groupingBy(
                        Student::getGraduationYear,
                        Collectors.averagingDouble(Student::getScore)
                ));
        averageScorePerYear.forEach((year, avgScore) ->
                System.out.println("Graduation Year: " + year + ", Average Score: " + avgScore));
    }

    public static void sortAndDisplayStudents() {
        StudentList.createStudents().stream()
                .sorted(Comparator.comparing(Student::getGraduationYear).reversed() // Sort by graduation year (recent first)
                        .thenComparing(Comparator.comparing(Student::getScore).reversed())) // Then by score (highest first)
                .forEach(student -> System.out.println(student));
    }


}
