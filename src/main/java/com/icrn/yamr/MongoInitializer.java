package com.icrn.yamr;



import com.icrn.yamr.domain.User;
import com.icrn.yamr.repositories.UserRepository;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

/**
 * @author Rob Winch
 */
@Component
class MongoInitiailizer implements SmartInitializingSingleton {

    private final UserRepository users;

    MongoInitiailizer(UserRepository users) {
        this.users = users;
    }

    @Override
    public void afterSingletonsInstantiated() {
        // sha256 w/ salt encoded "password"
        String passsword = "{sha256}" + "73ac8218b92f7494366bf3a03c0c2ee2095d0c03a29cb34c95da327c7aa17173248af74d46ba2d4c";

        User rob = new User("1", "rob@example.com", "Rob", "Winch", passsword);
        User joe = new User("100", "joe@example.com","Joe", "Grandja",passsword);
        User vedran = new User("200", "vedran@example.com", "Vedran", "Pavić",passsword);
        User test = new User();
        test.setEmail("test@email.com");
        test.setId("alpha");
        test.setPassword("{bcrypt}" + "$2a$10$3Zw0H.Bn6v8nOBe1.siNsuYoE26khxvqfzbNR3R0SK94xDokC4cv2"); //password123

        this.users.save(test).block();
        this.users.save(rob).block();
        this.users.save(joe).block();
        this.users.save(vedran).block();


        // @formatter:off
//        this.users.findAll()
//                .doOnNext(user -> user.setPassword("{sha256}" + user.getPassword()))
//                .flatMap(this.users::save)
//                .collectList()
//                .block();
        // @formatter:on

    }
}