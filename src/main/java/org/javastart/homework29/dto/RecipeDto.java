package org.javastart.homework29.dto;

import org.javastart.homework29.model.Ingredient;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class RecipeDto {
    private Long id;

    private String name;
    private String description;
    private String url;

    @OneToMany(mappedBy = "recipeDto", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Ingredient> ingredients = new ArrayList<>();

    private String category;

    public RecipeDto() {
    }

    public RecipeDto(Long id, String name, String description, String url, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}