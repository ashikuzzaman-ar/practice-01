package com.studevs.practice.practice01.controller;

import com.studevs.practice.practice01.service.CustomerService;
import graphql.ExecutionResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(value = "/get-all")
    public ResponseEntity<ExecutionResult> getCustomers(@RequestBody String query) {
        log.info("Calling: com.studevs.practice.practice01.controller.CustomerController.getCustomers");
        return ResponseEntity.ok(this.customerService.execute(query));
    }
}