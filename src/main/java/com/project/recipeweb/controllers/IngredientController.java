package com.project.recipeweb.controllers;

import com.project.recipeweb.model.Ingredient;
import com.project.recipeweb.services.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient (@PathVariable ("id") int id) {
        return ingredientService.getIngredient(id);
    }

    @PostMapping
    public Ingredient addIngredient (@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @GetMapping
    public List<Ingredient> getAllIngredients () {
        return ingredientService.getAllIngredients();
    }
    @PutMapping("/{id}")
    public Ingredient editIngredient (@PathVariable ("id") int id, @RequestBody Ingredient ingredient) {
        return ingredientService.editIngredient(id, ingredient);
    }

    @DeleteMapping ("/{id}")
    public Ingredient deleteIngredient (@PathVariable ("id") int id) {
        return ingredientService.deleteIngredient (id);
    }
}
