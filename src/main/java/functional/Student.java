package functional;

import java.util.*;
import java.util.stream.Collectors;

public class Student {

    private static final NavigableMap<Integer, String> gradeLetters = new TreeMap<>();

    static {
        gradeLetters.put(90, "A");
        gradeLetters.put(80, "B");
        gradeLetters.put(70, "C");
        gradeLetters.put(60, "D");
        gradeLetters.put(50, "E");
        gradeLetters.put(0, "F");
    }

    private String name;
    private int score;

    public String getLetterGrade() {
       return gradeLetters.floorEntry(score).getValue();
    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name + ", " + score + "%, grade is " + getLetterGrade();
    }

    public static void main(String[] args) {
        List<Student> school = Arrays.asList(
                new Student("Fred", 71),
                new Student("Jim", 38),
                new Student("Sheila", 99),
                new Student("Katy", 28),
                new Student("Ed", 56),
                new Student("Maggie", 87),
                new Student("Gus", 80),
                new Student("Molly", 21),
                new Student("Ruby", 56),
                new Student("Colby", 63)
        );

        //school.forEach(s -> System.out.println(s));

//        Comparator<Map.Entry<String, List<Student>>> comparator = (e1, e2) -> e2.getKey().compareTo(e1.getKey());
        Comparator<Map.Entry<String, List<Student>>> comparator = Map.Entry.comparingByKey(); // A - E
        comparator = comparator.reversed(); // E - A
        
        Map<String, List<Student>> table = school.stream()
                .collect(Collectors.groupingBy(s -> s.getLetterGrade()));


        table.entrySet().stream()
                .sorted(comparator)
                .forEach(e -> System.out.println("Students " + e.getValue() + " have grade " + e.getKey()));


        System.out.println("----------------------------------------------");

        Map<String, Long> table2 = school.stream()
                .collect(Collectors.groupingBy(s -> s.getLetterGrade(), Collectors.counting()));
        table2.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(e -> System.out.println(e.getValue() + " students got grade " + e.getKey()));
    }
}
