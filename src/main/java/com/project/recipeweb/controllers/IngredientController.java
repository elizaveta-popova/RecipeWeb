package com.project.recipeweb.controllers;

import com.project.recipeweb.model.Ingredient;
import com.project.recipeweb.model.Recipe;
import com.project.recipeweb.services.IngredientService;
import com.project.recipeweb.services.IngredientServiceImpl;
import com.project.recipeweb.services.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient (@PathVariable ("id") int id) {
        return IngredientService.getIngredient(id);
    }

    @PostMapping
    public Ingredient addIngredient (@RequestBody Ingredient ingredient) {
        return IngredientService.addIngredient(ingredient);
    }
}
