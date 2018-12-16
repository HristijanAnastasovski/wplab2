package mk.ukim.finki.wp.studentsapi.persistance.impl;

import mk.ukim.finki.wp.studentsapi.model.Student;
import mk.ukim.finki.wp.studentsapi.model.StudyProgram;
import mk.ukim.finki.wp.studentsapi.persistance.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private static List<Student> students;
    public StudyProgramRepositoryImpl studyProgramRepository;


    @PostConstruct
    public void init()
    {
        students = new ArrayList<>();
        studyProgramRepository= new StudyProgramRepositoryImpl();
       // List<StudyProgram> list =studyProgramRepository.listAllStudyProgram();
       // Student s1=new Student();
       // Student s2=new Student();


       // list=studyProgramRepository.listAllStudyProgram();
/*
        s1.index="161040";
        s1.name="Hristijan";
        s1.lastName="Anastasovski";
        s1.studyProgram=studyProgramRepository.listAllStudyProgram().get(0);

        s2.index="161253";
        s2.name="Bodan";
        s2.lastName="Mojsilovik";
        s2.studyProgram=studyProgramRepository.listAllStudyProgram().get(1);


        students.add(s1);
        students.add(s2);*/

    }



    @Override
    public List<Student> listAllStudents() {
        return students;
    }

    @Override
    public Student getStudentByIndex(int index) {
        for(int i=0;i<students.size();i++)
        {
            if(Integer.parseInt(students.get(i).index)==index)
                return students.get(i);

        }
        return null;
    }

    @Override
    public List<Student> getStudentByProgram(long index) {
        return students
                .stream()
                .filter(student -> student.studyProgram.id==index)
                .collect(Collectors.toList());
    }

    @Override
    public Student addStudent(String index, String name, String lastName, String studyProgramName) {
        Student s = new Student();
        s.index=index;
        s.name=name;
        s.lastName=lastName;
        for(int i=0;i<studyProgramRepository.listAllStudyProgram().size();i++)
        {
            if(studyProgramRepository.listAllStudyProgram().get(i).name.equals(studyProgramName))
            {
                s.studyProgram=studyProgramRepository.listAllStudyProgram().get(i);
            }
        }
        students.add(s);
        return s;
    }

    @Override
    public Student updateStudent(int index, String name, String lastName, String studyProgramName) {
        Student student = null;
        for(int i=0;i<students.size();i++)
        {
            if(Integer.parseInt(students.get(i).index)==index)
                student= students.get(i);

        }

        student.name=name;
        student.lastName=lastName;
        for(int i=0;i<studyProgramRepository.listAllStudyProgram().size();i++)
        {
            if(studyProgramRepository.listAllStudyProgram().get(i).name.equals(studyProgramName))
            {
                student.studyProgram=studyProgramRepository.listAllStudyProgram().get(i);
            }
        }
        return student;

    }

    @Override
    public Student deleteStudent(int index) {
        for(int i=0;i<students.size();i++)
        {
            if(Integer.parseInt(students.get(i).index)==index)
                return students.remove(i);

        }
        return null;
    }
}
