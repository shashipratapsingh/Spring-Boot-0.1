package com.flipkart.controller;

import com.flipkart.Model.Book;
import com.flipkart.repository.BookRepository;
import com.flipkart.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @PostMapping("/addBook")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(this.bookService.addBook(book), HttpStatus.CREATED);
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable long id) {
        return new ResponseEntity<>(this.bookService.getBookById(id),HttpStatus.OK);
    }
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam int pageNumber,@RequestParam int pageSize) {

        return new ResponseEntity<>(this.bookService.getAllBooks(pageNumber,pageSize),HttpStatus.OK);
    }
    @GetMapping("/search/{bookName}")
    public ResponseEntity<List<Book>> searchBooksByName(@PathVariable String bookName) {
        return new ResponseEntity<>(bookService.findByBookName(bookName),HttpStatus.OK);
    }
    @GetMapping("/searchByPublication/{publication}")
    public List<Book> findByPublication(@PathVariable String publication) {
        return this.bookService.findByPublication(publication);
    }









}
