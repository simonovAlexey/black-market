package com.simonov.blackMarket;

import com.simonov.blackMarket.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Initializer {

    @Autowired
    public Initializer(UserRepository userRepository) {

        /*for (int i = 0; i < 3; i++) {
            User entity = new User(UUID.randomUUID().toString());
            userRepository.save(entity);
            log.info(entity.toString());
        }*/
    }
}
