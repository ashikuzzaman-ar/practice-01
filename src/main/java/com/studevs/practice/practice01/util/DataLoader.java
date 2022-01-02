package com.studevs.practice.practice01.util;

import com.studevs.practice.practice01.model.Customer;
import com.studevs.practice.practice01.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    @Override
    public void run(ApplicationArguments args) {
        log.debug("Calling: com.studevs.practice.practice01.util.DataLoader.run");
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            log.debug("{}", this.customerRepository.save(new Customer(null, "Customer " + i, random.nextInt(100))));
        }
    }
}