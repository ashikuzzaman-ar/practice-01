package com.studevs.practice.practice01.service;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

@Slf4j
public abstract class GraphQLServiceCommon implements Serializable {
	protected abstract Resource getResource();
	
	protected abstract RuntimeWiring buildRuntimeWiring();
	
	@Autowired
	private SchemaParser schemaParser;
	
	@Autowired
	private SchemaGenerator schemaGenerator;
	
	private GraphQL graphQL;
	
	@PostConstruct
	private void loadSchema() throws IOException {
		log.debug("Calling: com.studevs.practice.practice01.service.GraphQLServiceCommon.loadSchema");
		File schemaFile = this.getResource().getFile();
		log.debug("Generating schema for: {}", schemaFile.getName());
		this.graphQL = GraphQL.newGraphQL(this.schemaGenerator.makeExecutableSchema(this.schemaParser.parse(schemaFile), this.buildRuntimeWiring())).build();
	}
	
	public ExecutionResult execute(final String query) {
		log.debug("Calling: com.studevs.practice.practice01.service.GraphQLServiceCommon.execute");
		return this.graphQL.execute(query);
	}
}