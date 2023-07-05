package com.mybatis.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mybatis.common.SessionTemplate;
import com.mybatis.model.dao.StudentDao;
import com.mybatis.model.dto.Student;

public class StudentService {

	private StudentDao dao=new StudentDao();
	
	public int insertStudent() {
		SqlSession session = SessionTemplate.getSession();
		int result = dao.insertStudent(session);
//		System.out.println(session+"(*StudentService)");
		
		if(result>0) session.commit();
		else session.rollback();
		session.close();
		return result;
	}
	
	public int insertStudentByName(String name) {
		SqlSession session=SessionTemplate.getSession();
		int result=dao.insertStudentByName(session, name);
		
		if(result>0)session.commit();
		else session.rollback();
		session.close();
		return result;
	}
	
	public int insertStudentAll(Student s) {
		SqlSession session=SessionTemplate.getSession();
		int result=dao.insertStudentAll(session, s);
		if(result>0)session.commit();
		else session.rollback();
		session.close();
		return result;
	}
	
	
	public int updateStudent(Student s) {
		SqlSession session=SessionTemplate.getSession();
		int result=dao.updateStudent(session, s);
		if(result>0)session.commit();
		else session.rollback();
		session.close();
		return result;
	}
	
	public int deleteStudent(int no) {
		SqlSession session=SessionTemplate.getSession();
		int result=dao.deleteStudent(session, no);
		if(result>0)session.commit();
		else session.rollback();
		session.close();
		return result;
	}
	
	public int selectStudentCount() {
		SqlSession session=SessionTemplate.getSession();
		int count =dao.selectStudentCount(session);
		session.close();
		return count;
	}
	
	public Student selectStudent(int no) {
		SqlSession session=SessionTemplate.getSession();
		Student s=dao.selectStudent(session,no);
		session.close();
		return s;
	}
	
	public List<Student> selectStudentAll(){
		SqlSession session=SessionTemplate.getSession();
		List<Student> result=dao.selectStudentAll(session);
		session.close();
		return result;
	}
	
	public List<Student> selectStudentByName(String name){
		SqlSession session=SessionTemplate.getSession();
		List<Student>students=dao.selectStudentByName(session, name);
		session.close();
		return students;
	}
	
	public Map selectStudentMap(int no) {
		SqlSession session=SessionTemplate.getSession();
		Map result=dao.selectStudentMap(session,no);
		session.close();
		return result;
	}
	
	public List<Map> selectStudentMapAll(){
		SqlSession session=SessionTemplate.getSession();
		List<Map> students=dao.selectStudentMapAll(session);
		session.close();
		return students;
	}
}
