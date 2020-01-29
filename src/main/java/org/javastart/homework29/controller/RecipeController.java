package org.javastart.homework29.controller;

import org.javastart.homework29.model.Ingredient;
import org.javastart.homework29.model.Recipe;
import org.javastart.homework29.repository.IngredientRepository;
import org.javastart.homework29.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class RecipeController {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping("/recipe/delete/{id}")
    public String delete(@PathVariable Long id) {
        recipeRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/allRecipies")
    public String findAll(Model model) {
        List<Recipe> allRecipies = recipeRepository.findAll();
        model.addAttribute("allRecipies", allRecipies);
        return "allRecipies";
    }

    @GetMapping("/recipe/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        List<Ingredient> ingredients = recipe.getIngredients();
        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", ingredients);
        return "recipe";
    }

    @GetMapping("/recipe/add")
    public String saveRecipe(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            ingredients.add(new Ingredient());
        }
        Recipe recipe = new Recipe();
        recipe.setIngredients(ingredients);

        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", ingredients);
        return "add";
    }

    @PostMapping("/recipe/save")
    public String saveRecipe(@ModelAttribute Recipe recipe, Model model) {
        ingredientRepository.saveAll(recipe.getIngredients());
        recipeRepository.save(recipe);
        model.addAttribute("allRecipies", recipeRepository.findAll());
        return "redirect:/allRecipies";
    }
}