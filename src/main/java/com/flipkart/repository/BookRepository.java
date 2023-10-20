package com.flipkart.repository;

import com.flipkart.Model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByBookName(String bookName);

    Optional<Book> findByBookRegisterId(String bookRegisterId);


    List<Book> findByPublication(String publication);
}
