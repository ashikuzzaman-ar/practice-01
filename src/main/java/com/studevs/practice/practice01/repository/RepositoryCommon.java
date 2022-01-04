package com.studevs.practice.practice01.repository;

import com.studevs.practice.practice01.model.EntityCommon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCommon<T extends EntityCommon> extends JpaRepository<T, Long> {
}
