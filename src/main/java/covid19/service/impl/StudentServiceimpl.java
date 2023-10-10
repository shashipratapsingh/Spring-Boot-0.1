package covid19.service.impl;

import covid19.model.Author;
import covid19.model.Student;
import covid19.repository.AuthorRepository;
import covid19.repository.StudentRepository;
import covid19.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceimpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student add1(Student student) {
        return studentRepository.save(student);
    }


}
