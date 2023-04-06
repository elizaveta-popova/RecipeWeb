package com.project.recipeweb.services.impls;

import com.project.recipeweb.exception.RecipeNotFoundException;
import com.project.recipeweb.model.Recipe;
import com.project.recipeweb.services.RecipeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List <Recipe> getAllRecipes() {
        List<Recipe> list = new ArrayList<>();
        for (Map.Entry<Integer, Recipe> entry: recipes.entrySet()) {
            list.add(Recipe.from(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    @Override
    public Recipe editRecipe(int id, Recipe recipe)  {
        Recipe existingRecipe = recipes.get(id);
        if (existingRecipe == null) {
            throw new RecipeNotFoundException();
        }
        recipes.put(id, recipe);
        return Recipe.from(id, recipe);
    }

    @Override
    public Recipe deleteRecipe(int id) {
        Recipe existingRecipe = recipes.remove(id);
        if (existingRecipe == null) {
            throw new RecipeNotFoundException();
        }
        return Recipe.from(id, existingRecipe);
    }
}
