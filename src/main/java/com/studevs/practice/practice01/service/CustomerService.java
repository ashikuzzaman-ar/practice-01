package com.studevs.practice.practice01.service;

import com.studevs.practice.practice01.repository.CustomerRepository;
import graphql.schema.idl.RuntimeWiring;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerService extends GraphQLServiceCommon {

    @Getter
    @Value("classpath:graphql/customer.graphql")
    private Resource resource;

    private final CustomerRepository customerRepository;

    protected RuntimeWiring buildRuntimeWiring() {
        log.debug("Calling: com.studevs.practice.practice01.service.CustomerService.buildRuntimeWiring");
        return RuntimeWiring.newRuntimeWiring().type("Query", typeWiring -> typeWiring.dataFetcher("customers", dataFetchingEnvironment -> this.customerRepository.findAll())).build();
    }
}