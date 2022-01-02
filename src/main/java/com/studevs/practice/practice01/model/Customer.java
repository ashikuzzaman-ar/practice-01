package com.studevs.practice.practice01.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer extends EntityCommon implements Serializable {

    private String name;
    private Double age;

    @OneToOne(cascade = CascadeType.ALL)
    private Address presentAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Address permanentAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<Contact> contacts;
}