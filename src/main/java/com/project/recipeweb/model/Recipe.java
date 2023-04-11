package com.project.recipeweb.model;

import com.project.recipeweb.services.IngredientService;
import com.project.recipeweb.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Recipe {
    private int id;
    private String title;
    private int cookingTime;
    private List <IngredientService> ingredientServices;
    private List <String> steps;
    private Ingredient ingredient;

    public static Recipe from(int id, Recipe recipe) {
        return recipe;
    }
}
