package com.project.recipeweb.services;

import com.project.recipeweb.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;
@Service
public class RecipeServiceImpl implements RecipeService {
    public static int recipeId = 0;
    private final Map<Integer, Recipe> listOfRecipes = new TreeMap<>();


    @Override
    public Recipe addRecipe(Recipe recipe) {
        listOfRecipes.put(recipeId++, recipe);
        return recipe;
    }
    @Override
    public Recipe getRecipe(int id) {
        return listOfRecipes.get(id);
    }
}
