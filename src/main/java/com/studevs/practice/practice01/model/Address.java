package com.studevs.practice.practice01.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address extends EntityCommon {
	
	private String houseNumber;
	private String roadNumber;
	private String policeStation;
	private String city;
	private String state;
	private String zipCode;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Customer customer;
}
