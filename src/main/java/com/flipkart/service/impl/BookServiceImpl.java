package com.flipkart.service.impl;

import com.flipkart.Model.Book;
import com.flipkart.repository.BookRepository;
import com.flipkart.repository.UserRepository;
import com.flipkart.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService ,AuthenticationProvider {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Book addBook(Book book) {
        String currentUsername = getCurrentUsername();
        book.setAddedBy(currentUsername);
        //calculate quantities
        String bookRegisterId = book.getBookRegisterId();
        Optional<Book> existingStock = bookRepository.findByBookRegisterId(bookRegisterId);
       if (existingStock.isPresent()) {
            book = existingStock.get();
            book.setQuantity(book.getQuantity()+ 1);
            bookRepository.save(book);
        } else {
            book.setBookRegisterId(bookRegisterId);
            book.setQuantity(1);
            bookRepository.save(book);
        }
        return this.bookRepository.save(book);
    }

    @Override
    public Optional<Book> getBookById(long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public List<Book> getAllBooks(int pageNumber,int pageSize) {
        Pageable pages= PageRequest.of(pageNumber,pageSize, Sort.Direction.ASC,"id");
        return this.bookRepository.findAll(pages).getContent();
    }

    @Override
    public List<Book> findByBookName(String bookName) {
        return this.bookRepository.findByBookName(bookName);
    }

    @Override
    public List<Book> findByPublication(String publication) {
        return this.bookRepository.findByPublication(publication);
    }

    /*---------------user name fetching---------------------*/
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException  {
        String email=authentication.getName();
        System.out.println(email);
       return null;
   }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        }
        return "Unknown";
    }




}
