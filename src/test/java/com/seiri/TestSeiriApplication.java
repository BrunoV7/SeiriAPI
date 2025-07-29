package com.seiri;

import org.springframework.boot.SpringApplication;

public class TestSeiriApplication {

    public static void main(String[] args) {
        SpringApplication.from(SeiriApplication::main).run(args);
    }

}
