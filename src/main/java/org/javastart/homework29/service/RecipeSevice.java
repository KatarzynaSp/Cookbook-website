package org.javastart.homework29.service;

import org.javastart.homework29.dto.RecipeDto;
import org.javastart.homework29.form.RecipeForm;
import org.javastart.homework29.model.Category;
import org.javastart.homework29.model.Ingredient;
import org.javastart.homework29.model.Recipe;
import org.javastart.homework29.repository.CategoryRepository;
import org.javastart.homework29.repository.IngredientRepository;
import org.javastart.homework29.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeSevice {

    private RecipeRepository recipeRepository;
    private IngredientRepository ingredientRepository;
    private CategoryRepository categoryRepository;

    public RecipeSevice(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
        this.categoryRepository = categoryRepository;
    }


    public void saveRecipe(RecipeForm form) {
        String categoryName = form.getCategory();
        Category category = categoryRepository.findByName(categoryName);
        List<Ingredient> ingredients = form.getIngredients();
        Recipe recipe = new Recipe(form.getName(), form.getDescription(), form.getUrl(), ingredients);

        ingredientRepository.saveAll(recipe.getIngredients());
        form.getIngredients()
                .forEach(ingredient -> ingredient.setRecipe(recipe));
        recipeRepository.save(recipe);
    }

    public RecipeDto addRecipe() {
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            ingredients.add(new Ingredient());
        }
        RecipeDto recipeDto = new RecipeDto();
        recipeDto.setIngredients(ingredients);
        return recipeDto;
    }

    public List<RecipeDto> recipiesByCategory(Long id) {
        List<Recipe> recipes = recipeRepository.findRecipesByCategory_Id(id);
        return recipes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<RecipeDto> showRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(this::toDto)
                .limit(8)
                .collect(Collectors.toList());
    }

    public List<RecipeDto> findAll() {
        return recipeRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }

    public RecipeDto findById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        RecipeDto recipeDto = toDto(recipe);
        List<Ingredient> ingredients = recipe.getIngredients();
        recipeDto.setIngredients(ingredients);
        return recipeDto;
    }

    private RecipeDto toDto(Recipe recipe) {
        return new RecipeDto(recipe.getId(), recipe.getName(), recipe.getDescription(), recipe.getUrl(), recipe.getCategory().getName());
    }

    public void delete(Long id) {
        recipeRepository.deleteById(id);
    }
}