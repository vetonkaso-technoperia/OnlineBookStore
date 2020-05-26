package com.example.EmT.service.impl;

import com.example.EmT.model.Author;
import com.example.EmT.service.AuthorService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

//ovoj Bean kje se kreira samo dokolku aktiven profil e amazon i vo toj sluchaj
//sekade kade shto se koristi AuthorService interfejsot (kako vo kontrolerite)
//implementacijata koja kje ja prepoznae kje bide ovaa namesto AuthorServiceImpl
//momentalno ne pravi nishto, samo za primer za profili go napraviv
@Service
@Profile("amazon")
public class AuthorAmazonServiceImpl implements AuthorService {
    @Override
    public List<Author> findAll() {
        return null;
    }

    @Override
    public List<Author> findAllByName(String name) {
        return null;
    }

    @Override
    public Author findById(Long id) {
        return null;
    }

    @Override
    public Author save(Author author) {
        return null;
    }

    @Override
    public Author update(Long id, Author author) {
        return null;
    }

    @Override
    public Author updateName(Long id, String name) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
