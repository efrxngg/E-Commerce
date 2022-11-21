package com.empresax.core;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Transactional
@Component
@AllArgsConstructor
public class ConsoleApplication implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

    }

}