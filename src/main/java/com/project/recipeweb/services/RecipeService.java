package com.project.recipeweb.services;

import com.project.recipeweb.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;
@Service
public interface RecipeService {
    int idCounter = 0;
    Map<Integer, Recipe> recipes = new TreeMap<>();

    Recipe addRecipe(Recipe recipe);
    Recipe getRecipe(int id);

}
