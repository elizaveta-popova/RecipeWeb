package com.project.recipeweb.services.impls;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.recipeweb.exception.IngredientNotFoundException;
import com.project.recipeweb.model.Ingredient;
import com.project.recipeweb.model.Recipe;
import com.project.recipeweb.services.FileService;
import com.project.recipeweb.services.IngredientService;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static final String STORE_FILES = "ingredients";
    private final FileService fileService;
    public static int ingredientId = 0;
    private final Map<Integer, Ingredient> listOfIngredients;

    public IngredientServiceImpl(FileService fileService) {
        this.fileService = fileService;
        Map<Integer, Ingredient> storedMap = fileService.readFromFile(STORE_FILES, new TypeReference<>() {
        });
        this.listOfIngredients = Objects.requireNonNullElseGet(storedMap, HashMap::new);
    }



    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        listOfIngredients.put(ingredientId++, ingredient);
        fileService.saveToFile (STORE_FILES, ingredients);
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
        fileService.saveToFile (STORE_FILES, ingredients);
        return Ingredient.from(id, ingredient);
    }
    @Override
    public Ingredient deleteIngredient(int id) {
        Ingredient existingIngredient = ingredients.remove(id);
        if (existingIngredient == null) {
            throw new IngredientNotFoundException();
        }
        fileService.saveToFile (STORE_FILES, ingredients);
        return Ingredient.from(id, existingIngredient);
    }


}
