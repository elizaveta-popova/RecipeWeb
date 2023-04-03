package com.project.recipeweb.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
@Service
public class RecipeImpl implements Recipe {

    public static long recipeId = 0;
    private final Map<Integer, Recipe> listOfRecipes = new TreeMap<>();


    @Override
    public Recipe addRecipe(Recipe recipe) {
        Map <Long, Recipe> newRecipe = listOfRecipes.getOrDefault(listOfRecipes, new LinkedHashMap<>());
        newRecipe.put(recipeId++, recipe);
        return recipe;
    }
    @Override
    public Recipe getRecipe(int recipeNumber) {
        return listOfRecipes.get(recipeNumber);
    }
}
