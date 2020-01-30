package org.javastart.homework29.repository;

import org.javastart.homework29.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoriesById(Long id);

    List<Category> findAll();
}