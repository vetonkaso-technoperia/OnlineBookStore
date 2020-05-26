package com.example.EmT.repository;

import com.example.EmT.model.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!in-memory")
public interface CategoryRepository extends  JpaRepository<Category, Long> {

    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category save(Category category);
    void deleteById(Long id);

}
