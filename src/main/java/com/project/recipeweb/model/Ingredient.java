package com.project.recipeweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Ingredient {
    private int id;
    private String title;
    private int number;
    private String measure;

    public static Ingredient from(int id, Ingredient ingredient) {
        return ingredient;
    }
}
