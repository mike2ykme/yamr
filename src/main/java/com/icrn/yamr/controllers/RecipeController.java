package com.icrn.yamr.controllers;

import com.icrn.yamr.domain.Ingredient;
import com.icrn.yamr.domain.Measurement;
import com.icrn.yamr.domain.Recipe;
import com.icrn.yamr.repositories.RecipeRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
@CrossOrigin("*")
@RestController
@RequestMapping(path = "/recipes",produces = {APPLICATION_JSON_UTF8_VALUE })
public class RecipeController {

    private RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @GetMapping
    public Flux<Recipe> allRecipes(){
        return recipeRepository.findAll();
    }

    @GetMapping("insertTest")
    public Mono<Recipe> insertARecord(){
        Recipe recipe = new Recipe();
        recipe.setDescription("Tomato Soup from a can");
        recipe.setTime("10 minutes");
        recipe.setName("Tomato Soup");
        Set<Ingredient> ingredientSet = new HashSet<>();
        ingredientSet.add(new Ingredient("Tomato Soup Can",1.0, Measurement.ITEM));
        ingredientSet.add(new Ingredient("Heavy Cream",0.5,Measurement.CUP));
        recipe.setIngredients(ingredientSet);
        return recipeRepository.save(recipe);

    }
    @PostMapping()
    public Mono<Recipe> insertRecipe(@Valid @RequestBody Recipe recipe){
        return recipeRepository.save(recipe);
    }

    @GetMapping("id/{id}")
    public Mono<Recipe> getById(@PathVariable("id") String id){
        return recipeRepository.findById(id);
    }
}
