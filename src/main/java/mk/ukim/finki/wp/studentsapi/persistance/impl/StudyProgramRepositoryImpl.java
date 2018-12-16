package mk.ukim.finki.wp.studentsapi.persistance.impl;

import mk.ukim.finki.wp.studentsapi.model.StudyProgram;
import mk.ukim.finki.wp.studentsapi.persistance.StudyProgramRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudyProgramRepositoryImpl implements StudyProgramRepository {

    public static List<StudyProgram> studyPrograms;


    public StudyProgramRepositoryImpl()
    {
        studyPrograms=new ArrayList<>();
//        StudyProgram s1 = new StudyProgram();
//        StudyProgram s2 = new StudyProgram();
//        s1.name="KNI";
//        s2.name="PET";
//        studyPrograms.add(s1);
//        studyPrograms.add(s2);
    }


    @Override
    public List<StudyProgram> listAllStudyProgram() {
        return studyPrograms;
    }

    @Override
    public StudyProgram addStudyProgram(String studyProgramName) {
        StudyProgram sp = new StudyProgram();
        sp.name=studyProgramName;
        studyPrograms.add(sp);
        return sp;
    }

    @Override
    public StudyProgram deleteStudyProgram(long id) {

        for(int i=0;i<studyPrograms.size();i++)
        {
            if(studyPrograms.get(i).id==id)
                return studyPrograms.remove(i);
        }
        return null;

    }
}
