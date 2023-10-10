package covid19.service.impl;

import covid19.model.Author;
import covid19.model.Student;
import covid19.repository.AuthorRepository;
import covid19.service.AuthorService;
import covid19.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }
}
