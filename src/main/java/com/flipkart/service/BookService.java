package com.flipkart.service;

import com.flipkart.Model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    public Book addBook(Book book);

    public Optional<Book> getBookById(long id);

    public List<Book> getAllBooks(int pageNumber,int pageSize);

    public List<Book> findByBookName (String bookName);

    public List<Book> findByPublication(String publication);




}
