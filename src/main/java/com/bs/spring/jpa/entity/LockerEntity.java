package com.bs.spring.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "student")
@Entity
public class LockerEntity {
	@Id
	private long lockerNo;
	private String lockerPosition;
	private String lockerColor;
	
	@OneToOne(mappedBy = "mylocker", cascade = {CascadeType.REMOVE})
	private StudentEntity student;
}
