package lab2;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    String name;
    List<Student> students;
    List<Subject> taughtSubjects;

    Teacher(String name) {
        this.name = name;
        this.students = new ArrayList<>();
        this.taughtSubjects = new ArrayList<>();
    }

    void addStudent(Student student) {
        students.add(student);
    }

    void teachSubject(Subject subject) {
        taughtSubjects.add(subject);
    }
}
