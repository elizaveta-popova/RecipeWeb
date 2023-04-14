package com.project.recipeweb.controllers;

import com.project.recipeweb.model.Ingredient;
import com.project.recipeweb.services.FileService;
import com.project.recipeweb.services.IngredientService;
import com.project.recipeweb.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/file_service")
@Tag(name = "Операции с файлами", description = "Реализация работы с файлами в формате json.")

public class FileController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    private final FileService fileService;


    public FileController(FileService fileService, RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.fileService = fileService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/files/export/recipes")
    public ResponseEntity<Resource> exportAllRecipes() {
        Resource recipes = recipeService.getRecipesFile();
        ContentDisposition disposition = ContentDisposition.attachment()
                .name("recipes.json")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(disposition);
        return ResponseEntity.ok().headers(headers).body(recipes);
    }

    @PostMapping(value = "/files/import/recipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importRecipes(@RequestParam MultipartFile file) {
        this.recipeService.importRecipes(file.getResource());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/files/export/ingredients")
    public ResponseEntity<Resource> exportAllIngredients() {
        Resource ingredients = ingredientService.getIngredientsFile();
        ContentDisposition disposition = ContentDisposition.attachment()
                .name("ingredients.json")
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(disposition);
        return ResponseEntity.ok().headers(headers).body(ingredients);
    }

    @PostMapping(value = "/files/import/ingredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importIngredients(@RequestParam MultipartFile file) {
        this.ingredientService.importIngredients(file.getResource());
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/export/txt")
    @Operation(summary = "Сохранение рецептов в формате TXT"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Файл загружен успешно."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверный запрос."
            )
    })
    public ResponseEntity<InputStreamResource> saveRecipesTxt() {
        try {
            File file = recipeService.saveRecipesTxt();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeLog.txt\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
