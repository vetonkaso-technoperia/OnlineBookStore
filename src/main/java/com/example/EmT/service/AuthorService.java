package com.example.EmT.service;

import com.example.EmT.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
    List<Author> findAllByName(String name);
    Author findById(Long id);
    Author save(Author author);
    Author update(Long id, Author author);
    Author updateName(Long id, String name);
    void deleteById(Long id);
}
