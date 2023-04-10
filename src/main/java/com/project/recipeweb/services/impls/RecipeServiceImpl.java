package com.project.recipeweb.services.impls;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.recipeweb.exception.RecipeNotFoundException;
import com.project.recipeweb.model.Recipe;
import com.project.recipeweb.services.FileService;
import com.project.recipeweb.services.RecipeService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final static String STORE_FILES = "recipes";

    public static int recipeId = 0;
    private final Map<Integer, Recipe> listOfRecipes = new TreeMap<>();

    private final FileService fileService;

    public RecipeServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }
    private HashMap<Long, ArrayList<String>> operations;

    @PostConstruct
    private void init() {
        try {
            String json = fileService.readFromFile("./json-files/");
            operations = new ObjectMapper().readValue(
                    json,
                    new TypeReference<HashMap<Long, ArrayList<String>>>() {}
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Recipe addRecipe(Recipe recipe) {
        listOfRecipes.put(recipeId++, recipe);
        this.fileService.saveToFile (STORE_FILES, this.recipes);
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
        this.fileService.saveToFile (STORE_FILES, this.recipes);
        return Recipe.from(id, recipe);
    }

    @Override
    public Recipe deleteRecipe(int id) {
        Recipe existingRecipe = recipes.remove(id);
        if (existingRecipe == null) {
            throw new RecipeNotFoundException();
        }
        this.fileService.saveToFile (STORE_FILES, this.recipes);
        return Recipe.from(id, existingRecipe);
    }
}
