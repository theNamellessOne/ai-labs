package lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class University {
    List<Student> students;
    List<Teacher> teachers;
    List<Subject> subjects;

    University() {
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
        this.subjects = new ArrayList<>();
    }

    void enrollStudent(String name, String group) {
        students.add(new Student(name, group));

        System.out.println(name + " enrolled!");
    }

    void hireTeacher(String name) {
        teachers.add(new Teacher(name));

        System.out.println(name + " hired!");
    }

    void addSubject(String name) {
        subjects.add(new Subject(name));

        System.out.println(name + " added!");
    }

    void assignStudentToTeacher(String studentName, String teacherName) {
        Student student = findStudentByName(studentName);
        Teacher teacher = findTeacherByName(teacherName);

        if (student != null && teacher != null) {
            teacher.addStudent(student);
        }

        System.out.println(teacherName + " is now teaching " + studentName);
    }

    void assignSubjectToStudent(String studentName, String subjectName) {
        Student student = findStudentByName(studentName);
        Subject subject = findSubjectByName(subjectName);

        if (student != null && subject != null) {
            student.enrollSubject(subject);

            System.out.println(subjectName + " assigned to " + studentName);
        }
    }

    void assignSubjectToTeacher(String teacherName, String subjectName) {
        Teacher teacher = findTeacherByName(teacherName);
        Subject subject = findSubjectByName(subjectName);

        if (teacher != null && subject != null) {
            teacher.teachSubject(subject);

            System.out.println(subjectName + " assigned to " + teacherName);
        }
    }

    boolean isStudentOfTeacher(String studentName, String teacherName) {
        Predicate<Student> studentPredicate = student -> student.name.equals(studentName);
        Predicate<Teacher> teacherPredicate = teacher -> teacher.name.equals(teacherName);

        boolean result = students.stream().anyMatch(studentPredicate) &&
                teachers.stream().anyMatch(teacherPredicate) &&
                teachers.stream().filter(teacherPredicate).findFirst()
                        .get().students.stream().anyMatch(studentPredicate);

        System.out.println(studentName + (result ? " is" : " is not") + " student of " + teacherName);

        return result;
    }

    boolean isTeachingSubject(String teacherName, String subjectName) {
        Predicate<Teacher> teacherPredicate = teacher -> teacher.name.equals(teacherName);
        Predicate<Subject> subjectPredicate = subject -> subject.name.equals(subjectName);

        boolean result = teachers.stream().anyMatch(teacherPredicate) &&
                teachers.stream().filter(teacherPredicate).findFirst()
                        .get().taughtSubjects.stream().anyMatch(subjectPredicate);

        System.out.println(teacherName + (result ? " is" : " is not") + " teaching " + subjectName);

        return result;
    }

    boolean isEnrolledInSubject(String studentName, String subjectName) {
        Predicate<Student> studentPredicate = student -> student.name.equals(studentName);
        Predicate<Subject> subjectPredicate = subject -> subject.name.equals(subjectName);

        boolean result =  students.stream().anyMatch(studentPredicate) &&
                students.stream().filter(studentPredicate).findFirst()
                        .get().enrolledSubjects.stream().anyMatch(subjectPredicate);

        System.out.println(studentName + (result ? " is" : " is not") + " enrolled in " + subjectName);

        return result;
    }

    private Student findStudentByName(String studentName) {
        return students.stream().filter(student -> student.name.equals(studentName)).findFirst().orElse(null);
    }

    private Teacher findTeacherByName(String teacherName) {
        return teachers.stream().filter(teacher -> teacher.name.equals(teacherName)).findFirst().orElse(null);
    }

    private Subject findSubjectByName(String subjectName) {
        return subjects.stream().filter(subject -> subject.name.equals(subjectName)).findFirst().orElse(null);
    }
}
