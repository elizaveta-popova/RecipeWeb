package com.project.recipeweb.controllers;

import com.project.recipeweb.model.Ingredient;
import com.project.recipeweb.services.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты", description = "CRUD-операции и энпойнты, связанные с ингредиентами.")
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Запрос выполнен",
                content = {
                        @Content(
                                mediaType = "application/json",
                                array = @ArraySchema

                        )
                }
        )
})
public class IngredientController {

    private IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение ингредиента по id.",
            description = "Можно найти определённый ингредиент из списка, введя его id.")

    public Ingredient getIngredient (@PathVariable ("id") int id) {
        return ingredientService.getIngredient(id);
    }

    @PostMapping
    @Operation (summary = "Добавление ингредиента.",
            description = "Можно добавить ингредиент в базу данных.")
    public Ingredient addIngredient (@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @GetMapping
    @Operation (summary = "Получение всех ингредиентов.",
            description = "Можно получить список всех имеющихся ингредиентов.")
    public List<Ingredient> getAllIngredients () {
        return ingredientService.getAllIngredients();
    }
    @PutMapping("/{id}")
    @Operation (summary = "Редактирование ингредиента по id.",
            description = "Можно внести изменения в выбранный ингредиент.")

    public Ingredient editIngredient (@PathVariable ("id") int id, @RequestBody Ingredient ingredient) {
        return ingredientService.editIngredient(id, ingredient);
    }

    @DeleteMapping ("/{id}")
    @Operation (summary = "Удаление ингредиента по id.",
            description = "Можно удалить выбранный ингредиент.")
    public Ingredient deleteIngredient (@PathVariable ("id") int id) {
        return ingredientService.deleteIngredient (id);
    }
}
