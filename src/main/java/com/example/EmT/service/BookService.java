package com.example.EmT.service;

import com.example.EmT.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {
    List<Book> findAll();
    List<Book> findAllByAuthorId(Long authorId);
    List<Book> findAllSortedByPrice(boolean asc);

  List<Book> findAllByCategoryId(Long categoryId);

  Book findById(Long id);
    Book saveBook(String name, Float price, Integer quantity, Long authorId);

  Book save(Book book, MultipartFile image) throws IOException;

  Book update(Long id, Book books, MultipartFile image) throws IOException;

  Book saveBook(Book book, MultipartFile image) throws IOException;
    Book updateBook(Long id, Book book, MultipartFile image) throws IOException;
    void deleteById(Long id);
}
