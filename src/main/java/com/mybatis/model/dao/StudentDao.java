package com.mybatis.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.model.dto.Student;

public class StudentDao {

	public int insertStudent(SqlSession session) {
//		Prepared.. 아웃
		int result=session.insert("student.insertStudent");
		return result;
	}
		
	public int insertStudentByName(SqlSession session, String name) {
		int result=session.insert("student.insertStudentByName", name);
		return result;
	}
	
	public int insertStudentAll(SqlSession session, Student s) {
		return session.insert("student.insertStudentAll",s);
	}
	
	public int updateStudent(SqlSession session, Student s) {
		return session.insert("student.updateStudent",s);
	}
	
	public int deleteStudent(SqlSession session, int no) {
		return session.insert("student.deleteStudent",no);
	}
	
	public int selectStudentCount(SqlSession session) {
//		int result=session.selectOne("student.selectStudentCount");
		return session.selectOne("student.selectStudentCount");
	}
	public Student selectStudent(SqlSession session, int no) {
		return session.selectOne("student.selectStudent",no);
	}
	
	public List<Student> selectStudentAll(SqlSession session){
		return session.selectList("student.selectStudentAll");
	}
	
}
