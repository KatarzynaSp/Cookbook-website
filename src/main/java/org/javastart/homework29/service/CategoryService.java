package org.javastart.homework29.service;

import org.javastart.homework29.dto.CategoryDto;
import org.javastart.homework29.model.Category;
import org.javastart.homework29.repository.CategoryRepository;
import org.javastart.homework29.repository.IngredientRepository;
import org.javastart.homework29.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto categoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        return toDto(category);
    }


    public List<CategoryDto> findCategories() {
        List<Category> all = categoryRepository.findAll();
        return all.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private CategoryDto toDto (Category category){
        return  new CategoryDto(category.getId(), category.getName(), category.getDescription(), category.getUrl());
    }
}