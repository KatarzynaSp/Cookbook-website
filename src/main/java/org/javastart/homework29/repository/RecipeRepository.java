package org.javastart.homework29.repository;

import org.javastart.homework29.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Optional<Recipe> findById(Long id);

    List<Recipe> findAll();

    List<Recipe> findRecipesByCategory_Id(Long id);
}