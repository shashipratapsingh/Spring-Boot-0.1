package covid19.controller;

import covid19.model.Author;
import covid19.model.Student;
import covid19.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    private StudentService studentService;



    @PostMapping("/add")
    public ResponseEntity<Student> add1(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.add1(student), HttpStatus.CREATED);
    }

}
