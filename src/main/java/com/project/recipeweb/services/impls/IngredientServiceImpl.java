package com.project.recipeweb.services.impls;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.recipeweb.exception.IngredientNotFoundException;
import com.project.recipeweb.model.Ingredient;
import com.project.recipeweb.services.FileService;
import com.project.recipeweb.services.IngredientService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {
    private static final String STORE_FILES = "ingredients";
    private final FileService fileService;
    public static int ingredientId = 0;
    private final Map<Integer, Ingredient> listOfIngredients = new TreeMap<>();

    private HashMap<Long, ArrayList<String>> operations;

    @PostConstruct
    private void init() {
        try {
            String json = fileService.readFromFile(" ./json-files/");
            operations = new ObjectMapper().readValue(
                    json,
                    new TypeReference<HashMap<Long, ArrayList<String>>>() {}
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public IngredientServiceImpl(FileService fileService) {
        this.fileService = fileService;
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
