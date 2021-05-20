package com.desafio.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.desafio.config.db.DBInitialize;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBInitialize dbInitialize;

    @Bean
    public boolean intastianteDatabase() throws ParseException {
        System.out.println("Inicia carga");
        dbInitialize.instantieateTestDatabase();
        System.out.println("Finaliza carga");
        return true;
    }
}
