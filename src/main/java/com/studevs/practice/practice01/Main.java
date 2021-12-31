package com.studevs.practice.practice01;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/customer")
class CustomerController {

    private final CustomerRepository repository;

    @GetMapping(value = "/get-all")
    public Flux<Customer> getCustomers() {
        log.info("Calling: com.studevs.practice.practice01.CustomerController.getCustomers");
        return this.repository.findAll();
    }
}

interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer> {
}


record Customer(@Id Integer id, String name) {
}