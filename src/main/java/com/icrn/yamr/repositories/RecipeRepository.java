package com.icrn.yamr.repositories;

import com.icrn.yamr.domain.Recipe;
import com.icrn.yamr.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface RecipeRepository extends ReactiveMongoRepository<Recipe,String> {
//    Flux<Recipe> FindRecipesByUser(User user);
}
