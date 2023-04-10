package com.project.recipeweb.services;

import com.project.recipeweb.model.Recipe;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
@Service
public interface RecipeService {
    int idCounter = 0;
    Map<Integer, Recipe> recipes = new TreeMap<>();

    Recipe addRecipe(Recipe recipe);
    Recipe getRecipe(int id);

    List<Recipe> getAllRecipes();

    Recipe editRecipe(int id, Recipe recipe);

    Recipe deleteRecipe(int id);

    Resource getRecipesFile();

    void importRecipes(Resource resource);
}
