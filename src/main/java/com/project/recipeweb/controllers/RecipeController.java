package com.project.recipeweb.controllers;

import com.project.recipeweb.model.Recipe;
import com.project.recipeweb.services.RecipeService;
import com.project.recipeweb.services.RecipeServiceImpl;
import org.springframework.web.bind.annotation.*;

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
}

