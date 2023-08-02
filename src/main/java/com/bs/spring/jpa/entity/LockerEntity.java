package com.bs.spring.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class LockerEntity {
	@Id
	private long lockerNo;
	private String lockerPosition;
	private String lockerColor;
	
	@OneToOne(mappedBy = "mylocker", cascade = {CascadeType.REMOVE})
	private StudentEntity student;
}
