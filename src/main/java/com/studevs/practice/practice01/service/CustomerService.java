package com.studevs.practice.practice01.service;

import com.studevs.practice.practice01.model.Address;
import com.studevs.practice.practice01.model.Contact;
import com.studevs.practice.practice01.model.Customer;
import com.studevs.practice.practice01.repository.CustomerRepository;
import graphql.schema.idl.RuntimeWiring;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerService extends GraphQLServiceCommon {
	
	private final CustomerRepository customerRepository;
	@Getter
	@Value("classpath:graphql/customer.graphql")
	private Resource resource;
	
	protected RuntimeWiring buildRuntimeWiring() {
		log.debug("Calling: com.studevs.practice.practice01.service.CustomerService.buildRuntimeWiring");
		return RuntimeWiring.newRuntimeWiring().type("Query", typeWiring -> typeWiring.dataFetcher("customers", dataFetchingEnvironment -> this.customerRepository.findAll())).build();
	}
	
	
	public List<Customer> saveCustomers(List<Customer> customers) {
		log.debug("Calling: com.studevs.practice.practice01.service.CustomerService.saveCustomers");
		if (CollectionUtils.isEmpty(customers)) {
			return null;
		}
		try {
			customers.forEach(customer -> {
				Address permanentAddress = customer.getPermanentAddress();
				if (permanentAddress != null) {
					permanentAddress.setCustomer(customer);
				}
				Address presentAddress = customer.getPresentAddress();
				if (presentAddress != null) {
					presentAddress.setCustomer(customer);
				}
				List<Contact> contacts = customer.getContacts();
				if (!CollectionUtils.isEmpty(contacts)) {
					contacts.forEach(contact -> contact.setCustomer(customer));
				}
			});
			List<Customer> result = this.customerRepository.saveAllAndFlush(customers);
			if (!CollectionUtils.isEmpty(result)) {
				log.info("{} customer saved", result.size());
			}
			return result;
		} catch (Exception e) {
			log.error("Unexpected exception while saving customers!", e);
		}
		return null;
	}
}