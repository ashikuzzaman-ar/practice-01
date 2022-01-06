package com.studevs.practice.practice01.configuration;

import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Slf4j
@Configuration
public class GraphQLConfiguration implements Serializable {
	
	@Bean
	public SchemaParser getSchemaParser() {
		log.debug("Configuring SchemaParser");
		return new SchemaParser();
	}
	
	@Bean
	public SchemaGenerator getSchemaGenerator() {
		log.debug("Configuring SchemaGenerator");
		return new SchemaGenerator();
	}
}