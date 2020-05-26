package com.example.EmT.service.impl;

import com.example.EmT.model.Category;
import com.example.EmT.model.exception.CategoryNotFoundException;
import com.example.EmT.repository.CategoryRepository;
import com.example.EmT.service.CategoryService;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!amazon")
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Override
  public List<Category> findAll() {
    return this.categoryRepository.findAll();
  }

  @Override
  public Category findById(Long id) {
    return this.categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
  }

  @Override
  public Category save(Category category) {
    return this.categoryRepository.save(category);
  }

  @Override
  public Category update(Long id, Category category) {
    Category m = this.findById(id);
    m.setName(category.getName());
    m.setDescription(category.getDescription());
    return this.categoryRepository.save(m);
  }

  @Override
  public void deleteById(Long id) {
    this.categoryRepository.deleteById(id);
  }
}
