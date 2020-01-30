package org.javastart.homework29.controller;


import org.javastart.homework29.repository.IngredientRepository;
import org.javastart.homework29.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class IngredientController {
    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientController(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }
}