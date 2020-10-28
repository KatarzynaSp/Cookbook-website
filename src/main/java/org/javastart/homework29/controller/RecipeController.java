package org.javastart.homework29.controller;

import org.javastart.homework29.dto.CategoryDto;
import org.javastart.homework29.dto.RecipeDto;
import org.javastart.homework29.form.RecipeForm;
import org.javastart.homework29.model.Ingredient;
import org.javastart.homework29.service.CategoryService;
import org.javastart.homework29.service.IngredientService;
import org.javastart.homework29.service.RecipeSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/recipe")
@Controller
public class RecipeController {

    private RecipeSevice recipeSevice;
    private IngredientService ingredientService;
    private CategoryService categoryService;

    @Autowired
    public RecipeController(RecipeSevice recipeSevice, IngredientService ingredientService, CategoryService categoryService) {
        this.recipeSevice = recipeSevice;
        this.ingredientService = ingredientService;
        this.categoryService = categoryService;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        recipeSevice.delete(id);
        return "redirect:/";
    }

    @GetMapping("/all")
    public String findAll(Model model) {
        List<RecipeDto> allDto = recipeSevice.findAll();
        List<CategoryDto> categories = categoryService.findCategories();
        model.addAttribute("allDto", allDto);
        model.addAttribute("categories", categories);
        return "all";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        RecipeDto recipeDto = recipeSevice.findById(id);
        List<Ingredient> ingredients = recipeDto.getIngredients();
        model.addAttribute("recipeDto", recipeDto);
        model.addAttribute("ingredients", ingredients);
        return "recipe";
    }

    @GetMapping("/add")
    public String saveRecipe(Model model) {
        RecipeDto recipeDto = recipeSevice.addRecipe();
        List<CategoryDto> categories = categoryService.findCategories();
        model.addAttribute("recipe", recipeDto);
        model.addAttribute("categories", categories);
        return "add";
    }

    @PostMapping("/save")
    public String saveRecipe(@ModelAttribute RecipeForm recipeForm) {
        recipeSevice.saveRecipe(recipeForm);
       return "redirect:/recipe/all";
    }
}