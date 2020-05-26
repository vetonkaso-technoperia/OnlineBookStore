package com.example.EmT.repository.impl;

import com.example.EmT.model.Category;
import com.example.EmT.repository.CategoryRepository;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@Profile("in-memory")
public class CategoryRepositoryImpl implements CategoryRepository {

  private HashMap<Long, Category> categories;
  private AtomicLong counter;


  public CategoryRepositoryImpl(){
        this.categories = new HashMap<>();
        this.counter = new AtomicLong(0);
    Category c1 = new Category(5L, "Advanture", "Yakala");
    this.categories.put(c1.getId(), c1);
    Category c2 = new Category(7L, "Fantastic", "HarryPotter");
    this.categories.put(c2.getId(), c2);

  }

  @Override
  public List<Category> findAll() {
    return null;
  }

  @Override
  public List<Category> findAll(Sort sort) {
    return null;
  }

  @Override
  public Page<Category> findAll(Pageable pageable) {
    return null;
  }

  @Override
  public List<Category> findAllById(Iterable<Long> iterable) {
    return null;
  }

  @Override
  public long count() {
    return 0;
  }

  @Override
  public void deleteById(Long aLong) {

  }

  @Override
  public void delete(Category category) {

  }

  @Override
  public void deleteAll(Iterable<? extends Category> iterable) {

  }

  @Override
  public void deleteAll() {

  }

  @Override
  public <S extends Category> List<S> saveAll(Iterable<S> iterable) {
    return null;
  }

  @Override
  public Optional<Category> findById(Long aLong) {
    return Optional.empty();
  }

  @Override
  public Category save(Category category) {
    return null;
  }

  @Override
  public boolean existsById(Long aLong) {
    return false;
  }

  @Override
  public void flush() {

  }

  @Override
  public <S extends Category> S saveAndFlush(S s) {
    return null;
  }

  @Override
  public void deleteInBatch(Iterable<Category> iterable) {

  }

  @Override
  public void deleteAllInBatch() {

  }

  @Override
  public Category getOne(Long aLong) {
    return null;
  }

  @Override
  public <S extends Category> Optional<S> findOne(Example<S> example) {
    return Optional.empty();
  }

  @Override
  public <S extends Category> List<S> findAll(Example<S> example) {
    return null;
  }

  @Override
  public <S extends Category> List<S> findAll(Example<S> example, Sort sort) {
    return null;
  }

  @Override
  public <S extends Category> Page<S> findAll(Example<S> example, Pageable pageable) {
    return null;
  }

  @Override
  public <S extends Category> long count(Example<S> example) {
    return 0;
  }

  @Override
  public <S extends Category> boolean exists(Example<S> example) {
    return false;
  }
}
