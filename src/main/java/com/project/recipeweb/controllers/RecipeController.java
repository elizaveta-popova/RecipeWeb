package com.project.recipeweb.controllers;

import com.project.recipeweb.model.Recipe;
import com.project.recipeweb.services.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public Recipe getRecipe (@PathVariable ("id") int id) {
        return recipeService.getRecipe(id);
    }

    @PostMapping
    public Recipe addRecipe (@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @GetMapping
    public List <Recipe> getAllRecipes () {
        return recipeService.getAllRecipes();
    }
    @PutMapping ("/{id}")
    public Recipe editRecipe (@PathVariable ("id") int id, @RequestBody Recipe recipe) {
        return recipeService.editRecipe(id, recipe);
    }

    @DeleteMapping ("/{id}")
    public Recipe deleteRecipe (@PathVariable ("id") int id) {
        return recipeService.deleteRecipe (id);
    }
}

