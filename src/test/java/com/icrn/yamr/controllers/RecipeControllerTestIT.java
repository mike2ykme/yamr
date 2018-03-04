package com.icrn.yamr.controllers;

import com.icrn.yamr.domain.Ingredient;
import com.icrn.yamr.domain.Measurement;
import com.icrn.yamr.domain.Recipe;
import com.icrn.yamr.repositories.RecipeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecipeControllerTestIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    RecipeRepository recipeRepository;


//    @Test
//    public void allRecipes() {
//    }

    @Test
    public void testCreateRecipe(){

        Recipe recipe = new Recipe();
        recipe.setDescription("Avocado Toast");
        recipe.setTime("10 minutes");
        recipe.setName("Avocado plus toast");
        Set<Ingredient> ingredientSet = new HashSet<>();
        ingredientSet.add(new Ingredient("Avocado",1.0, Measurement.ITEM));
        ingredientSet.add(new Ingredient("Toast",2.0,Measurement.ITEM));
        recipe.setIngredients(ingredientSet);
//        recipeRepository.save(recipe);

        webTestClient.post().uri("/recipes")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(recipe),Recipe.class)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("Avocado plus toast");
    }
}