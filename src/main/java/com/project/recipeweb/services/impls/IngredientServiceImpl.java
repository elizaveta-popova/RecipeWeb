package com.project.recipeweb.services.impls;

import com.project.recipeweb.exception.IngredientNotFoundException;
import com.project.recipeweb.model.Ingredient;
import com.project.recipeweb.services.IngredientService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> list = new ArrayList<>();
        for (Map.Entry<Integer, Ingredient> entry: ingredients.entrySet()) {
            list.add(Ingredient.from(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    @Override
    public Ingredient editIngredient(int id, Ingredient ingredient)  {
        Ingredient existingIngredient = ingredients.get(id);
        if (existingIngredient == null) {
            throw new IngredientNotFoundException();
        }
        ingredients.put(id, ingredient);
        return Ingredient.from(id, ingredient);
    }
    @Override
    public Ingredient deleteIngredient(int id) {
        Ingredient existingIngredient = ingredients.remove(id);
        if (existingIngredient == null) {
            throw new IngredientNotFoundException();
        }
        return Ingredient.from(id, existingIngredient);
    }


}
