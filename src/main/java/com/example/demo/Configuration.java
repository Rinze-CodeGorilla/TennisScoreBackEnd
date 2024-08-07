package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.SessionScope;

@org.springframework.context.annotation.Configuration
public class Configuration {
    /**
     * Provides a session scoped game bean, @SessionScope
     * wraps the bean with an object that retrieves the
     * right bean, because the bean might be used inside
     * a longer lived component, e.g. a Controller. This
     * does mean fields are not accessible, only methods
     */
    @Bean
    @SessionScope
    public Game game() {
        System.out.println("Create game");
        Game game = new Game();
        return game;
    }
}
