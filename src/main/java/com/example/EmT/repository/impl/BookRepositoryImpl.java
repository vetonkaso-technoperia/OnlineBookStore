package com.example.EmT.repository.impl;


import com.example.EmT.model.Book;
import com.example.EmT.repository.BookRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private HashMap<Long, Book> books;
    private Long counter;



    @PostConstruct
    public void init() {
        this.counter = 0L;
        this.books = new HashMap<>();
        Long id = this.generateKey();
        Long quantity = this.generateKey();

        this.books.put(id, new Book(id,"p1",quantity,123f,3, null, null));
        id = this.generateKey();
        this.books.put(id, new Book(id,"p1",quantity,123f,3, null, null));
    }


    @Override
    public List<Book> findAll() {
        return new ArrayList<>(this.books.values());
    }

    @Override
    public List<Book> findAll(Sort sort) {
        return null;
    }

    @Override
    public List<Book> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public <S extends Book> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Book> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Book> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Book getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Book> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Book> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Book> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Book> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Book> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Book> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public List<Book> findAllByOrderByPriceAsc() {
        return null;
    }

    @Override
    public List<Book> findAllByOrderByPriceDesc() {
        return null;
    }

  @Override
  public List<Book> findAllByCategoryId(Long categoryId) {
    return null;
  }

  @Override
    public long countAllByPriceGreaterThan(Float price) {
        return 0;
    }

    @Override
    public List<Book> findAllByPriceGreaterThan(Float price) {
        return null;
    }

    @Override
    public List<Book> findAllSortedByPrice(boolean asc) {
        Comparator<Book> priceComparator = Comparator.comparing(Book::getPrice);
        if (!asc) {
            priceComparator = priceComparator.reversed();
        }
        return this.books.values()
                .stream()
                .sorted(priceComparator)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllByAuthorId(Long authorId) {
        return this.books.values()
                .stream()
                .filter(item -> item.getAuthor().getId().equals(authorId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> findAllByNameLikeAndAuthorIdOrderByPriceDesc(String name, Long authorId) {
        return null;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(this.books.get(id));
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            book.setId(this.generateKey());
        }
        this.books.put(book.getId(), book);
        return book;
    }

    @Override
    public void deleteById(Long id) {
        this.books.remove(id);
    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public void deleteAll(Iterable<? extends Book> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    private Long generateKey() {
        return ++counter;
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return null;
    }
}
