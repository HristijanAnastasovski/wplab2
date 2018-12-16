package mk.ukim.finki.wp.studentsapi.service.impl;

import mk.ukim.finki.wp.studentsapi.model.Student;
import mk.ukim.finki.wp.studentsapi.model.StudyProgram;
import mk.ukim.finki.wp.studentsapi.model.exceptions.IndexDigitsException;
import mk.ukim.finki.wp.studentsapi.model.exceptions.ParameterNumberException;
import mk.ukim.finki.wp.studentsapi.model.exceptions.StudentNotFoundException;
import mk.ukim.finki.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;
import mk.ukim.finki.wp.studentsapi.persistance.StudentRepository;
import mk.ukim.finki.wp.studentsapi.persistance.StudyProgramRepository;
import mk.ukim.finki.wp.studentsapi.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudyProgramRepository studyProgramRepository;

    public StudentServiceImpl(StudentRepository studentRepository, StudyProgramRepository studyProgramRepository) {
        this.studentRepository = studentRepository;
        this.studyProgramRepository = studyProgramRepository;
    }

    @Override
    public List<Student> listAllStudents() {
        return studentRepository.listAllStudents();
    }

    @Override
    public Student getStudentByIndex(int index) throws StudentNotFoundException {
        Student s=studentRepository.getStudentByIndex(index);
        if(s==null)
            throw new StudentNotFoundException();
        return s;
    }

    @Override
    public List<Student> getStudentByProgram(long index) {
        return studentRepository.getStudentByProgram(index);
    }

    @Override
    public Student addStudent(String index, String name, String lastName, String studyProgramName) throws ParameterNumberException, IndexDigitsException, StudyProgramNotFoundException {

        if(index==null || studyProgramName==null || lastName==null || name==null)
            throw new ParameterNumberException();
        if(index.length()!=6)
            throw new IndexDigitsException();
        boolean containsProgram = studyProgramRepository.listAllStudyProgram().stream().anyMatch(studyProgram -> studyProgram.name.equals(studyProgramName));
        if(!containsProgram)
            throw new StudyProgramNotFoundException();

        return studentRepository.addStudent(index,name,lastName,studyProgramName);
    }

    @Override
    public Student updateStudent(int index, String name, String lastName, String studyProgramName) throws StudyProgramNotFoundException, StudentNotFoundException {
        boolean containsProgram = studyProgramRepository.listAllStudyProgram().stream().anyMatch(studyProgram -> studyProgram.name.equals(studyProgramName));
        if(!containsProgram)
            throw new StudyProgramNotFoundException();
        boolean containsStudent=studentRepository.listAllStudents().stream().anyMatch(student->Integer.parseInt(student.index)==index);
        if(!containsStudent)
            throw new StudentNotFoundException();
        return studentRepository.updateStudent(index,name,lastName,studyProgramName);
    }

    @Override
    public Student deleteStudent(int index) throws StudentNotFoundException {
        Student s = studentRepository.deleteStudent(index);
            if (s==null)
                throw new StudentNotFoundException();
        return s;
    }

    @Override
    public List<StudyProgram> listAllStudyProgram() {
        return studyProgramRepository.listAllStudyProgram();
    }

    @Override
    public StudyProgram addStudyProgram(String studyProgramName) {
        return studyProgramRepository.addStudyProgram(studyProgramName);
    }

    @Override
    public StudyProgram deleteStudyProgram(long id) {
        return studyProgramRepository.deleteStudyProgram(id);
    }
}
