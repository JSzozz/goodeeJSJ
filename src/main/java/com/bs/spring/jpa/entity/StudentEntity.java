package com.bs.spring.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
public class StudentEntity {

	@Id
	private long studentNo;
	
	private String studentName;
	
	private int grade;
	
	private int classNumber;
	
	//영속성전이
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},orphanRemoval = true)
	@JoinColumn(name="mylocker", nullable = false)
	private LockerEntity mylocker;
}
