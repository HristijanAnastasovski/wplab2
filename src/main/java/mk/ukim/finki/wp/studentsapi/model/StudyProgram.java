package mk.ukim.finki.wp.studentsapi.model;

public class StudyProgram {
    public long id;
    public String name;
    public static long idCounter=1;

    public StudyProgram()
    {
        id=idCounter;
        StudyProgram.idCounter++;
    }
}
