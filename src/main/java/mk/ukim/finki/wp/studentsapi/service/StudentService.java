package mk.ukim.finki.wp.studentsapi.service;

import mk.ukim.finki.wp.studentsapi.model.Student;
import mk.ukim.finki.wp.studentsapi.model.StudyProgram;
import mk.ukim.finki.wp.studentsapi.model.exceptions.IndexDigitsException;
import mk.ukim.finki.wp.studentsapi.model.exceptions.ParameterNumberException;
import mk.ukim.finki.wp.studentsapi.model.exceptions.StudentNotFoundException;
import mk.ukim.finki.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentService {
    List<Student> listAllStudents();

    Student getStudentByIndex(int index) throws StudentNotFoundException;

    List<Student> getStudentByProgram(long index);

    Student addStudent(String index, String name, String lastName, String studyProgramName) throws ParameterNumberException, IndexDigitsException, StudyProgramNotFoundException;

    Student updateStudent(int index, String name, String lastName, String studyProgramName) throws StudyProgramNotFoundException,StudentNotFoundException;

    Student deleteStudent(int index) throws StudentNotFoundException;

    List<StudyProgram> listAllStudyProgram();

    StudyProgram addStudyProgram(String studyProgramName);

    StudyProgram deleteStudyProgram(long id) ;
}
