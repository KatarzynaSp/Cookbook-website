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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/category")
@Controller
public class CategoryController {
    private RecipeSevice recipeSevice;
    private IngredientService ingredientService;
    private CategoryService categoryService;

    @Autowired
    public CategoryController(RecipeSevice recipeSevice, IngredientService ingredientService, CategoryService categoryService) {
        this.recipeSevice = recipeSevice;
        this.ingredientService = ingredientService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String category(Model model) {
        List<CategoryDto> categories = categoryService.findCategories();
        model.addAttribute("categories", categories);
        return "fragments";
    }
    @GetMapping("/categories")
    public String allCategories(Model model) {
        List<CategoryDto> categories = categoryService.findCategories();
        model.addAttribute("categories", categories);
        return "allCategories";
    }

    @GetMapping("/{id}")
    public String recipiesBycategory(@PathVariable Long id, Model model) {
        List<RecipeDto> recipeList = recipeSevice.recipiesByCategory(id);
        CategoryDto categoryDto = categoryService.categoryById(id);
        List<CategoryDto> categories = categoryService.findCategories();
        model.addAttribute("recipeList", recipeList);
        model.addAttribute("categoryDto", categoryDto);
        model.addAttribute("categories", categories);
        return "category";
    }
}