package org.javastart.homework29.repository;

import org.javastart.homework29.model.Ingredient;
import org.javastart.homework29.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findByRecipe(Recipe recipe);

    @Modifying
    @Transactional
    void deleteByRecipeId(Long id);
}
