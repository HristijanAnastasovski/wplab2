package mk.ukim.finki.wp.studentsapi.web.rest;

import mk.ukim.finki.wp.studentsapi.model.Student;
import mk.ukim.finki.wp.studentsapi.model.StudyProgram;
import mk.ukim.finki.wp.studentsapi.model.exceptions.IndexDigitsException;
import mk.ukim.finki.wp.studentsapi.model.exceptions.ParameterNumberException;
import mk.ukim.finki.wp.studentsapi.model.exceptions.StudentNotFoundException;
import mk.ukim.finki.wp.studentsapi.model.exceptions.StudyProgramNotFoundException;
import mk.ukim.finki.wp.studentsapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class StudentResource {
    private final StudentService studentService;

    @Autowired
    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents()
    {
        return studentService.listAllStudents();
    }

    @GetMapping("/students/{index}")
    public Student getStudentByIndex(@PathVariable("index") int index) throws StudentNotFoundException
    {
        return studentService.getStudentByIndex(index);
    }

    @GetMapping("/students/by_study_program/{id}")
    public List<Student> getStudentByStudyProgram(@PathVariable("id") long id)
    {
        return studentService.getStudentByProgram(id);
    }

    @RequestMapping(value="/students/{index}",method = RequestMethod.PATCH)
    public Student updateStudent(@PathVariable("index") int index, @RequestParam("name") String name, @RequestParam("lastName") String lastName ,@RequestParam("studyProgramName") String spn) throws StudentNotFoundException,StudyProgramNotFoundException
    {
        return studentService.updateStudent(index,name,lastName,spn);
    }



    @RequestMapping(value="/students",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addNew(@RequestParam("index") String index, @RequestParam("name") String name, @RequestParam("lastName") String lastName ,@RequestParam("studyProgramName") String spn, HttpServletResponse response) throws ParameterNumberException, IndexDigitsException, StudyProgramNotFoundException
    {
        studentService.addStudent(index,name,lastName,spn);
        response.setHeader("Location", "/students/" + index);
    }

    @DeleteMapping(value="/students/{index}")
    public Student deleteStudent(@PathVariable("index") int index) throws StudentNotFoundException
    {
        return studentService.deleteStudent(index);
    }

    @GetMapping("/study_programs")
    public List<StudyProgram> getStudyPrograms()
    {
        return studentService.listAllStudyProgram();
    }

    @RequestMapping(value="/study_programs",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewStudyProgram(@RequestParam("name") String name)
    {
        studentService.addStudyProgram(name);
    }


    @RequestMapping(value = "/study_programs/{id}", method = RequestMethod.DELETE) 
    public StudyProgram deleteStudyProgram(@PathVariable("id") long id)
    {
        return studentService.deleteStudyProgram(id);
    }






}
