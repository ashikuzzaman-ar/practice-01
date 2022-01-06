package com.studevs.practice.practice01.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public class EntityCommon implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private Long version;
	
	private String createdBy;
	
	@CreationTimestamp
	private LocalDateTime createTime;
	
	private String updatedBy;
	
	@UpdateTimestamp
	private LocalDateTime updateTime;
}
