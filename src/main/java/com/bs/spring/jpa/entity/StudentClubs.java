package com.bs.spring.jpa.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Table(uniqueConstraints = {@UniqueConstraint(name = "uq_student_clubs", columnNames = { "student_no","club_no" })})
@SequenceGenerator(name="seq_studentclubno",sequenceName = "seq_studentclubno",
initialValue = 1,allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(StudentClubsId.class)
public class StudentClubs {
//	@Id
//	@GeneratedValue(generator = "seq_studentclubno", strategy = GenerationType.SEQUENCE)
//	private Long studentClubsNo;
	
	@Id
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="student_no")
	private StudentEntity student;
	
	@Id
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="club_no")
	private Club club;
	
	@Temporal(TemporalType.DATE)
	private Date enrollDate;
	
}
