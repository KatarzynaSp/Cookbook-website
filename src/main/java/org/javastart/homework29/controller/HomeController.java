package org.javastart.homework29.controller;

import org.javastart.homework29.dto.CategoryDto;
import org.javastart.homework29.dto.RecipeDto;
import org.javastart.homework29.service.CategoryService;
import org.javastart.homework29.service.IngredientService;
import org.javastart.homework29.service.RecipeSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private RecipeSevice recipeService;
    private IngredientService ingredientService;
    private CategoryService categoryService;

    @Autowired
    public HomeController(RecipeSevice recipeService, IngredientService ingredientService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<RecipeDto> recipiesDto = recipeService.showRecipes();
        List<CategoryDto> categories = categoryService.findCategories();

        model.addAttribute("recipies", recipiesDto);
        model.addAttribute("categories", categories);
        return "home";
    }
}