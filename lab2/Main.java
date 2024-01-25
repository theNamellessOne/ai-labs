package lab2;

public class Main {
    public static void main(String[] args) {
        University uni = new University();

        uni.enrollStudent("John Doe", "Group A");
        uni.enrollStudent("Jane Smith", "Group B");

        uni.hireTeacher("Prof. Johnson");
        uni.hireTeacher("Dr. Williams");

        uni.addSubject("Mathematics");

        uni.assignStudentToTeacher("John Doe", "Prof. Johnson");
        uni.assignSubjectToStudent("John Doe", "Mathematics");
        uni.assignSubjectToTeacher("Prof. Johnson", "Mathematics");

        String studentName = "John Doe";
        String teacherName = "Prof. Johnson";
        String subjectName = "Mathematics";

        boolean isStudentOfTeacher = uni.isStudentOfTeacher(studentName, teacherName);
        boolean isTeachingSubject = uni.isTeachingSubject(teacherName, subjectName);
        boolean isEnrolledInSubject = uni.isEnrolledInSubject(studentName, subjectName);
    }
}
