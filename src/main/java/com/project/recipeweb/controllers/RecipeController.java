package com.project.recipeweb.controllers;

import com.project.recipeweb.model.Recipe;
import com.project.recipeweb.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Tag(name = "Рецепты", description = "CRUD-операции и энпойнты, связанные с рецептами.")
@RestController
@RequestMapping("/recipe")
@ApiResponses(value = {
        @ApiResponse (
                responseCode = "200",
                description = "Запрос выполнен",
                content = {
                        @Content (
                                mediaType = "application/json",
                                array = @ArraySchema

                        )
                }
        )
})

public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/{id}")
    @Operation (summary = "Получение рецепта по id.",
            description = "Можно найти определённый рецепт из списка, введя его id.")

    public Recipe getRecipe (@PathVariable ("id") int id) {
        return recipeService.getRecipe(id);
    }

    @PostMapping
    @Operation (summary = "Добавление рецепта.",
            description = "Можно добавить рецепт в базу данных.")
    public Recipe addRecipe (@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @GetMapping
    @Operation (summary = "Получение всех рецептов.",
            description = "Можно получить список всех имеющихся рецептов.")
    public List <Recipe> getAllRecipes () {
        return recipeService.getAllRecipes();
    }
    @PutMapping ("/{id}")
    @Operation (summary = "Редактирование рецепта по id.",
            description = "Можно внести изменения в выбранный рецепт.")

    public Recipe editRecipe (@PathVariable ("id") int id, @RequestBody Recipe recipe) {
        return recipeService.editRecipe(id, recipe);
    }

    @DeleteMapping ("/{id}")
    @Operation (summary = "Удаление рецепта по id.",
            description = "Можно удалить выбранный рецепт.")
    public Recipe deleteRecipe (@PathVariable ("id") int id) {
        return recipeService.deleteRecipe (id);
    }
}

