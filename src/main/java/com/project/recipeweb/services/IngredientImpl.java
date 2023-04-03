package com.project.recipeweb.services;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
@Service
public class IngredientImpl implements Ingredient {
    public static long ingredientId = 0;
    private final Map<Integer, Ingredient> listOfIngredients = new TreeMap<>();


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        Map <Long, Ingredient> newRecipe = listOfIngredients.getOrDefault(listOfIngredients, new LinkedHashMap<>());
        newRecipe.put(ingredientId++, ingredient);
        return ingredient;
    }
    @Override
    public Ingredient getIngredient(int ingredientNumber) {
        return listOfIngredients.get(ingredientNumber);
    }
}
