package org.javastart.homework29.controller;

import org.javastart.homework29.model.Category;
import org.javastart.homework29.model.Ingredient;
import org.javastart.homework29.model.Recipe;
import org.javastart.homework29.repository.CategoryRepository;
import org.javastart.homework29.repository.IngredientRepository;
import org.javastart.homework29.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class RecipeController {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private CategoryRepository categoryRepository;


    @Autowired
    public RecipeController(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/category")
    public String RecipiesBycategory(Model model, @RequestParam Long category_Id) {
        List<Recipe> recipiesByCategory;
        if (category_Id != null) {
            recipiesByCategory = recipeRepository.findRecipesByCategory_Id(category_Id);
            Category categoriesById = categoryRepository.findCategoriesById(category_Id);
            model.addAttribute("recipiesByCategory", recipiesByCategory);
            model.addAttribute("category", categoriesById);
            return "category";
        } else {
            return "redirect:/";
        }
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
        List<Category> categories = categoryRepository.findAll();
        Recipe recipe = new Recipe();
        recipe.setIngredients(ingredients);

        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("categories", categories);
        return "add";
    }

    @PostMapping("/recipe/save")
    public String saveRecipe(@ModelAttribute Recipe recipe, Model model) {


        ingredientRepository.saveAll(recipe.getIngredients());
        recipe.getIngredients()
                .forEach(ingredient -> ingredient.setRecipe(recipe));
        recipeRepository.save(recipe);

        model.addAttribute("allRecipies", recipeRepository.findAll());
        return "redirect:/allRecipies";
    }
}