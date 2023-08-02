package com.bs.spring.jpa.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@SequenceGenerator(name="seq_studentclubno",sequenceName = "seq_studentclubno",
initialValue = 1,allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentClubs {
	@Id
	@GeneratedValue(generator = "seq_studentclubno", strategy = GenerationType.SEQUENCE)
	private Long studentClubsNo;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="student_no")
	private StudentEntity student;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="club_no")
	private Club club;
	
	@Temporal(TemporalType.DATE)
	private Date enrollDate;
	
}
