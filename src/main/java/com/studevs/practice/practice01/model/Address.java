package com.studevs.practice.practice01.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address extends EntityCommon implements Serializable {

    private String houseNumber;
    private String roadNumber;
    private String policeStation;
    private String city;
    private String state;
    private String zipCode;

    @OneToOne
    @JsonIgnore
    private Customer customer;
}
