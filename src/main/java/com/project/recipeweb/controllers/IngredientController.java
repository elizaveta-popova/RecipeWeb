package com.project.recipeweb.controllers;

import com.project.recipeweb.services.Ingredient;
import com.project.recipeweb.services.IngredientImpl;
import com.project.recipeweb.services.Recipe;
import com.project.recipeweb.services.RecipeImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class IngredientController {

    private Ingredient ingredient;

    public IngredientController(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @GetMapping("/{id}")
    public long ingredientNumber (@PathVariable long id) {
        return IngredientImpl.ingredientId;
    }

    @GetMapping ("/add")
    public long newAddIngredient () {
        return IngredientImpl.ingredientId;}
    @GetMapping ("/get")
    public long getIngredient () {
        return IngredientImpl.ingredientId;}
}
