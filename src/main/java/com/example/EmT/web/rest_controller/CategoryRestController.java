package com.example.EmT.web.rest_controller;

import com.example.EmT.model.Category;
import com.example.EmT.service.CategoryService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

  private final CategoryService categoryService;

  public CategoryRestController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public List<Category> findAll(@RequestParam(required = false) String name) {
      return this.categoryService.findAll();
  }

  @GetMapping("/{id}")
  public Category findById(@PathVariable Long id) {
    return this.categoryService.findById(id);
  }


  @PostMapping
  public Category save(@Valid Category category) {
    return this.categoryService.save(category);
  }

  @PutMapping("/{id}")
  public Category update(@PathVariable Long id, @Valid Category category) {
    return this.categoryService.update(id, category);
  }


  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    this.categoryService.deleteById(id);
  }


}
