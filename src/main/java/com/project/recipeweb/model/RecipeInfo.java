package com.project.recipeweb.model;

import com.project.recipeweb.services.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RecipeInfo {
    private String name;
    private int cookingTimeInMin;
    private List <Ingredient> ingredients;
    private List <String> steps;
}
