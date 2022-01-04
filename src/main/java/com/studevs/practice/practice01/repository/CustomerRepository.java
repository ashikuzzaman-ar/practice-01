package com.studevs.practice.practice01.repository;

import com.studevs.practice.practice01.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends RepositoryCommon<Customer> {
}