package com.icrn.yamr.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Recipe {

    @Id
    private String id;
    private String name;
    private String time;
    private String description;
    private User owner;
    private Set<Ingredient> ingredients;

    public Recipe(String name, String time, String description, Set<Ingredient> ingredients,User owner) {
        this.name = name;
        this.time = time;
        this.description = description;
        this.ingredients = ingredients;
        this.owner = owner;
    }
}
