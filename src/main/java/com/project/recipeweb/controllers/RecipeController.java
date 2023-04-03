package com.project.recipeweb.controllers;

import com.project.recipeweb.services.Recipe;
import com.project.recipeweb.services.RecipeImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recipe")
public class RecipeController {
    private Recipe recipe;

    public RecipeController(Recipe recipe) {
        this.recipe = recipe;
    }

    @GetMapping("/{id}")
    public long recipeNumber (@PathVariable long id) {
        return RecipeImpl.recipeId;
    }

    @GetMapping ("/add")
    public long newAddReceipt () {
        return RecipeImpl.recipeId;}
}

