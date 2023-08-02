package com.bs.spring.jpa.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Club {
	@Id
	private long clubNo;
	
	private String clubName;
	private String location;
	
//	@ManyToMany(mappedBy = "clubs")
//	private List<StudentEntity> students;
	@OneToMany(mappedBy = "club")
	private List<StudentClubs> clubs;
}








