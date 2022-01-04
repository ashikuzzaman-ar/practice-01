package com.studevs.practice.practice01.repository;

import com.studevs.practice.practice01.model.SystemProperty;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemPropertyRepository extends RepositoryCommon<SystemProperty> {

    public Optional<SystemProperty> findByKey(String key);
}
