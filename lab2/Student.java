package lab2;

import java.util.ArrayList;
import java.util.List;

public class Student {
    String name;
    String group;
    List<Subject> enrolledSubjects;

    Student(String name, String group) {
        this.name = name;
        this.group = group;
        this.enrolledSubjects = new ArrayList<>();
    }

    void enrollSubject(Subject subject) {
        enrolledSubjects.add(subject);
    }
}
