package com.icrn.yamr.repositories;

import com.icrn.yamr.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecipeRepository extends ReactiveMongoRepository<Recipe,String> {
}
