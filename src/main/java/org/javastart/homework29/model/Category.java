package org.javastart.homework29.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String url;

    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Recipe> recipies;

    public Category(String name, String description, String url, List<Recipe> recipies) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.recipies = recipies;
    }

    public void addRecipies(Recipe recipe) {
        recipies.add(recipe);
        recipe.setCategory(this);
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

    public List<Recipe> getRecipies() {
        return recipies;
    }

    public void setRecipies(List<Recipe> recipies) {
        this.recipies = recipies;
    }
}
