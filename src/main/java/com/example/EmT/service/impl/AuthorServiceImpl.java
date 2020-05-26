package com.example.EmT.service.impl;

import com.example.EmT.model.Author;
import com.example.EmT.model.exception.AuthorNotFoundException;
import com.example.EmT.repository.AuthorRepository;
import com.example.EmT.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;


//ovoj Bean kje se kreira samo dokolku aktiveniot profil NE e amazon (ova vazhi i nieden profil da ne e aktiven)
//i vo toj sluchaj
//sekade kade shto se koristi AuthorService interfejsot (kako vo kontrolerite)
//implementacijata koja kje ja prepoznae kje bide ovaa namesto AuthorAmazonServiceImpl
@Service
@Profile("!amazon")
public class AuthorServiceImpl implements AuthorService {


    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public List<Author> findAllByName(String name) {
//        return this.authorRepository.findAllByName(name);
        return null;
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @Override
    public Author save(Author author) {
        return this.authorRepository.save(author);
    }

    @Override
    public Author update(Long id, Author author) {
        Author m = this.findById(id);
        m.setName(author.getName());
        m.setAddress(author.getAddress());
        return this.authorRepository.save(m);
    }

    @Override
    public Author updateName(Long id, String name) {
        Author m = this.findById(id);
        m.setName(name);
        return this.authorRepository.save(m);
    }

    @Override
    public void deleteById(Long id) {
//        this is correct, you should check if author has books
//        only author without books could be deleted
//        Author author = this.findById(id);
//        if (author.getBooks().size() > 0) {
//            //throw exception
//        }

//        this is if you want to delete the relationship, but it will fail because author
//        is  annotated @NotNull in books
//        author.getBooks()
//                .forEach(book -> book.setAuthor(null));

        this.authorRepository.deleteById(id);
    }
}
