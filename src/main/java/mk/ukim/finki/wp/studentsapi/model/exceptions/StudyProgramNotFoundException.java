package mk.ukim.finki.wp.studentsapi.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudyProgramNotFoundException extends Exception {
    public StudyProgramNotFoundException()
    {
        super("The study program does not exist");
    }
}
