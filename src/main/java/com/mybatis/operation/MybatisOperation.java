package com.mybatis.operation;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.pojo.Student;

public class MybatisOperation{
	
	public static void main(String args[]) throws IOException{

		Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sqlSessionFactory.openSession();
	
		//Select all student data
		List<Student> student = session.selectList("Student.getAll");
	
		for(Student st : student){
			System.out.println(st.getId());
			System.out.println(st.getFirst_name());
			System.out.println(st.getLast_name());
		}
			
		System.out.println("Records display Successfully");
	
		//Insert operation
		Student student1 = new Student ("Harsh","Darji");
	
		session.insert("Student.insert", student1);
		System.out.println("record inserted successfully");
	
		//Delete operation
		session.delete("Student.deleteById", 1);
	
		System.out.println("Record deleted successfully");
	
		//Update operation
	
		//select a particular student using id
		Student student2 = (Student) session.selectOne("Student.getById",2);
		System.out.println("Current details of the student are");
		System.out.println(student.toString());
	
		//Set new values to the first name and last name of the student
		student2.setFirst_name("Nitesh");
		student2.setLast_name("Shah");
	
		//Update the student data
		session.update("Student.update",student2);
		System.out.println("Record updated successfully");
	
		//Checking data
		Student std = (Student) session.selectOne("Student.getById",2);
		System.out.println("Details of the student after update operation");
		System.out.println(std.toString()); 
	
		session.commit();
		session.close();
	}
}
