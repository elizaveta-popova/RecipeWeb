package com.project.recipeweb.services.impls;

import com.fasterxml.jackson.core.type.TypeReference;
import com.project.recipeweb.exception.RecipeNotFoundException;
import com.project.recipeweb.model.Recipe;
import com.project.recipeweb.services.FileService;
import com.project.recipeweb.services.RecipeService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final static String STORE_FILES = "recipes";
    public static int recipeId = 0;
    private Map<Integer, Recipe> listOfRecipes;

    private final FileService fileService;

    public RecipeServiceImpl(FileService fileService) {
        this.fileService = fileService;
        Map<Integer, Recipe> storedMap = fileService.readFromFile(STORE_FILES, new TypeReference<>() {
        });
        this.listOfRecipes = Objects.requireNonNullElseGet(storedMap, HashMap::new);
    }


    @Override
    public Recipe addRecipe(Recipe recipe) {
        listOfRecipes.put(recipeId++, recipe);
        this.fileService.saveToFile (STORE_FILES, this.listOfRecipes);
        return recipe;
    }
    @Override
    public Recipe getRecipe(int id) {
        return listOfRecipes.get(id);
    }

    @Override
    public List <Recipe> getAllRecipes() {
        List<Recipe> list = new ArrayList<>();
        for (Map.Entry<Integer, Recipe> entry: listOfRecipes.entrySet()) {
            list.add(Recipe.from(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    @Override
    public Recipe editRecipe(int id, Recipe recipe)  {
        Recipe existingRecipe = listOfRecipes.get(id);
        if (existingRecipe == null) {
            throw new RecipeNotFoundException();
        }
        listOfRecipes.put(id, recipe);
        this.fileService.saveToFile (STORE_FILES, this.listOfRecipes);
        return Recipe.from(id, recipe);
    }

    @Override
    public Recipe deleteRecipe(int id) {
        Recipe existingRecipe = listOfRecipes.remove(id);
        if (existingRecipe == null) {
            throw new RecipeNotFoundException();
        }
        this.fileService.saveToFile (STORE_FILES, this.listOfRecipes);
        return Recipe.from(id, existingRecipe);
    }

    public Resource getRecipesFile () {
        return fileService.getResource(STORE_FILES);
    }

    public void importRecipes (Resource resource) {
        fileService.saveResource(STORE_FILES, resource);
        this.listOfRecipes = fileService.readFromFile(STORE_FILES, new TypeReference<>() {
        });
    }

    public File saveRecipesTxt() {
        File file = fileService.addTempFile("recipesReadable");
        for(Recipe recipe : listOfRecipes.values()){
            try(Writer writer = Files.newBufferedWriter(file.toPath(), StandardOpenOption.APPEND)){
                writer.append("Название блюда: " + recipe.getTitle()
                        + "\nВремя приготовления: " + recipe.getCookingTime()
                        + " минут"
                        + "\nШаги: " + recipe.getSteps());
                writer.append("\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }

}
