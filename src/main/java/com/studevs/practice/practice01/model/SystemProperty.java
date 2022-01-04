package com.studevs.practice.practice01.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system_property", indexes = {@Index(name = "idx_system_property_key", columnList = "key", unique = true)})
public class SystemProperty extends EntityCommon {

    @Column(unique = true, nullable = false)
    private String key;
    private String value;

    public static final transient String DB_VERSION = "db.version";
}
