package com.project.recipeweb.services;

import com.project.recipeweb.model.Ingredient;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
@Service
public interface IngredientService {
    Map<Integer, Ingredient> listOfIngredients = new TreeMap<>();

    Ingredient addIngredient(Ingredient ingredient);

    Ingredient getIngredient(int ingredientNumber);

    List<Ingredient> getAllIngredients();

    Ingredient editIngredient(int id, Ingredient ingredient);

    Ingredient deleteIngredient(int id);

    void importIngredients(Resource resource);

    Resource getIngredientsFile();
}
