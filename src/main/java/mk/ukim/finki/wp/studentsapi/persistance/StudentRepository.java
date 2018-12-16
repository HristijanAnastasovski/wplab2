package mk.ukim.finki.wp.studentsapi.persistance;

import mk.ukim.finki.wp.studentsapi.model.Student;

import java.util.List;



public interface StudentRepository {
    List<Student> listAllStudents();

    Student getStudentByIndex(int index);

    List<Student> getStudentByProgram(long index);

    Student addStudent(String index, String name, String lastName, String studyProgramName);

    Student updateStudent(int index, String name, String lastName, String studyProgramName);

    Student deleteStudent(int index);

}
