package com.studevs.practice.practice01;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
    public List<Customer> getCustomers() {
        log.info("Calling: com.studevs.practice.practice01.CustomerController.getCustomers");
        return this.repository.findAll();
    }
}

interface CustomerRepository extends JpaRepository<Customer, Integer> {
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;
}

@Slf4j
@RequiredArgsConstructor
@Component
class DataLoader implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("Calling: com.studevs.practice.practice01.DataLoader.run");
        for (int i = 1; i <= 1000; i++) {
            this.customerRepository.save(new Customer(null, "Customer " + i));
        }
    }
}