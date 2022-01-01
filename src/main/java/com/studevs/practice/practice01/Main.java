package com.studevs.practice.practice01;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

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

    private final CustomerService customerService;

    @PostMapping(value = "/get-all")
    public ResponseEntity<ExecutionResult> getCustomers(@RequestBody String query) {
        log.info("Calling: com.studevs.practice.practice01.CustomerController.getCustomers");
        return ResponseEntity.ok(this.customerService.getGraphQL().execute(query));
    }
}

@Slf4j
@RequiredArgsConstructor
@Service
class CustomerService implements Serializable {

    @Value("classpath:graphql/schema.graphql")
    private Resource resource;

    @Getter
    private GraphQL graphQL;

    private final CustomerRepository customerRepository;

    @PostConstruct
    private void loadSchema() throws IOException {
        log.debug("Calling: com.studevs.practice.practice01.CustomerService.loadSchema");
        File schemaFile = this.resource.getFile();
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
        RuntimeWiring runtimeWiring = buildWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private RuntimeWiring buildWiring() {
        log.debug("Calling: com.studevs.practice.practice01.CustomerService.buildWiring");
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("customers", dataFetchingEnvironment -> this.customerRepository.findAll()))
                .build();
    }
}

interface CustomerRepository extends JpaRepository<Customer, Integer> {
}

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;

    private Integer age;
}

@Slf4j
@RequiredArgsConstructor
@Component
class DataLoader implements ApplicationRunner {

    private final CustomerRepository customerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("Calling: com.studevs.practice.practice01.DataLoader.run");
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            log.debug("{}", this.customerRepository.save(new Customer(null, "Customer " + i, random.nextInt(100))));
        }
    }
}