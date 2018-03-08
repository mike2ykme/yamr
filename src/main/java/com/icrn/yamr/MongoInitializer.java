package com.icrn.yamr;



import com.icrn.yamr.domain.Recipe;
import com.icrn.yamr.domain.User;
import com.icrn.yamr.repositories.RecipeRepository;
import com.icrn.yamr.repositories.UserRepository;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Rob Winch
 */
@Component
class MongoInitiailizer implements SmartInitializingSingleton {

    private final UserRepository users;
    private final RecipeRepository recipeRepo;
    private final PasswordEncoder encoder;

    public MongoInitiailizer(UserRepository users, RecipeRepository recipeRepo,PasswordEncoder encoder) {
        this.users = users;
        this.recipeRepo = recipeRepo;
        this.encoder = encoder;
    }

    @Override
    public void afterSingletonsInstantiated() {
        // sha256 w/ salt encoded "password"
        String passsword = "{sha256}" + "73ac8218b92f7494366bf3a03c0c2ee2095d0c03a29cb34c95da327c7aa17173248af74d46ba2d4c";

        User rob = new User(null, "rob@example.com", "Rob", "Winch", passsword);
        User joe = new User(null, "joe@example.com","Joe", "Grandja",passsword);
        User vedran = new User(null, "vedran@example.com", "Vedran", "Pavić",passsword);
        User test = new User();
        test.setEmail("test@email.com");

        test.setPassword(encoder.encode("password123")); //password123

        Recipe tomatoSoup = new Recipe("Tomato Soup","35 Min", "Can and soup", null,rob);

        this.users.save(test).block();
        this.users.save(rob).block();
        this.users.save(joe).block();
        this.users.save(vedran).block();

        this.recipeRepo.save(tomatoSoup).block();

        // @formatter:off
//        this.users.findAll()
//                .doOnNext(user -> user.setPassword("{sha256}" + user.getPassword()))
//                .flatMap(this.users::save)
//                .collectList()
//                .block();
        // @formatter:on

    }
}