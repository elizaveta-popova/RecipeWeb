package com.project.recipeweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientInfo {
    private String name;
    private int weight;
    private String measureUnite;
}
