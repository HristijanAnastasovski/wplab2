package mk.ukim.finki.wp.studentsapi.persistance;

import mk.ukim.finki.wp.studentsapi.model.StudyProgram;

import java.util.List;

public interface StudyProgramRepository {
    List<StudyProgram> listAllStudyProgram();

    StudyProgram addStudyProgram(String studyProgramName);

    StudyProgram deleteStudyProgram(long id);
}
