package mk.ukim.finki.wp.studentsapi.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IndexDigitsException extends Exception {
    public IndexDigitsException()
    {
        super("The index should have 6 digits");
    }
}
