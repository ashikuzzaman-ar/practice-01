package com.studevs.practice.practice01.util;

import com.studevs.practice.practice01.model.Address;
import com.studevs.practice.practice01.model.Contact;
import com.studevs.practice.practice01.model.Customer;
import com.studevs.practice.practice01.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
            Customer customer = Customer.builder()
                    .name("Customer " + i)
                    .age(random.nextDouble(100))
                    .build();
            customer.setPresentAddress(Address.builder()
                    .roadNumber(String.valueOf(random.nextDouble()))
                    .city("City " + i)
                    .customer(customer)
                    .build()
            );
            customer.setPermanentAddress(Address.builder()
                    .roadNumber(String.valueOf(random.nextDouble()))
                    .city("City " + i)
                    .customer(customer)
                    .build()
            );
            customer.setContacts(
                    IntStream.range(0, random.nextInt(5) + 1)
                            .mapToObj(n -> Contact.builder()
                                    .phoneNumber(String.valueOf(random.nextDouble()))
                                    .customer(customer)
                                    .build())
                            .collect(Collectors.toList())
            );
            log.debug("Saved customer with id: {}", this.customerRepository.save(customer).getId());
        }
    }
}