package com.bs.spring.jpa.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
//	//영속성전이
//	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE},orphanRemoval = true)
//	@JoinColumn(name="mylocker", nullable = false)
//	private LockerEntity mylocker;
	@OneToOne(cascade = {CascadeType.PERSIST})
	private LockerEntity mylocker;
	
	//다대다 관계를 설정했을때
	//join테이블이 생성되어야한다. -> join테이블 생성에 대한 설정을 할 수 있다.
	@ManyToMany(cascade={CascadeType.PERSIST})
	@JoinTable(name="student_clubs",
	joinColumns = @JoinColumn(name="student_no") ,//현 entity의 pk가 저장되는 컬럼에 대한 설정
	inverseJoinColumns = @JoinColumn(name="club_no"))//상대방 entity의 pk가 저장되는 컬럼에 대한 설정
	private List<Club> clubs;
}
