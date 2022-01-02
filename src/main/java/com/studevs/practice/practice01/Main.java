package com.studevs.practice.practice01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Date;

@Slf4j
@SpringBootApplication
@RestController
public class Main implements Serializable {

    public static void main(String[] args) {
        log.info("Starting application");
        SpringApplication.run(Main.class, args);
        log.info("Started application");
    }

    @RequestMapping(value = "/")
    public String hello() {
        log.debug("Calling: com.studevs.practice.practice01.Main.hello");
        return new Date().toString();
    }
}