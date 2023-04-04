package com.project.recipeweb.services;

import com.project.recipeweb.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;
@Service
public class IngredientServiceImpl implements IngredientService {
    public static int ingredientId = 0;
    private final Map<Integer, Ingredient> listOfIngredients = new TreeMap<>();


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        listOfIngredients.put(ingredientId++, ingredient);
        return ingredient;
    }
    @Override
    public Ingredient getIngredient(int ingredientNumber) {
        return listOfIngredients.get(ingredientNumber);
    }
}
