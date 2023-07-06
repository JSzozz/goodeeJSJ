package com.mybatis.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
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
	
	public List<Student> selectStudentByName(SqlSession session, String name){
		return session.selectList("student.selectStudentByName", name); 
	}
	
	public Map selectStudentMap(SqlSession session,int no) {
		return session.selectOne("student.selectStudentMap",no);
	}
	
	public List<Map> selectStudentMapAll(SqlSession session){
		return session.selectList("student.selectStudentMapAll");
	}
	
	public List<Student> selectStudentPage(SqlSession session, int cPage, int numPerpage){
		//페이지처리할 때는 마이바티스가 제공하는 페이징처리 클래스를 이용
		//RowBounds클래스 이용
		//selectList호출 시 세번쨰 매개변수에 RowBounds클래스를 생성해서 전달해주면 됨.
		//매개변수에 있는 생성자를 이용
		//1.매개변수 : offset -> 시작 row번호(cPage-1)*numPerpage
		//2.매개변수 : 범위 -> numPerpage
		//new RowBounds((cPage-1)*numPerpage, numPerpage);
		
		RowBounds rb=new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("student.selectStudentPage",null,rb);
	}
}
