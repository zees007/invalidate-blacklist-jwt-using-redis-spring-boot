package com.spring3.oauth.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class OauthJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthJwtApplication.class, args);
    }

}
